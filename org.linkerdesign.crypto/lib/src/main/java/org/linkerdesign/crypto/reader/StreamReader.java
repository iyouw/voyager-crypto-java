package org.linkerdesign.crypto.reader;

import java.io.InputStream;

import org.linkerdesign.crypto.abstraction.Reader;

public class StreamReader implements Reader {
  private InputStream _stream;

  private long _length;

  public StreamReader(InputStream stream) {
    _stream = stream;
    _length = getAvailable();
  }

  public InputStream getStream() {
    return _stream;
  }

  public long getLength() {
    return _length;
  }

  public long getAvailable() {
    try {
      return _stream.available();
    } catch (Exception e) {
      return 0;
    }
  }

  public byte[] read(int length) {
    var size = Math.min(length, (int)getAvailable());
    if (size == 0) return null;
    var res = new byte[size];
    try {
      _stream.readNBytes(res, 0, size);
      return res;
    } catch (Exception e) {
      return null;
    }
  }
}
