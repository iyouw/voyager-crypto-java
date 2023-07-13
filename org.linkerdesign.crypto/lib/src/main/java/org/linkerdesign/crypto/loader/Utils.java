package org.linkerdesign.crypto.loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * utils class
 */
class Utils {
  /**
   * whether the default file system is posix compliant
   * @return boolean
   */
  public static boolean isPosixCompliant() {
    try {
      return FileSystems.getDefault()
        .supportedFileAttributeViews()
        .contains("posix");
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * create temporary dir
   * @param name the name of the temporary dir
   * @return the temporary dir
   * @throws IOException io exception
   */
  public static File createTempDirectory(String name) throws IOException {
    String tempDir = System.getProperty("java.io.tmpdir");
    File dir = new File(tempDir, name + System.nanoTime());
    if (!dir.mkdir())
      throw new IOException("Failed to create temp dir" + dir.getName());
    return dir;
  }

  /**
   * join library path
   * @param path the base path
   * @param fileName library name
   * @return library path
   */
  public static String joinResourcePath(String path, String fileName) {
    if (!path.endsWith("/")) {
      path += "/";
    }
    return path + fileName;
  }
}
