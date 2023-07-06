package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

public class Sha384 extends MdBase {
  @Override
  public MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA384;
  }
}