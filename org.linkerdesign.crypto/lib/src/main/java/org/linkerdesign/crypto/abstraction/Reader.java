package org.linkerdesign.crypto.abstraction;

/**
 * reader
 */
public interface Reader {
  /**
   * read data 
   * @param length the length of data to read
   * @return data
   */
  byte[] read(int length);
}
