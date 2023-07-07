package org.linkerdesign.crypto.symmetricencryptdecrypt;

import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.ExportType;

/**
 * Aes
 */
public class Aes extends AesBase {
  /**
   * aes encrypt with cbc mode. the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return the encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCBC(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return encryptCBC(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with cbc mode
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoded type(base64, hex, utf8) 
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCBC(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes decrypt with cbc mode. the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] decryptCBC(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return decryptCBC(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with cbc mode.
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoded type(base64, hex, utf8) 
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] decryptCBC(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes encrypt with cbc mode. the plain data is utf-8 encoded, the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCBCWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException{
    return encryptCBCWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with cbc mode. the plain data is utf-8 encoded.
   * @param key aes key
   * @param iv aes iv
   * @param data plain data with utf8 encoded
   * @param keyType key encoded type(base64, hex, utf8)
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCBCWithUTF8(String key, String iv, String data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes encrypt with cbc mode. the plain data is utf-8 encoded. the key and iv are base64 encoded, the result is a string with `exporteType` encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param exportType result ecoded type (base64, hex, utf8)
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String encryptCBCWithUTF8(String key, String iv, String data, ExportType exportType) 
    throws UnsupportedEncodingException{
    return encryptCBCWithUTF8(key, iv, data, exportType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with cbc mode. the plain data is utf-8 encoded. the result is a string with `exporteType` encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param exportType result ecoded type (base64, hex, utf8)
   * @param keyType key encoded type(base64, hex, utf8)
   * @param ivType iv encoded type(base64, hex, utf8)
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String encryptCBCWithUTF8(String key, String iv, String data, ExportType exportType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, exportType, bufferSize);
  }

  /**
   * aes decrypt with cbc mode. the plain data is utf-8 encoded, the result is utf8 encoded, the key and iv are base64 encoded
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCBCWithUTF8(String key, String iv, byte[] data) 
    throws UnsupportedEncodingException {
    return decryptCBCWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with cbc mode. the result is of type string(utf8). 
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCBCWithUTF8(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  /**
   * aes decrypt with cbc mode. the result is of type string(utf8). the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param dataType data encoding
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCBCWithUTF8(String key, String iv, String data, ExportType dataType) 
    throws UnsupportedEncodingException {
    return decryptCBCWithUTF8(key, iv, data, dataType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with cbc mode. the result is of type string(utf8)
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param dataType data encoding
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCBCWithUTF8(String key, String iv, String data, ExportType dataType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, dataType, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  /**
   * aes encrypt with ctr mode. the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCTR(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return encryptCTR(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with ctr mode.
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCTR(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes decrypt with ctr mode. the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] decryptCTR(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return decryptCTR(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with ctr mode. 
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] decryptCTR(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes encrypt with ctr mode. the encrypt result is of type byte[]. the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCTRWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException{
    return encryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with ctr mode. the encrypt result is of type byte[].
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] encryptCTRWithUTF8(String key, String iv, String data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, bufferSize);
  }

  /**
   * aes encrypt with ctr mode. the encrypt result is of type string(utf8). the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param exportType encrypt result's encoding
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String encryptCTRWithUTF8(String key, String iv, String data, ExportType exportType) 
    throws UnsupportedEncodingException{
    return encryptCTRWithUTF8(key, iv, data, exportType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes encrypt with ctr mode. the encrypt result is of type string(utf8)
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param exportType encrypt result's encoding
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String encryptCTRWithUTF8(String key, String iv, String data, ExportType exportType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CTR;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, exportType, bufferSize);
  }

  /**
   * aes decrypt with ctr mode. the result is of type string(utf8). the key and iv are encoded by base64
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCTRWithUTF8(String key, String iv, byte[] data) 
    throws UnsupportedEncodingException {
    return decryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with ctr mode. the result encoded type is of type string(utf8)
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCTRWithUTF8(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  /**
   * aes decrypt with ctr mode. the plain data encoded type is base64. the key and iv encoded type are base64. the result encoded type is utf8
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @return encryted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCTRWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException {
    return decryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  /**
   * aes decrypt with ctr mode. the plain data is dataType encoded. the result is utf8 encoded 
   * @param key aes key
   * @param iv aes iv
   * @param data plain data
   * @param dataType plain data encoding
   * @param keyType key encoding
   * @param ivType iv encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return encrypted data
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String decryptCTRWithUTF8(String key, String iv, String data, ExportType dataType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, dataType, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
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
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String generateKey(AesKeySize keySize, ExportType exportType)
    throws UnsupportedEncodingException {
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
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String generateIV(ExportType exportType) 
    throws UnsupportedEncodingException {
    return generateIVCore(exportType);
  }
}
