package org.linkerdesign.crypto.abstraction;

public interface Reader {
  long getLength();
  byte[] read(int length);
}
