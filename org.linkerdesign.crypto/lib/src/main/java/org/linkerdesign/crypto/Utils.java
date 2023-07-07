package org.linkerdesign.crypto;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

class Utils {
  public static boolean isPosixCompliant() {
    try {
      return FileSystems.getDefault()
        .supportedFileAttributeViews()
        .contains("posix");
    } catch (Exception e) {
      return false;
    }
  }

  public static File createTempDirectory(String name) throws IOException {
    String tempDir = System.getProperty("java.io.tmpdir");
    File dir = new File(tempDir, name + System.nanoTime());
    if (!dir.mkdir())
      throw new IOException("Failed to create temp dir" + dir.getName());
    return dir;
  }

  public static String joinResourcePath(String path, String fileName) {
    if (!path.endsWith("/")) {
      path += "/";
    }
    return path + fileName;
  }
}
