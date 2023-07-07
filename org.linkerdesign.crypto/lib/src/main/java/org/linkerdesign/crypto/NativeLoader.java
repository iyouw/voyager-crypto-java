package org.linkerdesign.crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NativeLoader {
  public static String TEMPORARY_DIR_NAME = "org.linkerdesign.crypto.lib";

  private static File tempDir;

  private String tempDirName;


  private Map<OsType, List<String>> libraries;

  public NativeLoader() {
    this(TEMPORARY_DIR_NAME, new HashMap<OsType, List<String>>());
  }

  public NativeLoader(String tempDirName, Map<OsType, List<String>> libraries) {
    this.tempDirName = tempDirName;
    this.libraries = libraries;
  }

  public void addLibrary(OsType type, String path, List<String> libraryNames) {
    if (!libraries.containsKey(type)) {
      libraries.put(type, new ArrayList<String>());
    }
    List<String> list =  libraries.get(type);
    for(String libraryName : libraryNames) {
      libraryName = type.resolveLibrayName(libraryName);
      String libraryPath = Utils.joinResourcePath(path, libraryName);
      if (!list.contains(libraryPath)) list.add(libraryPath);
    }
  }

  public void addWindowsLibraries(String path, List<String> libraryNames) {
    addLibrary(OsType.WINDOWS, path, libraryNames);
  }

  public void addLinuxLibraries(String path, List<String> libraryNames) {
    addLibrary(OsType.LINUX, path, libraryNames);
  }

  public void addMacLibraries(String path, List<String> libraryNames) {
    addLibrary(OsType.MACOS, path, libraryNames);
  }

  public void loadFromJar() throws IOException {
    OsType osType = OS.get();
    List<String> libs = libraries.get(osType);
    if (null == libs) return;
    for(String lib : libs) {
      loadFromJar(lib);
    }
  }

  public void loadFromJar(String path) throws IOException {
    if (null == path || !path.startsWith("/")) {
      throw new IllegalArgumentException("The path has to be absolute (start with '/').");
    }
    
    if (tempDir == null) {
      tempDir = Utils.createTempDirectory(tempDirName);
      tempDir.deleteOnExit();
    }

    String[] parts = path.split("/");
    String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

    // Check if the filename is okay
    if (filename == null) {
      throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
    }

    File temp = new File(tempDir, filename);

    try (InputStream is = NativeLoader.class.getResourceAsStream(path)) {
      Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      temp.delete();
      throw e;
    } catch (NullPointerException e) {
      temp.delete();
      throw new FileNotFoundException("File " + path + "was not found inside jar");
    }

    try {
      System.load(temp.getAbsolutePath());
    } finally {
      if(Utils.isPosixCompliant()) {
        temp.delete();
      } else {
        temp.deleteOnExit();
      }
    }
  }
}
