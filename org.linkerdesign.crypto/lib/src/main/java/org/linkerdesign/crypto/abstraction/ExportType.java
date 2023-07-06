package org.linkerdesign.crypto.abstraction;

public enum ExportType {
  Base64(1),

  Hex(2),

  UTF8(3);
  
  private int _value;

  public int getValue() {
    return _value;
  }

  ExportType(int value) {
    _value = value;
  }
}
