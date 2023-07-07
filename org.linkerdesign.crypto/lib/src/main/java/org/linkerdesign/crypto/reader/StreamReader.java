package org.linkerdesign.crypto.reader;

import java.io.InputStream;

import org.linkerdesign.crypto.abstraction.Reader;

/**
 * stream reader
 */
public class StreamReader implements Reader {
  private InputStream _stream;

  private long _length;

  /**
   * stream reader constructor
   * @param stream stream
   */
  public StreamReader(InputStream stream) {
    _stream = stream;
    _length = getAvailable();
  }

  /**
   * get stream
   * @return stream
   */
  public InputStream getStream() {
    return _stream;
  }

  /**
   * get stream length
   */
  public long getLength() {
    return _length;
  }

  /**
   * get remaining length of stream data
   * @return remaing length
   */
  public long getAvailable() {
    try {
      return _stream.available();
    } catch (Exception e) {
      return 0;
    }
  }

  /**
   * read data
   */
  public byte[] read(int length) {
    int size = Math.min(length, (int)getAvailable());
    if (size == 0) return null;
    byte[] res = new byte[size];
    try {
      _stream.readNBytes(res, 0, size);
      return res;
    } catch (Exception e) {
      return null;
    }
  }
}
