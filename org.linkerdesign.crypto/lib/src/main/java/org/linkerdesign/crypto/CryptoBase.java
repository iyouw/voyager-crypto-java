package org.linkerdesign.crypto;

import org.linkerdesign.crypto.abstraction.AesKeySize;
import org.linkerdesign.crypto.abstraction.AesMode;
import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class CryptoBase {
  public static final int DEFAULT_BUFFER_SIZE = 8092;

  protected byte[] digestNative(int bufferLength, MdAlgorithm algorithm, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.digest(bufferLength, algorithm.getValue());
  }

  protected byte[] generateAesKeyNative(AesKeySize keySize) {
    Native nat = new Native();
    return nat.generateAesKey(keySize.getValue());
  }

  protected byte[] generateAesIVNative() {
    Native nat = new Native();
    return nat.generateAesIV();
  }

  protected byte[] aesEncryptNative(int bufferLength, byte[] key, byte[] iv, AesMode mode, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.aesEncrypt(bufferLength, key, iv, mode.getValue());
  }

  protected byte[] aesDecryptNative(int bufferLength, byte[] key, byte[] iv, AesMode mode, ReadCallback readCallback) {
    Native nat = new Native(readCallback);
    return nat.aesDecrypt(bufferLength, key, iv, mode.getValue());
  }
}
