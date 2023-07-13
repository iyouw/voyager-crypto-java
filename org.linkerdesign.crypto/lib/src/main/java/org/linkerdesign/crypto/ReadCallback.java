package org.linkerdesign.crypto;

/**
 * Readcallback of native
 */
@FunctionalInterface
public interface ReadCallback {
  /**
   * native data read callback
   * @param length length
   * @return data
   */
  byte[] read(int length);
}
