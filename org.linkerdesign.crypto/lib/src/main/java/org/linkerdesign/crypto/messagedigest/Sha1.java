package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * sha1
 */
public class Sha1 extends MdBase {
  @Override
  protected MdAlgorithm getAlgorithm() {
    return MdAlgorithm.SHA1;
  }
}