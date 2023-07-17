#include "org_linkerdesign_crypto_Native.h"

#include "crypto_base.h"
#include "converter.h"

#include <openssl/rand.h>

/*
 * Class:     org_linkerdesign_crypto_Native
 * Method:    digest
 * Signature: (IILorg/linkerdesign/crypto/ReadCallback;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_linkerdesign_crypto_Native_digest(
  JNIEnv *env, 
  jobject obj, 
  jint bufferLength, 
  jint algorithm,
  jobject readCallback) 
{
  jmethodID mid;

  EVP_MD_CTX *ctx;
  const EVP_MD *type;

  unsigned char *message;
  unsigned char *digest;
  unsigned int digest_len;

  int read_byte_len;
  jbyteArray read_bytes;

  jclass cls = (*env)->GetObjectClass(env, readCallback);
  if (NULL == (mid = (*env)->GetMethodID(env, cls, "read", "(I)[B")))
    goto err;

  if (NULL == (ctx = EVP_MD_CTX_new()))
    goto err;

  if (NULL == (type = get_algorithm((enum MD_ALGORITHM)algorithm)))
    goto err;

  if (1 != EVP_DigestInit_ex(ctx, type, NULL))
    goto err;

  if (NULL == (message = (unsigned char *)malloc(bufferLength)))
    goto err;

  while (1)
  {
    // create nested scope
    if ((*env)->PushLocalFrame(env, MAX_REF_PER_FRAME) < 0)
      goto err;
    
    if(NULL == (read_bytes = (jbyteArray)(*env)->CallObjectMethod(env, readCallback, mid, bufferLength)))
    {
      (*env)->PopLocalFrame(env, NULL);
      break;
    }

    read_byte_len = (*env)->GetArrayLength(env,read_bytes);
    as_unsigned_char_array_ex(message, env, read_bytes);
    if (1 != EVP_DigestUpdate(ctx, message, read_byte_len))
      goto err;

    (*env)->PopLocalFrame(env, NULL);
  }
  
  if (NULL == (digest = OPENSSL_malloc(EVP_MD_size(type))))
    goto err;
  
  if (1 != EVP_DigestFinal_ex(ctx, digest, &digest_len))
    goto err;

  jbyteArray res = as_jbyte_array(env, digest, digest_len, 0);

  EVP_MD_CTX_free(ctx);
  OPENSSL_free(digest);
  free(message);

  return res;
err:
  if (NULL != ctx) EVP_MD_CTX_free(ctx);
  if (NULL != digest) OPENSSL_free(digest);
  if (NULL != message) free(message);
  handleErrors();
  return NULL;
}

/*
 * Class:     org_linkerdesign_crypto_Native
 * Method:    generateAesKey
 * Signature: (I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_linkerdesign_crypto_Native_generateAesKey(
  JNIEnv *env, 
  jobject obj, 
  jint length)
{
  int key_len = length >> 3;
  unsigned char *key = (unsigned char*)malloc(key_len);
  RAND_bytes(key, key_len);
  return as_jbyte_array(env, key, key_len, 1);
}

/*
 * Class:     org_linkerdesign_crypto_Native
 * Method:    generateAesIV
 * Signature: ()[B
 */
JNIEXPORT jbyteArray JNICALL Java_org_linkerdesign_crypto_Native_generateAesIV(
  JNIEnv *env, 
  jobject obj)
{
  const int len = 16;
  unsigned char *iv = (unsigned char*)malloc(len);
  RAND_bytes(iv, len);
  return as_jbyte_array(env, iv, len, 1);
}

/*
 * Class:     org_linkerdesign_crypto_Native
 * Method:    aesEncrypt
 * Signature: (I[B[BILorg/linkerdesign/crypto/ReadCallback;Lorg/linkerdesign/crypto/WriteCallback;)I
 */
JNIEXPORT jint JNICALL Java_org_linkerdesign_crypto_Native_aesEncrypt(
  JNIEnv *env, 
  jobject obj, 
  jint bufferLength, 
  jbyteArray key, 
  jbyteArray iv, 
  jint aesMode,
  jobject readCallback,
  jobject writeCallback)
{
  jmethodID readCallbackMid;
  jmethodID writeCallbackMid;

  EVP_CIPHER_CTX *ctx;
  const EVP_CIPHER *cipher;

  int len;
  int read_len;
  jbyteArray read_bytes;

  unsigned char *plaintext;
  unsigned char *ciphertext;

  unsigned char *key_buf;
  unsigned char *iv_buf;

  const int read_block_size = resolve_read_block_size(16, bufferLength);

  const int block_size = (*env)->GetArrayLength(env, key) << 3;

  jclass readCallbackCls = (*env)->GetObjectClass(env, readCallback);
  jclass writeCallbackCls = (*env)->GetObjectClass(env, writeCallback);

  if (NULL == (readCallbackMid = (*env)->GetMethodID(env, readCallbackCls, "read", "(I)[B")))
    goto err;
  
  if (NULL == (writeCallbackMid = (*env)->GetMethodID(env, writeCallbackCls, "write", "([B)V")))
    goto err;

  if (NULL == (key_buf = as_unsigned_char_array(env, key)))
    goto err;

  if (NULL == (iv_buf = as_unsigned_char_array(env, iv)))
    goto err;
  
  if (NULL == (plaintext = (unsigned char *)malloc(read_block_size)))
    goto err;
  
  if (NULL == (ciphertext = (unsigned char *)malloc(read_block_size)))
    goto err;
  
  if (NULL == (ctx = EVP_CIPHER_CTX_new()))
    goto err;
  
  if (NULL == (cipher = get_cipher((enum AES_MODE)aesMode, block_size)))
    goto err;
  
  if (1 != EVP_EncryptInit_ex(ctx, cipher, NULL, key_buf, iv_buf))
    goto err;

  while(1)
  {
    if ((*env)->PushLocalFrame(env, MAX_REF_PER_FRAME) < 0)
      goto err;
    
    if (NULL == (read_bytes = (jbyteArray)(*env)->CallObjectMethod(env, readCallback, readCallbackMid, (jint)read_block_size)))
    {
      (*env)->PopLocalFrame(env, NULL);
      break;
    }

    read_len = (*env)->GetArrayLength(env, read_bytes);
    as_unsigned_char_array_ex(plaintext, env, read_bytes);
    if (1 != EVP_EncryptUpdate(ctx, ciphertext, &len, plaintext, read_len))
      goto err;
    (*env)->CallVoidMethod(env, writeCallback, writeCallbackMid, as_jbyte_array(env, ciphertext, len, 0));

    (*env)->PopLocalFrame(env, NULL);
  }

  if(1 != EVP_EncryptFinal_ex(ctx, ciphertext, &len))
    goto err;

  (*env)->CallVoidMethod(env, writeCallback, writeCallbackMid, as_jbyte_array(env, ciphertext, len, 0));

  EVP_CIPHER_CTX_free(ctx);
  free(plaintext);
  free(ciphertext);
  free(key_buf);
  free(iv_buf);

  return 0;
err:
  if (NULL != ctx) EVP_CIPHER_CTX_free(ctx);
  if (NULL != plaintext) free(plaintext);
  if (NULL != ciphertext) free(ciphertext);
  if (NULL != key_buf) free(key_buf);
  if (NULL != iv_buf) free(iv_buf);
  handleErrors();
  return -1;
}

/*
 * Class:     org_linkerdesign_crypto_Native
 * Method:    aesDecrypt
 * Signature: (I[B[BILorg/linkerdesign/crypto/ReadCallback;Lorg/linkerdesign/crypto/WriteCallback;)I
 */
JNIEXPORT jint JNICALL Java_org_linkerdesign_crypto_Native_aesDecrypt(
  JNIEnv *env, 
  jobject obj, 
  jint bufferLength, 
  jbyteArray key, 
  jbyteArray iv, 
  jint aesMode,
  jobject readCallback,
  jobject writeCalback)
{
  jmethodID readCallbackMid;
  jmethodID writeCallbackMid;

  EVP_CIPHER_CTX *ctx;
  const EVP_CIPHER *cipher;

  int len;
  int read_len;
  jbyteArray read_bytes;

  unsigned char *plaintext;
  unsigned char *ciphertext;

  unsigned char *key_buf;
  unsigned char *iv_buf;

  const int read_block_size = resolve_read_block_size(16, bufferLength);
  const int block_size = (*env)->GetArrayLength(env, key) << 3;

  jclass readCallbackCls = (*env)->GetObjectClass(env, readCallback);
  jclass writeCallbackCls = (*env)->GetObjectClass(env, writeCalback);

  if (NULL == (readCallbackMid = (*env)->GetMethodID(env, readCallbackCls, "read", "(I)[B")))
    goto err;
  
  if (NULL == (writeCallbackMid = (*env)->GetMethodID(env, writeCallbackCls, "write", "([B)V")))
    goto err;

  if (NULL == (key_buf = as_unsigned_char_array(env, key)))
    goto err;

  if (NULL == (iv_buf = as_unsigned_char_array(env, iv)))
    goto err;
  
  if (NULL == (plaintext = (unsigned char *)malloc(read_block_size)))
    goto err;
  
  if (NULL == (ciphertext = (unsigned char *)malloc(read_block_size)))
    goto err;
  
  if (NULL == (ctx = EVP_CIPHER_CTX_new()))
    goto err;
  
  if (NULL == (cipher = get_cipher((enum AES_MODE)aesMode, block_size)))
    goto err;
  
  if (1 != EVP_DecryptInit_ex(ctx, cipher, NULL, key_buf, iv_buf))
    goto err;

  while(1)
  {
    if ((*env)->PushLocalFrame(env, MAX_REF_PER_FRAME) < 0)
      goto err;
    
    if (NULL == (read_bytes = (jbyteArray)(*env)->CallObjectMethod(env, readCallback, readCallbackMid, (jint)read_block_size)))
    {
      (*env)->PopLocalFrame(env, NULL);
      break;
    }

    read_len = (*env)->GetArrayLength(env, read_bytes);
    as_unsigned_char_array_ex(ciphertext, env, read_bytes);
    if (1 != EVP_DecryptUpdate(ctx, plaintext, &len, ciphertext, read_len))
      goto err;
    (*env)->CallVoidMethod(env, writeCalback, writeCallbackMid, as_jbyte_array(env, plaintext, len, 0));

    (*env)->PopLocalFrame(env, NULL);
  }

  if(1 != EVP_DecryptFinal_ex(ctx, plaintext, &len))
    goto err;
  
  (*env)->CallVoidMethod(env, writeCalback, writeCallbackMid, as_jbyte_array(env, plaintext, len, 0));

  EVP_CIPHER_CTX_free(ctx);
  free(plaintext);
  free(ciphertext);
  free(key_buf);
  free(iv_buf);

  return 0;
err:
  if (NULL != ctx) EVP_CIPHER_CTX_free(ctx);
  if (NULL != plaintext) free(plaintext);
  if (NULL != ciphertext) free(ciphertext);
  if (NULL != key_buf) free(key_buf);
  if (NULL != iv_buf) free(iv_buf);
  handleErrors();
  return -1;
}