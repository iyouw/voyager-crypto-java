package org.linkerdesign.crypto;

import org.linkerdesign.crypto.loader.NativeLoader;

import java.util.List;

class Native {
  public native byte[] digest(int bufferLength, int algorithm, ReadCallback readCallback);

  public native byte[] generateAesKey(int length);

  public native byte[] generateAesIV();

  public native int aesEncrypt(int bufferLength, byte[] key, byte[] iv, int aesMode, ReadCallback readCallback, WriteCallback writeCallback);

  public native int aesDecrypt(int bufferLength, byte[] key, byte[] iv, int aesMode, ReadCallback readCallback, WriteCallback writeCallback);

  static {
    try {
      NativeLoader loader = new NativeLoader();
      loader.addWindowsLibraries("/native/win", List.of("libcrypto-3-x64.dll","kcrypto.dll"));
      loader.addLinuxLibraries("/native/linux", List.of("libcrypto.so.3","libkcrypto.so"));
      loader.loadFromJar();
    } catch (Exception e) {
      System.err.println(e);
      System.exit(-1);
    }
  }
}
