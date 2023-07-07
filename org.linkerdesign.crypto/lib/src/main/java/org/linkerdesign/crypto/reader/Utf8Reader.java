package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Utf8;

/**
 * utf8 reader
 */
public class Utf8Reader extends ByteArrayReader {
  private byte[] _source;

  /**
   * utf8 reader constructor
   * @param text text
   */
  public Utf8Reader(String text) {
    _source = new Utf8().decode(text);
  }

  /**
   * source
   */
  @Override
  public byte[] getSource() {
    return _source;
  }
}
