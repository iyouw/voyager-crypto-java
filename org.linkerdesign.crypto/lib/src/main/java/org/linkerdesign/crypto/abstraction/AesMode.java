package org.linkerdesign.crypto.abstraction;

/**
 * aes mode
 */
public enum AesMode {
  /**
   * ctr mode
   */
  CTR(1),

  /**
   * cbc mode
   */
  CBC(2);
  
  private int _value;

  /**
   * get value
   * @return value
   */
  public int getValue() {
    return _value;
  }

  private AesMode(int value) {
    _value = value;
  }
}
