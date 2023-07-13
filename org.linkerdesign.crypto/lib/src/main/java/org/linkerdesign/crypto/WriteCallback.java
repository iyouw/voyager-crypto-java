package org.linkerdesign.crypto;

/**
 * WriteCallback of native
 */
@FunctionalInterface
public interface WriteCallback {
  /**
   * native write data callback
   * @param bytes bytes
   */
  void write(byte[] bytes);
}
