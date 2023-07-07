package org.linkerdesign.crypto;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * crypto base
 */
public class CryptoBase {
  /**
   * default native buffer size.
   */
  public static final int DEFAULT_BUFFER_SIZE = 8092;

  /**
   * native digest 
   * @param bufferLength native buffer size
   * @param algorithm digest algorithm
   * @param readCallback native data readcallback
   * @return digest result
   */
  protected byte[] digestNative(int bufferLength, MdAlgorithm algorithm, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.digest(bufferLength, algorithm.getValue());
  }

  /**
   * native aes key generate
   * @param keySize aes key size
   * @return aes key
   */
  protected byte[] generateAesKeyNative(AesKeySize keySize) {
    Native nat = new Native();
    return nat.generateAesKey(keySize.getValue());
  }

  /**
   * native aes iv generate
   * @return aes iv
   */
  protected byte[] generateAesIVNative() {
    Native nat = new Native();
    return nat.generateAesIV();
  }

  /**
   * native aes encrypt
   * @param bufferLength native buffer size
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param readCallback native data readcallback
   * @return encrypt result
   */
  protected byte[] aesEncryptNative(int bufferLength, byte[] key, byte[] iv, AesMode mode, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.aesEncrypt(bufferLength, key, iv, mode.getValue());
  }

  /**
   * native aes decrypt
   * @param bufferLength native buffer size
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param readCallback native data readcallbak
   * @return decrypt result
   */
  protected byte[] aesDecryptNative(int bufferLength, byte[] key, byte[] iv, AesMode mode, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.aesDecrypt(bufferLength, key, iv, mode.getValue());
  }
}
