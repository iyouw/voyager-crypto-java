package org.linkerdesign.crypto.datatype;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.BinaryEncoder;

/**
 * base64 encoder decoder
 */
public class Base64 implements BinaryEncoder, BinaryDecoder {
  /**
   * encode 
   */
  public String encode(byte[] bytes) {
    return java.util.Base64.getEncoder().encodeToString(bytes);
  }

  /**
   * decode
   */
  public byte[] decode(String text) {
    return java.util.Base64.getDecoder().decode(text);
  }
}
