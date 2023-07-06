package org.linkerdesign.crypto.reader;

public class RawReader extends ByteArrayReader {
  private byte[] _source;

  public RawReader(byte[] bytes) {
    _source = bytes;
  }

  @Override
  public byte[] getSource() {
    return _source;
  }
}
