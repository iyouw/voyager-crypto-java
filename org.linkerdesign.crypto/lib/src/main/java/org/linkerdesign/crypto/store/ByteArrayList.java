package org.linkerdesign.crypto.store;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *  ByteArrayList class
 */
public class ByteArrayList {
  private List<byte[]> _items;

  /**
   * constructor
   */
  public ByteArrayList() {
    _items = new ArrayList<>();
  }

  /**
   * get the size of the list
   * @return the size of the list
   */
  public int size() {
    return _items.size();
  }

  /**
   * get the items of the list
   * @return the items of the list
   */
  public List<byte[]> items() {
    return _items;
  }

  /**
   * add new item to the list
   * @param bytes the item
   * @return byte array list self
   */
  public ByteArrayList add(byte[] bytes) {
    _items.add(bytes);
    return this;
  }

  /**
   * merge all items to a byte array
   * @return the merged byte array
   */
  public byte[] toArray() {
    try(ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
      for (byte[] item : _items) {
        stream.write(item);
      }
      return stream.toByteArray();      
    } catch (Exception e) {
      return null;
    }
  }
}
