package org.linkerdesign.crypto.symmetricencryptdecrypt;

import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.ExportType;

public class Aes extends AesBase {
  public byte[] encryptCBC(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return encryptCBC(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] encryptCBC(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  public byte[] decryptCBC(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return decryptCBC(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] decryptCBC(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  public byte[] encryptCBCWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException{
    return encryptCBCWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] encryptCBCWithUTF8(String key, String iv, String data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, bufferSize);
  }

  public String encryptCBCWithUTF8(String key, String iv, String data, ExportType exportType) 
    throws UnsupportedEncodingException{
    return encryptCBCWithUTF8(key, iv, data, exportType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String encryptCBCWithUTF8(String key, String iv, String data, ExportType exportType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, exportType, bufferSize);
  }

  public String decryptCBCWithUTF8(String key, String iv, byte[] data) 
    throws UnsupportedEncodingException {
    return decryptCBCWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String decryptCBCWithUTF8(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  public String decryptCBCWithUTF8(String key, String iv, String data, ExportType dataType) 
    throws UnsupportedEncodingException {
    return decryptCBCWithUTF8(key, iv, data, dataType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String decryptCBCWithUTF8(String key, String iv, String data, ExportType dataType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CBC;
    return decryptCore(data, dataType, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  public byte[] encryptCTR(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return encryptCTR(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] encryptCTR(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return encryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  public byte[] decryptCTR(String key, String iv, byte[] data)
    throws UnsupportedEncodingException {
    return decryptCTR(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] decryptCTR(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, key, keyType, iv, ivType, mode, bufferSize);
  }

  public byte[] encryptCTRWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException{
    return encryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public byte[] encryptCTRWithUTF8(String key, String iv, String data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CBC;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, bufferSize);
  }

  public String encryptCTRWithUTF8(String key, String iv, String data, ExportType exportType) 
    throws UnsupportedEncodingException{
    return encryptCTRWithUTF8(key, iv, data, exportType, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String encryptCTRWithUTF8(String key, String iv, String data, ExportType exportType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException{
    AesMode mode = AesMode.CTR;
    return encryptCore(data, ExportType.UTF8, key, keyType, iv, ivType, mode, exportType, bufferSize);
  }

  public String decryptCTRWithUTF8(String key, String iv, byte[] data) 
    throws UnsupportedEncodingException {
    return decryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String decryptCTRWithUTF8(String key, String iv, byte[] data, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  public String decryptCTRWithUTF8(String key, String iv, String data) 
    throws UnsupportedEncodingException {
    return decryptCTRWithUTF8(key, iv, data, ExportType.Base64, ExportType.Base64, ExportType.Base64, DEFAULT_BUFFER_SIZE);
  }

  public String decryptCTRWithUTF8(String key, String iv, String data, ExportType dataType, ExportType keyType, ExportType ivType, int bufferSize) 
    throws UnsupportedEncodingException {
    AesMode mode = AesMode.CTR;
    return decryptCore(data, dataType, key, keyType, iv, ivType, mode, ExportType.UTF8, bufferSize);
  }

  public byte[] generateKey() {
    return generateKeyCore(AesKeySize.KS256);
  }

  public String generateKey(AesKeySize keySize, ExportType exportType)
    throws UnsupportedEncodingException {
    return generateKeyCore(keySize, exportType);
  }

  public byte[] generateIV() {
    return generateIVCore();
  }

  public String generateIV(ExportType exportType) 
    throws UnsupportedEncodingException {
    return generateIVCore(exportType);
  }
}
