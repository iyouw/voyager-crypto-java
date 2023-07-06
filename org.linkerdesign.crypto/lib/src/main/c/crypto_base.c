#include "crypto_base.h"

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <openssl/err.h>

void handleErrors(void)
{
  ERR_print_errors_fp(stderr);
  abort();
}

const EVP_MD *get_algorithm(enum MD_ALGORITHM algorithm)
{
  const EVP_MD *res = NULL;
  switch (algorithm)
  {
    case SHA1:
      res = EVP_sha1();
      break;
    case SHA256:
      res = EVP_sha256();
      break;
    case SHA384:
      res = EVP_sha384();
    case SHA512:
      res = EVP_sha512();
      break;
    case MD5:
      res = EVP_md5();
      break;
    case MD5_SHA1:
      res = EVP_md5_sha1();
      break;
  }
  if (NULL == res)
  {
    fprintf(stderr, "Could not find the algorihtm");
    exit(EXIT_FAILURE);
  }
  return res;
}

const EVP_CIPHER *get_cipher(enum AES_MODE mode, int key_length)
{
  const EVP_CIPHER *res = NULL;
  switch (mode)
  {
    case CTR:
      if (128 == key_length) 
      {
          res = EVP_aes_128_ctr();
      } 
      else if ( 192 == key_length) 
      {
          res = EVP_aes_192_ctr();
      } 
      else if (256 == key_length) 
      {
          res = EVP_aes_256_ctr();
      }
      break;
    case CBC:
      if (128 == key_length) 
      {
          res = EVP_aes_128_cbc();
      } 
      else if ( 192 == key_length) 
      {
          res = EVP_aes_192_cbc();
      } 
      else if (256 == key_length) 
      {
          res = EVP_aes_256_cbc();
      } 
      break;
  }

  if (NULL == res)
    fprintf(stderr, "Could not find the aes algorithm!\n");
  
  return res;
}

int resolve_read_block_size(int block_size, int buf_len)
{
  int count = ceil((double)buf_len / block_size);
  return count * block_size;
}