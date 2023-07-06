package org.linkerdesign.crypto;

import java.util.List;

public class Native {
  private ReadCallback _readCallback;

  private WriteCallback _writeCallback;

  public Native() {}

  public Native(ReadCallback callback) {
    _readCallback = callback;
  }

  public Native(WriteCallback callback) {
    _writeCallback = callback;
  }

  public Native(ReadCallback readCallback, WriteCallback writeCallback) {
    _readCallback = readCallback;
    _writeCallback = writeCallback;
  }

  public byte[] readCallback(int length) {
    if (_readCallback == null) return null;
    return _readCallback.read(length);
  }

  public void writeCallback(byte[] bytes) {
    if (_writeCallback != null) {
      _writeCallback.write(bytes);
    }
  }

  public native byte[] digest(int bufferLength, int algorithm);

  public native byte[] generateAesKey(int length);

  public native byte[] generateAesIV();

  public native byte[] aesEncrypt(int bufferLength, byte[] key, byte[] iv, int aesMode);

  public native byte[] aesDecrypt(int bufferLength, byte[] key, byte[] iv, int aesMode);

  static {
    try {
      var loader = new NativeLoader();
      loader.addWindowsLibraries("/native/win", List.of("libcrypto-3-x64.dll","kcrypto.dll"));
      loader.addLinuxLibraries("/native/linux", List.of("libkcrypto.so"));
      loader.loadFromJar();
    } catch (Exception e) {
      System.err.println(e);
      System.exit(-1);
    }
  }
}
