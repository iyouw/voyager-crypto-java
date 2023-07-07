package org.linkerdesign.crypto.abstraction;

/**
 * reader
 */
public interface Reader {
  /**
   * the data length of reader
   * @return length
   */
  long getLength();

  /**
   * read data 
   * @param length the length of data to read
   * @return data
   */
  byte[] read(int length);
}
