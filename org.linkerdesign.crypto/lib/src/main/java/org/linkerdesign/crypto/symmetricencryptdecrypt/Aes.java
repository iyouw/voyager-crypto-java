package org.linkerdesign.crypto.symmetricencryptdecrypt;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.EncodingType;

/**
 * Aes
 */
public class Aes extends AesBase {
  private AesMode _mode;

  /**
   * constructor
   */
  public Aes() {
    this(AesMode.CBC);
  }

  /**
   * constructor
   * @param mode aes mode
   */
  public Aes(AesMode mode) {
    _mode = mode;
  }

  /**
   * get the current aes mode
   * @return aes mode
   */
  public AesMode getMode() {
    return _mode;
  }

  /**
   * aes encrypt the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return the encrypted data
   */
  public byte[] encrypt(String key, String iv, byte[] data) {
    return encrypt(key, iv, data, EncodingType.Base64, EncodingType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoded type(base64, hex, utf8) 
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   */
  public byte[] encrypt(String key, String iv, byte[] data, EncodingType keyType, EncodingType ivType, int bufferSize) {
    return encryptCore(data, key, keyType, iv, ivType, getMode(), bufferSize);
  }

  /**
   * aes encrypt the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param dataType data encoding
   * @return the encrypt result
   */
  public byte[] encrypt(String key, String iv, String data, EncodingType dataType) {
    return encryptCore(data, dataType, key, EncodingType.Base64, iv, EncodingType.Base64, getMode(), DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param dataType data encoding
   * @return the encrypt result
   */
  public byte[] encrypt(String key, String iv, String data, EncodingType dataType, EncodingType keyType, EncodingType ivType) {
    return encryptCore(data, dataType, key, keyType, iv, ivType, getMode(), DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param dataType data encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return the encrypt result
   */
  public byte[] encrypt(String key, String iv, String data, EncodingType keyType, EncodingType ivType, EncodingType dataType, int bufferSize) {
    return encryptCore(data, dataType, key, keyType, iv, ivType, getMode(), bufferSize);
  }

  /**
   * aes decrypt. the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   */
  public byte[] decrypt(String key, String iv, byte[] data) {
    return decrypt(key, iv, data, EncodingType.Base64, EncodingType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt. the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param exportType the result encoding
   * @return encrypted data
   */
  public String decrypt(String key, String iv, byte[] data, EncodingType exportType) {
    return decryptCore(data, key, EncodingType.Base64, iv, EncodingType.Base64, getMode(), exportType, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt.
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoded type(base64, hex, utf8) 
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   */
  public byte[] decrypt(String key, String iv, byte[] data, EncodingType keyType, EncodingType ivType, int bufferSize) {
    return decryptCore(data, key, keyType, iv, ivType, getMode(), bufferSize);
  }

  /**
   * generate aes key with size of 256
   * @return the raw key of type byte[]
   */
  public byte[] generateKey() {
    return generateKeyCore(AesKeySize.KS256);
  }

  /**
   * generate aes key
   * @param keySize aes key length (128, 192, 256)
   * @param exportType key encoded type (base64, hex, utf8)
   * @return the encoded key
   */
  public String generateKey(AesKeySize keySize, EncodingType exportType) {
    return generateKeyCore(keySize, exportType);
  }

  /**
   * generate aes iv
   * @return the raw iv of type byte[]
   */
  public byte[] generateIV() {
    return generateIVCore();
  }

  /**
   * generate aes iv with the exporttype encoded
   * @param exportType result encoded type (base64, hex, utf8)
   * @return encoded iv
   */
  public String generateIV(EncodingType exportType) {
    return generateIVCore(exportType);
  }
}
