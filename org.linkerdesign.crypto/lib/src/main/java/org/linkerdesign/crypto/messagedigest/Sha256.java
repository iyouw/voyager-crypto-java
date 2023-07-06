package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class Sha256 extends MdBase {
  @Override
  public MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA256;
  }
}