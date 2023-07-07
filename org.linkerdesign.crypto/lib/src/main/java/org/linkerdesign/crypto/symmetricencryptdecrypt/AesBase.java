package org.linkerdesign.crypto.symmetricencryptdecrypt;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.CryptoBase;
import org.linkerdesign.crypto.ReadCallback;
import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.abstraction.Reader;
import org.linkerdesign.crypto.datatype.BinaryDecodeStrategy;
import org.linkerdesign.crypto.datatype.BinaryEncodeStrategy;
import org.linkerdesign.crypto.reader.ReaderStrategy;

/**
 * aes base 
 */
class AesBase extends CryptoBase {
  /**
   * aes encrypt with reader
   * @param reader data reader
   * @param key aes key
   * @param iv  aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] encryptCore(Reader reader, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    ReadCallback readCallback = (int length) -> reader.read(length);
    return aesEncryptNative(bufferSize, key, iv, mode, readCallback);
  }

  /**
   * aes encrypt with byte array
   * @param data plain data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] encryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   * aes encrypt with byte array, key,iv encoded
   * @param data plain data
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] encryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize)
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);
    
    return encryptCore(data, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes encrypt with string result
   * @param data plain data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result 
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize)
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes encrypt with string result
   * @param data plain data
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv 
   * @param ivType iv encoding
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes encrypt with string data
   * @param data plain data
   * @param dataType data encoding
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] encryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(data, dataType);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   * aes encrypt
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] encryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return encryptCore(data, dataType, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes encrypt
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, dataType, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes encrypt
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, dataType, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes encrypt
   * @param data data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] encryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   *  aes encrypt
   * @param data data
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] encryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return encryptCore(data, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes encrypt with string result
   * @param data data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes encrypt with string result
   * @param data data
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aesmode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String encryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt
   * @param reader reader
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] decryptCore(Reader reader, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    ReadCallback readCallback = (int length) -> reader.read(length);
    return aesDecryptNative(bufferSize, key, iv, mode, readCallback);
  }

  /**
   * aes decrypt
   * @param data data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] decryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   * aes decrypt
   * @param data daa
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] decryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes decrypt with string result
   * @param data data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt with string result
   * @param data data
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt with string data
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] decryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(data, dataType);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   * aes decrypt with string data
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] decryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, dataType, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes decrypt with string data
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result 
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, dataType, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt with string data
   * @param data data
   * @param dataType data encoding
   * @param key aes key
   * @param keyType key encoding
   * @param iv aes iv
   * @param ivType iv encoding
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result 
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, dataType, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt with stream data
   * @param data plain data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] decryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  /**
   * aes decrypt with stream data
   * @param data plain data
   * @param key aes key
   * @param keyType key encoding
   * @param iv iv 
   * @param ivType iv encoding
   * @param mode aes mode
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result 
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] decryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
   throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, keyData, ivData, mode, bufferSize);
  }

  /**
   * aes decrypt with stream data
   * @param data plain data
   * @param key aes key
   * @param iv aes iv
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * aes decrypt with stream data
   * @param data plain data
   * @param key aes key
   * @param keyType key encoding
   * @param iv iv 
   * @param ivType iv encoding
   * @param mode aes mode
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String decryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType,  int bufferSize) 
   throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * generate aes key
   * @param keySize key size
   * @return aes key
   */
  protected byte[] generateKeyCore(AesKeySize keySize) {
    return generateAesKeyNative(keySize);
  }

  /**
   * generate aes key
   * @param keySize key size
   * @param exportType key encoding
   * @return aes key
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String generateKeyCore(AesKeySize keySize, ExportType exportType) 
   throws UnsupportedEncodingException {
    byte[] bytes = generateKeyCore(keySize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * generate aes iv
   * @return aes iv
   */
  protected byte[] generateIVCore() {
    return generateAesIVNative();
  }

  /**
   * generate aes iv
   * @param exportType iv encoding
   * @return aes iv
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String generateIVCore(ExportType exportType)
    throws UnsupportedEncodingException {
    byte[] bytes = generateIVCore();
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }
}
