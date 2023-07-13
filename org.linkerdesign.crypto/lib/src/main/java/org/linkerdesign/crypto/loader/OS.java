package org.linkerdesign.crypto.loader;

/**
 * os class
 */
class OS {
  /**
   * window os name
   */
  public static final String WINDOWS = "Windows";
  /**
   * linux os name
   */
  public static final String LINUX = "Linux";
  /**
   * mac os name
   */
  public static final String MACOS = "Mac";

  /**
   * get the current os
   * @return the current os type
   */
  public static OsType get() {
    String osName = System.getProperty("os.name");
    OsType res = OsType.UNKNOW;
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

  /**
   * whether current is window os
   * @return
   */
  public static boolean isWindows() {
    return OsType.WINDOWS == get();
  }

  /**
   * whether current is linux os
   * @return
   */
  public static boolean isLinux() {
    return OsType.LINUX == get();
  }

  /**
   * whether current is mac os
   * @return
   */
  public static boolean isMac() {
    return OsType.MACOS == get();
  }

  /**
   * resolve native library name for os convention
   * @param type the os type
   * @param libraryName library name
   * @return library name
   */
  public static String resolveLibraryName(OsType type, String libraryName) {
    return type.resolveLibrayName(libraryName);
  }
}
