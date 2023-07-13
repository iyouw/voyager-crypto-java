package org.linkerdesign.crypto.reader;

import java.io.InputStream;

import org.linkerdesign.crypto.abstraction.EncodingType;
import org.linkerdesign.crypto.abstraction.Reader;

/**
 * reader strategy
 */
public class ReaderStrategy implements Reader {
  private Reader _reader;

  /**
   * reader strategy constructor
   * @param text text
   * @param exportType encoding
   */
  public ReaderStrategy(String text, EncodingType exportType) {
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
        _reader = new Base64Reader(text);
    }
  }

  /**
   * reader strategy constructor
   * @param stream stream
   */
  public ReaderStrategy(InputStream stream) {
    _reader = new StreamReader(stream);
  }

  /**
   * reader strategy constructor
   * @param bytes bytes
   */
  public ReaderStrategy(byte[] bytes) {
    _reader = new RawReader(bytes);
  }

  /**
   * read data
   */
  public byte[] read(int length) {
    return _reader.read(length);
  }
}
