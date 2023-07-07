package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * sha384
 */
public class Sha384 extends MdBase {
  @Override
  protected MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA384;
  }
}