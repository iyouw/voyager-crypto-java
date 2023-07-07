package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Base64;

/**
 * base64 reader
 */
public class Base64Reader extends ByteArrayReader {
  private byte[] _source;

  /**
   * base64 constructor
   * @param text text
   */
  public Base64Reader(String text) {
    _source = new Base64().decode(text);
  }

  /**
   * source
   */
  @Override
  public byte[] getSource() {
    return _source;
  }
}
