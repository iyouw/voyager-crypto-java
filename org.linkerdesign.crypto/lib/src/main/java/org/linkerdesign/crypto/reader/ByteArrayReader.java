package org.linkerdesign.crypto.reader;

import java.util.Arrays;

import org.linkerdesign.crypto.abstraction.Reader;

/**
 * byte array reader
 */
public abstract class ByteArrayReader implements Reader {
  private long _readedLength;

  /**
   * the readed length of this reader
   * @return readed length
   */
  public long getReadedLength() {
    return _readedLength;
  }

  /**
   * set readed length
   * @param length readed length
   */
  protected void setReadedLength(long length) {
    _readedLength = length;
  }

  /**
   * the data source of the reader
   * @return data source
   */
  public abstract byte[] getSource();

  /**
   * the length of the reader
   */
  public long getLength() {
    return getSource().length;
  }

  /**
   * the remaining data length of the reader
   * @return remaining data length
   */
  public long getAvailable() {
    return getLength() - _readedLength;
  }

  /**
   * read data from source
   */
  public byte[] read(int length) {
    int size = (int)Math.min(length, getAvailable());
    if (size == 0) return null;
    return Arrays.copyOfRange(getSource(), (int)_readedLength, (int)(_readedLength += size));
  }
}
