package org.linkerdesign.crypto.abstraction;

/**
 * message digest algorithm
 */
public enum MdAlgorithm {
  /**
   * sha1
   */
  SHA1(1),

  /**
   * sha256
   */
  SHA256(2),

  /**
   * sha384
   */
  SHA384(3),

  /**
   * sha512
   */
  SHA512(4),

  /**
   * md5
   */
  MD5(5),

  /**
   * md5_sha1
   */
  MD5_SHA1(6);
  
  private int _value;

  /**
   * get value
   * @return value
   */
  public int getValue() {
    return _value;
  }

  MdAlgorithm(int value) {
    _value = value;
  }
}
