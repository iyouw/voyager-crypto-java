package org.linkerdesign.crypto.loader;

enum OsType {
  /**
   * window os type
   */
  WINDOWS (1) {
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
  LINUX (2) {
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
  MACOS (3) {
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
  UNKNOW (186);

  private Integer _value;

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

  /**
   * get os type value
   * @return os type value
   */
  public Integer getValue() { return _value; }

  private OsType(Integer value) {
    _value = value;
  }
}
