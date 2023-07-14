package org.linkerdesign.crypto.loader;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 *  JarResourceLibraryLoader class 
 */
public class JarResourceLibraryLoader implements LibraryLoader {

  /**
   * load
   */
  @Override
  public boolean load(LoaderContext context) {
    if (context == null) return false;

    File tempDir = context.getTempDir();
    List<String> libraries = context.getLibraries();

    for (String library : libraries) {
      if (!load(library, tempDir)) return false;
    }

    return true;
  }

  /**
   * load the library
   * @param path library path
   * @param tempDir temp dir
   * @return whether load successful
   */
  public boolean load(String path, File tempDir) {
    if (null == path || !path.startsWith("/")) return false;
    
    String[] parts = path.split("/");
    String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

    if (null == filename) return false;
    
    File temp = new File(tempDir, filename);

    try(InputStream is = getClass().getResourceAsStream(path)) {
      Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      temp.delete();
      return false;
    }

    try {
      System.load(temp.getAbsolutePath());
    } catch( Exception e) {
      return false;
    } finally {
      if (Utils.isPosixCompliant()) {
        temp.delete();
      } else {
        temp.deleteOnExit();
      }
    }
    return true;
  }
}
