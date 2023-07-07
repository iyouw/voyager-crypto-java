package org.linkerdesign.crypto.datatype;

import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.ExportType;

/**
 * binary decode strategy 
 */
public class BinaryDecodeStrategy implements BinaryDecoder {
  private final BinaryDecoder _decoder;

  /**
   * the binary decode strategy constructor
   * @param exportType encoding type
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public BinaryDecodeStrategy(ExportType exportType) 
    throws UnsupportedEncodingException {
    switch(exportType) {
      case Base64:
        _decoder = new Base64();
        break;
      case Hex:
        _decoder = new Hex();
        break;
      case UTF8:
        _decoder = new Utf8();
        break;
      default:
        throw new UnsupportedEncodingException();
    }
  }

  /**
   * decode
   */
  public byte[] decode(String text) {
    return _decoder.decode(text);
  }
}
