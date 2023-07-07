package org.linkerdesign.crypto.abstraction;

/**
 * binary encoder
 */
public interface BinaryEncoder {
  /**
   * encode byte arrary to string
   * @param bytes bytes
   * @return result
   */
  String encode(byte[] bytes);
}
