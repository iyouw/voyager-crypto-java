package org.linkerdesign.crypto.reader;

import org.linkerdesign.crypto.datatype.Base64;

public class Base64Reader extends ByteArrayReader {
  private byte[] _source;

  public Base64Reader(String text) {
    _source = new Base64().decode(text);
  }

  @Override
  public byte[] getSource() {
    return _source;
  }
}
