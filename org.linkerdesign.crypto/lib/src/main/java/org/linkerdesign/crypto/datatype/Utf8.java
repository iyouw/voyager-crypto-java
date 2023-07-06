package org.linkerdesign.crypto.datatype;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.BinaryEncoder;

public class Utf8 implements BinaryEncoder, BinaryDecoder {
  public String encode(byte[] bytes) {
    try {
      return new String(bytes, "UTF-8");
    } catch (Exception e) {
      return null;
    }
  }

  public byte[] decode(String text) {
    try {
      return text.getBytes("UTF-8");
    } catch (Exception e) {
      return null;
    }
  }
}
