package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Utf8;

public class Utf8Reader extends ByteArrayReader {
  private byte[] _source;

  public Utf8Reader(String text) {
    _source = new Utf8().decode(text);
  }

  @Override
  public byte[] getSource() {
    return _source;
  }
}
