package org.linkerdesign.crypto.reader;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.abstraction.Reader;

public class ReaderStrategy implements Reader {
  private Reader _reader;

  public ReaderStrategy(String text, ExportType exportType)
    throws UnsupportedEncodingException {
    switch(exportType) {
      case Base64:
        _reader = new Base64Reader(text);
        break;
      case Hex:
        _reader = new HexReader(text);
        break;
      case UTF8:
        _reader = new Utf8Reader(text);
        break;
      default:
        throw new UnsupportedEncodingException();
    }
  }

  public ReaderStrategy(InputStream stream) {
    _reader = new StreamReader(stream);
  }

  public ReaderStrategy(byte[] bytes) {
    _reader = new RawReader(bytes);
  }

  public long getLength() {
    return _reader.getLength();
  }

  public byte[] read(int length) {
    return _reader.read(length);
  }
}
