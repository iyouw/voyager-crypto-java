package org.linkerdesign.crypto.abstraction;

/**
 * binary decoder
 */
public interface BinaryDecoder {
  /**
   * decode string to byte array
   * @param text the text
   * @return result
   */
  byte[] decode(String text);
}