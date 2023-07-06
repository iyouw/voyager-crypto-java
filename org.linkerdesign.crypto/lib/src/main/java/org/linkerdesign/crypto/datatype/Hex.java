package org.linkerdesign.crypto.datatype;

import org.linkerdesign.crypto.abstraction.BinaryDecoder;
import org.linkerdesign.crypto.abstraction.BinaryEncoder;

public class Hex implements BinaryEncoder, BinaryDecoder {
  public String encode(byte[] bytes) {
    StringBuilder sb = new StringBuilder(bytes.length * 2);
    for (byte b : bytes) {
      sb.append((String.format("%02x", b)));
    }
    return sb.toString().toLowerCase();
  }

  public byte[] decode(String text) {
    byte[] bytes = new byte[text.length() / 2];
    for (int i = 0; i < bytes.length; i ++) {
      int index = i * 2;
      int val = Integer.parseInt(text.substring(index, index + 2), 16);
      bytes[i] = (byte)val;
    }
    return bytes;
  }
}
