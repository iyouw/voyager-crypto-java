package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * sha512
 */
public class Sha512 extends MdBase {
  @Override
  protected MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA512;
  }
}