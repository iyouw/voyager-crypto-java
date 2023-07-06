package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class Sha1 extends MdBase {
  @Override
  public MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA1;
  }
}