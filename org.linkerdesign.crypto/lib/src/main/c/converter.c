#include "converter.h"

#include <stdlib.h>

jbyteArray as_jbyte_array(JNIEnv *env, unsigned char *bytes, int length, int freeBytes)
{
  jbyteArray jBytes = (*env)->NewByteArray(env, length);
  if(NULL == jBytes)
  {
    // failed to malloc
    return NULL;
  }
  (*env)->SetByteArrayRegion(env, jBytes, 0, length, (jbyte *) bytes);
  if (freeBytes != 0) free(bytes);
  return jBytes;
}

unsigned char *as_unsigned_char_array(JNIEnv *env, jbyteArray jBytes)
{
  int length = (*env)->GetArrayLength(env, jBytes);
  unsigned char *bytes = (unsigned char *)malloc(length);
  if (NULL == bytes)
  {
    // failed to malloc
    return NULL;
  }
  (*env)->GetByteArrayRegion(env, jBytes, 0, length, (jbyte *)bytes);
  return bytes;
}

int as_unsigned_char_array_ex(unsigned char *bytes, JNIEnv *env, jbyteArray jBytes)
{
  int length = (*env)->GetArrayLength(env, jBytes);
  (*env)->GetByteArrayRegion(env, jBytes, 0, length, (jbyte *)bytes);
  return 0;
}