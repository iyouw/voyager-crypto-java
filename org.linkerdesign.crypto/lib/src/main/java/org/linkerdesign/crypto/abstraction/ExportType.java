package org.linkerdesign.crypto.abstraction;

/**
 * string encoding type
 */
public enum ExportType {
  /**
   * base64 encoding
   */
  Base64(1),

  /**
   * hex encoding
   */
  Hex(2),

  /**
   * utf8 encoding
   */
  UTF8(3);
  
  private int _value;

  /**
   * get value 
   * @return value
   */
  public int getValue() {
    return _value;
  }

  ExportType(int value) {
    _value = value;
  }
}
