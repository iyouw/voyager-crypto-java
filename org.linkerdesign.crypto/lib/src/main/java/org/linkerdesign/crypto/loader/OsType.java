package org.linkerdesign.crypto.loader;

enum OsType {
  /**
   * window os type
   */
  WINDOWS {
    @Override
    public boolean isWindows() {
      return true;
    }

    @Override
    String getSuffix() {
      return ".dll";
    }
  },
  /**
   * linux os type
   */
  LINUX {
    @Override
    public boolean isLinux() {
      return true;
    }

    @Override
    String getPrefix() {
      return "lib";
    }

    @Override
    String getSuffix() {
      return ".so";
    }
  },
  /**
   * mac os type
   */
  MACOS {
    @Override
    public boolean isMacOS() {
      return true;
    }

    @Override
    String getPrefix() {
      return "lib";
    }

    @Override
    String getSuffix() {
      return ".dylib";
    }
  },
  /**
   * unknow os type
   */
  UNKNOW;

  String getPrefix() { return ""; }

  String getSuffix() { return ""; }
  /**
   * whether os type is windows
   * @return boolean
   */
  public boolean isWindows() { return false; }

  /**
   * whether os type is linux
   * @return boolean
   */
  public boolean isLinux() { return false; }

  /**
   * whether os type is mac
   * @return boolean
   */
  public boolean isMacOS() { return false; }

  /**
   * resolve library name for the os convention
   * @param name library name
   * @return library name
   */
  public String resolveLibrayName(String name) {
    if (null == name) return name;
    String prefix = getPrefix();
    String suffix = getSuffix();
    if (!name.startsWith(prefix)) {
      name = prefix + name;
    }
    if (!name.endsWith(suffix)) {
      name += suffix;
    }
    return name;
  }
}
