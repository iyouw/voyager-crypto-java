package org.linkerdesign.crypto.datatype;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.EncodingType;

/**
 * binary decode strategy 
 */
public class BinaryDecodeStrategy implements BinaryDecoder {
  private final BinaryDecoder _decoder;

  /**
   * the binary decode strategy constructor
   * @param exportType encoding type
   */
  public BinaryDecodeStrategy(EncodingType exportType) {
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
        _decoder = new Base64();
    }
  }

  /**
   * decode
   */
  public byte[] decode(String text) {
    return _decoder.decode(text);
  }
}
