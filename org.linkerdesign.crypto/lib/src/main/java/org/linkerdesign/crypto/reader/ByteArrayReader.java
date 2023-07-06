package org.linkerdesign.crypto.reader;

import java.util.Arrays;

import org.linkerdesign.crypto.abstraction.Reader;

public abstract class ByteArrayReader implements Reader {
  private long _readedLength;

  public long getReadedLength() {
    return _readedLength;
  }

  protected void setReadedLength(long length) {
    _readedLength = length;
  }

  public abstract byte[] getSource();

  public long getLength() {
    return getSource().length;
  }

  public long getAvailable() {
    return getLength() - _readedLength;
  }

  public byte[] read(int length) {
    int size = (int)Math.min(length, getAvailable());
    if (size == 0) return null;
    return Arrays.copyOfRange(getSource(), (int)_readedLength, (int)(_readedLength += size));
  }
}
