package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class Md5Sha1 extends MdBase {
  @Override
  public MdAlgorithm getAlgorithm() {
    return MdAlgorithm.MD5_SHA1;
  }
}