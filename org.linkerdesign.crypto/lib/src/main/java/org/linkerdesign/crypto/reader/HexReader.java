package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Hex;

public class HexReader extends ByteArrayReader {
  private byte[] _source;

  public HexReader(String text) {
    _source = new Hex().decode(text);
  }

  @Override
  public byte[] getSource() {
    return _source;
  }
}
