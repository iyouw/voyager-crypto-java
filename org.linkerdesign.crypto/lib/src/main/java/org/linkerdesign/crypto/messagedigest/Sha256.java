package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * sha256
 */
public class Sha256 extends MdBase {
  @Override
  protected MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA256;
  }
}