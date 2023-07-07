package org.linkerdesign.crypto.abstraction;

/**
 * aes key size
 */
public enum AesKeySize {
  /**
   * 128 bit key
   */
  KS128(128),

  /**
   * 192 bit key
   */
  KS192(192),
  
  /**
   * 256 bit key
   */
  KS256(256);

  private int _value;

  /**
   * get value
   * @return value
   */
  public int getValue() {
    return _value;
  }

  private AesKeySize(int value) {
    _value = value;
  }
}
