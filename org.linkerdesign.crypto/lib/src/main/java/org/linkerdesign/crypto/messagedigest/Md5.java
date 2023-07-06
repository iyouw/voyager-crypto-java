package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class Md5 extends MdBase {
  @Override
  public MdAlgorithm getAlgorithm() {
    return MdAlgorithm.MD5;
  }
}
