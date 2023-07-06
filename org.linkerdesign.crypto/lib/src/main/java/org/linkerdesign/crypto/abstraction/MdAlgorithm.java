package org.linkerdesign.crypto.abstraction;

public enum MdAlgorithm {

  SHA1(1),

  SHA256(2),

  SHA384(3),

  SHA512(4),

  MD5(5),

  MD5_SHA1(6);
  
  private int _value;

  public int getValue() {
    return _value;
  }

  MdAlgorithm(int value) {
    _value = value;
  }
}
