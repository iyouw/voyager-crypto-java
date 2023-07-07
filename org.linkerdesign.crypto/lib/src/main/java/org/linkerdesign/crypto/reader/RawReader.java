package org.linkerdesign.crypto.reader;

/**
 * raw reader
 */
public class RawReader extends ByteArrayReader {
  private byte[] _source;

  /**
   * raw reader constructor
   * @param bytes bytes
   */
  public RawReader(byte[] bytes) {
    _source = bytes;
  }

  /**
   * source
   */
  @Override
  public byte[] getSource() {
    return _source;
  }
}
