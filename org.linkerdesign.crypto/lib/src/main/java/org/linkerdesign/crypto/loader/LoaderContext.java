package org.linkerdesign.crypto.loader;

import java.io.File;
import java.util.List;

/**
 * LoaderContext class
 */
public class LoaderContext {
  private File _tempDir;
  private List<String> _libraries;

  /**
   * constructor
   * @param tempDir temp dir
   * @param libraries libraries
   */
  public LoaderContext(File tempDir, List<String> libraries) {
    _tempDir = tempDir;
    _libraries = libraries;
  }

  /**
   * get temp dir
   * @return temp dir
   */
  public File getTempDir() {
    return _tempDir;
  }

  /**
   * get libraries
   * @return libraries
   */
  public List<String> getLibraries() {
    return _libraries;
  }
}
