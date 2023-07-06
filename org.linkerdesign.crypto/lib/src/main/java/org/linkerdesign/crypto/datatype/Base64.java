package org.linkerdesign.crypto.datatype;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.BinaryEncoder;

public class Base64 implements BinaryEncoder, BinaryDecoder {
  public String encode(byte[] bytes) {
    return java.util.Base64.getEncoder().encodeToString(bytes);
  }

  public byte[] decode(String text) {
    return java.util.Base64.getDecoder().decode(text);
  }
}
