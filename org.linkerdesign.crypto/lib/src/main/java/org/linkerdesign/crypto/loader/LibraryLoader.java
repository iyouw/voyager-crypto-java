package org.linkerdesign.crypto.loader;

/**
 * LibraryLoader interface
 */
public interface LibraryLoader {
  /**
   * load libraries
   * @param context loader context
   * @return whether load libraries successful
   */
  boolean load(LoaderContext context);
}
