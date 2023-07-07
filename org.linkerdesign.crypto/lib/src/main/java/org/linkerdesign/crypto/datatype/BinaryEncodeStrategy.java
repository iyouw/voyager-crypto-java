package org.linkerdesign.crypto.datatype;

import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.BinaryEncoder;
import org.linkerdesign.crypto.abstraction.ExportType;

/**
 * binary encode strategy
 */
public class BinaryEncodeStrategy implements BinaryEncoder {
  private final BinaryEncoder _encoder;

  /**
   * the binary encode strategy constructor
   * @param exportType encoding
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public BinaryEncodeStrategy(ExportType exportType)
    throws UnsupportedEncodingException {
    switch(exportType) {
      case Base64:
        _encoder = new Base64();
        break;
      case Hex:
        _encoder = new Hex();
        break;
      case UTF8:
        _encoder = new Utf8();
        break;
      default:
        throw new UnsupportedEncodingException();
    }
  }

  /**
   * encode
   */
  public String encode(byte[] bytes) {
    return _encoder.encode(bytes);
  }
}
