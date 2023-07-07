package org.linkerdesign.crypto;

/**
 * WriteCallback of native
 */
public interface WriteCallback {
  /**
   * native write data callback
   * @param bytes bytes
   */
  void write(byte[] bytes);
}
