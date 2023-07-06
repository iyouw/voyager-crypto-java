package org.linkerdesign.crypto;

public enum OsType {
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

  UNKNOW (186);

  private Integer _value;

  String getPrefix() { return ""; }

  String getSuffix() { return ""; }

  public boolean isWindows() { return false; }

  public boolean isLinux() { return false; }

  public boolean isMacOS() { return false; }

  public String resolveLibrayName(String name) {
    if (null == name) return name;
    var prefix = getPrefix();
    var suffix = getSuffix();
    if (!name.startsWith(prefix)) {
      name = prefix + name;
    }
    if (!name.endsWith(suffix)) {
      name += suffix;
    }
    return name;
  }

  public Integer getValue() { return _value; }

  private OsType(Integer value) {
    _value = value;
  }
}
