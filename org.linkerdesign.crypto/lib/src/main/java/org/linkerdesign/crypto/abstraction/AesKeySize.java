package org.linkerdesign.crypto.abstraction;

public enum AesKeySize {
  KS128(128),

  KS192(192),
  
  KS256(256);

  private int _value;

  public int getValue() {
    return _value;
  }

  private AesKeySize(int value) {
    _value = value;
  }
}
