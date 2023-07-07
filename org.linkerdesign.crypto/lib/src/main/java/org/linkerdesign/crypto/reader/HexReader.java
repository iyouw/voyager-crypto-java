package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Hex;

/**
 * hex reader
 */
public class HexReader extends ByteArrayReader {
  private byte[] _source;

  /**
   * hex reader constructor
   * @param text text
   */
  public HexReader(String text) {
    _source = new Hex().decode(text);
  }

  /**
   * source
   */
  @Override
  public byte[] getSource() {
    return _source;
  }
}
