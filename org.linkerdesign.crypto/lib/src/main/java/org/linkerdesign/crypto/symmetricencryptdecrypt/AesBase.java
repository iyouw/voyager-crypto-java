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

public class AesBase extends CryptoBase {
  public byte[] encryptCore(Reader reader, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    ReadCallback readCallback = (int length) -> reader.read(length);
    return aesEncryptNative(bufferSize, key, iv, mode, readCallback);
  }

  public byte[] encryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] encryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize)
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);
    
    return encryptCore(data, keyData, ivData, mode, bufferSize);
  }

  public String encryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize)
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String encryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] encryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(data, dataType);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] encryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return encryptCore(data, dataType, keyData, ivData, mode, bufferSize);
  }

  public String encryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, dataType, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String encryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, dataType, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }


  public byte[] encryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return encryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] encryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return encryptCore(data, keyData, ivData, mode, bufferSize);
  }

  public String encryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String encryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] decryptCore(Reader reader, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    ReadCallback readCallback = (int length) -> reader.read(length);
    return aesDecryptNative(bufferSize, key, iv, mode, readCallback);
  }

  public byte[] decryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] decryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, keyData, ivData, mode, bufferSize);
  }

  public String decryptCore(byte[] data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String decryptCore(byte[] data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] decryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(data, dataType);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] decryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
    throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, dataType, keyData, ivData, mode, bufferSize);
  }

  public String decryptCore(String data, ExportType dataType, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, dataType, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String decryptCore(String data, ExportType dataType, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, dataType, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] decryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, int bufferSize) {
    Reader reader = new ReaderStrategy(data);
    return decryptCore(reader, key, iv, mode, bufferSize);
  }

  public byte[] decryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, int bufferSize) 
   throws UnsupportedEncodingException {
    BinaryDecodeStrategy strategy = new BinaryDecodeStrategy(keyType);
    byte[] keyData = strategy.decode(key);

    strategy = new BinaryDecodeStrategy(ivType);
    byte[] ivData = strategy.decode(iv);

    return decryptCore(data, keyData, ivData, mode, bufferSize);
  }

  public String decryptCore(InputStream data, byte[] key, byte[] iv, AesMode mode, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, iv, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public String decryptCore(InputStream data, String key, ExportType keyType, String iv, ExportType ivType, AesMode mode, ExportType exportType,  int bufferSize) 
   throws UnsupportedEncodingException {
    byte[] bytes = decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] generateKeyCore(AesKeySize keySize) {
    return generateAesKeyNative(keySize);
  }

  public String generateKeyCore(AesKeySize keySize, ExportType exportType) 
   throws UnsupportedEncodingException {
    byte[] bytes = generateKeyCore(keySize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  public byte[] generateIVCore() {
    return generateAesIVNative();
  }

  public String generateIVCore(ExportType exportType)
    throws UnsupportedEncodingException {
    byte[] bytes = generateIVCore();
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }
}
