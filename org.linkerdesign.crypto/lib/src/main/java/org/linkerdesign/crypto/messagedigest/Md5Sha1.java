package org.linkerdesign.crypto.messagedigest;

import org.linkerdesign.crypto.abstraction.MdAlgorithm;

/**
 * md5_sha1
 */
public class Md5Sha1 extends MdBase {
  @Override
  protected MdAlgorithm getAlgorithm() {
    return MdAlgorithm.MD5_SHA1;
  }
}