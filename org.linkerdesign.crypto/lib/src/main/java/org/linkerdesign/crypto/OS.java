package org.linkerdesign.crypto;

public class OS {
  public static final String WINDOWS = "Windows";
  public static final String LINUX = "Linux";
  public static final String MACOS = "Mac";

  public static OsType get() {
    var osName = System.getProperty("os.name");
    var res = OsType.UNKNOW;
    if (null != osName) {
      if (osName.startsWith(WINDOWS)) {
        res = OsType.WINDOWS;
      } else if (osName.startsWith(MACOS)) {
        res = OsType.MACOS;
      } else {
        res = OsType.LINUX;
      }
    }
    return res;
  }

  public static boolean isWindows() {
    return OsType.WINDOWS == get();
  }

  public static boolean isLinux() {
    return OsType.LINUX == get();
  }

  public static boolean isMac() {
    return OsType.MACOS == get();
  }

  public static String resolveLibraryName(OsType type, String libraryName) {
    return type.resolveLibrayName(libraryName);
  }
}
