#ifndef LINKER_DESIGN_CRYPTO_BASE_H
#define LINKER_DESIGN_CRYPTO_BASE_H

#include <openssl/evp.h>

#define MAX_REF_PER_FRAME 6

enum AES_MODE {
  CTR = 1,
  CBC,
};

enum MD_ALGORITHM {
  SHA1 = 1,
  SHA256,
  SHA384,
  SHA512,
  MD5,
  MD5_SHA1,
};

void handleErrors(void);

const EVP_MD *get_algorithm(enum MD_ALGORITHM algorithm);

const EVP_CIPHER *get_cipher(enum AES_MODE mode, int key_length);

int resolve_read_block_size(int block_size, int buf_len);
#endif