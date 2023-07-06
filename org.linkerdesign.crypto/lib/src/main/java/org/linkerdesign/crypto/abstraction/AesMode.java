package org.linkerdesign.crypto.abstraction;

public enum AesMode {
  CTR(1),

  CBC(2);
  
  private int _value;

  public int getValue() {
    return _value;
  }

  private AesMode(int value) {
    _value = value;
  }
}
