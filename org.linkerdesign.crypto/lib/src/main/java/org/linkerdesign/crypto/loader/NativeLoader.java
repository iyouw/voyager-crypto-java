package org.linkerdesign.crypto.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Native Loader class
 */
public class NativeLoader {
  /**
   *  temporary dir
   */
  public static String TEMPORARY_DIR_NAME = "org.linkerdesign.crypto.lib";

  private static File _tempDir;

  private String _tempDirName;

  private Map<OsType, List<String>> _libraries;

  private List<LibraryLoader> _loaders;

  /**
   * constructor
   */
  public NativeLoader() {
    this(new ArrayList<>(), TEMPORARY_DIR_NAME, new HashMap<OsType, List<String>>());
    addLoader(new JarResourceLibraryLoader());
  }

  /**
   * constructor
   * @param loaders library loaders
   * @param tempDirName temporary dir name
   * @param libraries native libraries
   */
  public NativeLoader(
    List<LibraryLoader> loaders,
    String tempDirName, 
    Map<OsType, List<String>> libraries) {
    _loaders = loaders;
    _tempDirName = tempDirName;
    _libraries = libraries;
  }

  /**
   * add native library
   * @param type os type
   * @param path base path to the library
   * @param libraryNames list of library names
   * @return native loader
   */
  public NativeLoader addLibrary(OsType type, String path, List<String> libraryNames) {
    if (!_libraries.containsKey(type)) {
      _libraries.put(type, new ArrayList<String>());
    }
    List<String> list =  _libraries.get(type);
    for(String libraryName : libraryNames) {
      String libraryPath = joinPath(path, libraryName);
      if (!list.contains(libraryPath)) list.add(libraryPath);
    }
    return this;
  }

  /**
   * add windows native libraries
   * @param path the base path
   * @param libraryNames library names
   * @return native loader
   */
  public NativeLoader addWindowsLibraries(String path, List<String> libraryNames) {
    return addLibrary(OsType.WINDOWS, path, libraryNames);
  }

  /**
   * add linux native libraries
   * @param path the base path
   * @param libraryNames library names
   * @return native loader
   */
  public NativeLoader addLinuxLibraries(String path, List<String> libraryNames) {
    return addLibrary(OsType.LINUX, path, libraryNames);
  }

  /**
   * add mac native libraries
   * @param path the base path
   * @param libraryNames library names
   * @return native loader
   */
  public NativeLoader addMacLibraries(String path, List<String> libraryNames) {
    return addLibrary(OsType.MACOS, path, libraryNames);
  }

  /**
   * add loader
   * @param loader library loader
   * @return native loader
   */
  public NativeLoader addLoader(LibraryLoader loader) {
    if (!_loaders.contains(loader)) {
      _loaders.add(loader);
    }
    return this;
  }

  /**
   * remove loader
   * @param loader loader
   * @return native loader
   */
  public NativeLoader removeLoader(LibraryLoader loader) {
    _loaders.remove(loader);
    return this;
  }

  /**
   * clear loaders
   * @return native loader
   */
  public NativeLoader clearLoader() {
    _loaders.clear();
    return this;
  }

  /**
   * load native library from jar file
   * @return whether load successful.
   */
  public boolean load() {
    try {
      LoaderContext context = getLoaderContext();
      for (LibraryLoader loader : _loaders) {
        if (!loader.load(context)) return false;
      }
    } catch (Exception e) {
      System.err.println(e);
      return false;
    }
    return true;
  }

  /**
   * construct loader context
   * @return loader context
   * @throws IOException io exception(Could not create temp dir)
   * @throws Exception exception (Could not found libraries for current os)
   */
  private LoaderContext getLoaderContext() 
    throws IOException, Exception {
    File tempDir =  ensureTempDirExist();
    List<String> libraries = getLibraries();
    return new LoaderContext(tempDir, libraries);
  }

  /**
   * create temporary dir if not exist
   * @return temporary dir
   * @throws IOException io exception
   */
  private File ensureTempDirExist() throws IOException {
    if (null == _tempDir) {
      _tempDir = Utils.createTempDirectory(_tempDirName);
      _tempDir.deleteOnExit();
    }
    return _tempDir;
  }

  /**
   * get libraries for current os
   * @return libraries for current os
   * @throws Exception exception
   */
  private List<String> getLibraries() throws Exception {
    OsType osType = OS.get();
    List<String> libs = _libraries.get(osType);
    if (null == libs) 
      throw new Exception("not found libraries for the" + osType.name());
    return libs;
  }

  /**
   * join path
   * @param path the base path
   * @param fileName name
   * @return path
   */
  private String joinPath(String path, String fileName) {
    if (!path.endsWith("/")) {
      path += "/";
    }
    return path + fileName;
  }
}
