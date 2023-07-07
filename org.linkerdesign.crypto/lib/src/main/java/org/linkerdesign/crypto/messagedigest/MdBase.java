package org.linkerdesign.crypto.messagedigest;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.linkerdesign.crypto.CryptoBase;
import org.linkerdesign.crypto.ReadCallback;
import org.linkerdesign.crypto.abstraction.ExportType;
import org.linkerdesign.crypto.abstraction.MdAlgorithm;
import org.linkerdesign.crypto.abstraction.Reader;
import org.linkerdesign.crypto.datatype.BinaryEncodeStrategy;
import org.linkerdesign.crypto.reader.ReaderStrategy;

/**
 * message digest
 */
abstract class MdBase extends CryptoBase {
  /**
   * get algorithm
   * @return message digest algorithm
   */
  protected abstract MdAlgorithm getAlgorithm();

  /**
   * digest with result of type byte[]
   * @param message message
   * @param msgType message encoding
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] digest(String message, ExportType msgType)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest with result of type byte[]. 
   * @param message message
   * @param msgType message encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] digest(String message, ExportType msgType, int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, bufferSize);
  }

  /**
   * digest with result of type string
   * @param message message 
   * @param msgType message encoding
   * @param exportType result encoding
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(String message, ExportType msgType, ExportType exportType)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, exportType, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest with result of type string
   * @param message message
   * @param msgType message encoding
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(String message, ExportType msgType, ExportType exportType , int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, exportType, bufferSize);
  }

  /**
   * digest
   * @param bytes message
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] digest(byte[] bytes)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest 
   * @param bytes message
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public byte[] digest(byte[] bytes, int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, bufferSize);
  }

  /**
   * digest
   * @param bytes message
   * @param exportType result encoding
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(byte[] bytes, ExportType exportType)
   throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, exportType, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest
   * @param bytes message
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(byte[] bytes, ExportType exportType, int bufferSize)
   throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, exportType, bufferSize);
  }

  /**
   * digest with stream message
   * @param stream message
   * @return result
   */
  public byte[] digest(InputStream stream)
  {
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(stream, algorithm, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest with stream message
   * @param stream message
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  public byte[] digest(InputStream stream, int bufferSize)
  {
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(stream, algorithm, bufferSize);
  }

  /**
   * digest with stream message
   * @param stream message
   * @param exportType result encoding
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(InputStream stream, ExportType exportType)
    throws UnsupportedEncodingException {
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(stream, algorithm, exportType, DEFAULT_BUFFER_SIZE);
  }

  /**
   * digest with stream message
   * @param stream message 
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  public String digest(InputStream stream, ExportType exportType, int bufferSize)
    throws UnsupportedEncodingException {
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(stream, algorithm, exportType, bufferSize);
  }

  /**
   * digest
   * @param reader reader
   * @param algorithm digest algorithm
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] digestCore(Reader reader, MdAlgorithm algorithm, int bufferSize) {
    ReadCallback readCallback = (int length)-> reader.read(length);
    return digestNative(bufferSize, algorithm, readCallback);
  }

  /**
   * digest
   * @param msg message
   * @param algorithm digest algorithm
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] digestCore(byte[] msg, MdAlgorithm algorithm, int bufferSize) {
    Reader reader = new ReaderStrategy(msg);
    return digestCore(reader, algorithm, bufferSize);
  }

  /**
   * digest
   * @param msg message 
   * @param algorithm digest algorithm
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String digestCore(byte[] msg, MdAlgorithm algorithm, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * digest 
   * @param msg message
   * @param algorithm digest algorithm
   * @param msgType message encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected byte[] digestCore(String msg, MdAlgorithm algorithm, ExportType msgType, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(msg, msgType);
    return digestCore(reader, algorithm, bufferSize);
  }

  /**
   * digest 
   * @param msg message 
   * @param algorithm digest algorithm
   * @param msgType message encoding
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String digestCore(String msg, MdAlgorithm algorithm, ExportType msgType, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, msgType, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  /**
   * digest
   * @param msg mesage
   * @param algorithm digest algorithm
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   */
  protected byte[] digestCore(InputStream msg, MdAlgorithm algorithm, int bufferSize) {
    Reader reader = new ReaderStrategy(msg);
    return digestCore(reader, algorithm, bufferSize);
  }

  /**
   * digest
   * @param msg message
   * @param algorithm digest algorithm
   * @param exportType result encoding
   * @param bufferSize native buffer size, which could turn the performance of decrypt algorithm
   * @return result
   * @throws UnsupportedEncodingException UnsupportedEncoding
   */
  protected String digestCore(InputStream msg, MdAlgorithm algorithm, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }
}
