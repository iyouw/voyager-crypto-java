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

public abstract class MdBase extends CryptoBase {
  public abstract MdAlgorithm getAlgorithm();

  public byte[] digest(String message, ExportType msgType)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, DEFAULT_BUFFER_SIZE);
  }

  public byte[] digest(String message, ExportType msgType, int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, bufferSize);
  }

  public String digest(String message, ExportType msgType, ExportType exportType)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, exportType, DEFAULT_BUFFER_SIZE);
  }

  public String digest(String message, ExportType msgType, ExportType exportType , int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(message, algorithm, msgType, exportType, bufferSize);
  }

  public byte[] digest(byte[] bytes)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, DEFAULT_BUFFER_SIZE);
  }

  public byte[] digest(byte[] bytes, int bufferSize)
    throws UnsupportedEncodingException{
    MdAlgorithm algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, bufferSize);
  }

  public String digest(byte[] bytes, ExportType exportType)
   throws UnsupportedEncodingException{
    var algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, exportType, DEFAULT_BUFFER_SIZE);
  }

  public String digest(byte[] bytes, ExportType exportType, int bufferSize)
   throws UnsupportedEncodingException{
    var algorithm = getAlgorithm();
    return digestCore(bytes, algorithm, exportType, bufferSize);
  }

  public byte[] digest(InputStream stream)
  {
    var algorithm = getAlgorithm();
    return digestCore(stream, algorithm, DEFAULT_BUFFER_SIZE);
  }

  public byte[] digest(InputStream stream, int bufferSize)
  {
    var algorithm = getAlgorithm();
    return digestCore(stream, algorithm, bufferSize);
  }

  public String digest(InputStream stream, ExportType exportType)
    throws UnsupportedEncodingException {
    var algorithm = getAlgorithm();
    return digestCore(stream, algorithm, exportType, DEFAULT_BUFFER_SIZE);
  }

  public String digest(InputStream stream, ExportType exportType, int bufferSize)
    throws UnsupportedEncodingException {
    var algorithm = getAlgorithm();
    return digestCore(stream, algorithm, exportType, bufferSize);
  }

  protected byte[] digestCore(Reader reader, MdAlgorithm algorithm, int bufferSize) {
    ReadCallback readCallback = (int length)-> reader.read(length);
    return digestNative(bufferSize, algorithm, readCallback);
  }

  protected byte[] digestCore(byte[] msg, MdAlgorithm algorithm, int bufferSize) {
    Reader reader = new ReaderStrategy(msg);
    return digestCore(reader, algorithm, bufferSize);
  }

  protected String digestCore(byte[] msg, MdAlgorithm algorithm, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  protected byte[] digestCore(String msg, MdAlgorithm algorithm, ExportType msgType, int bufferSize) 
    throws UnsupportedEncodingException {
    Reader reader = new ReaderStrategy(msg, msgType);
    return digestCore(reader, algorithm, bufferSize);
  }

  protected String digestCore(String msg, MdAlgorithm algorithm, ExportType msgType, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, msgType, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }

  protected byte[] digestCore(InputStream msg, MdAlgorithm algorithm, int bufferSize) {
    Reader reader = new ReaderStrategy(msg);
    return digestCore(reader, algorithm, bufferSize);
  }

  protected String digestCore(InputStream msg, MdAlgorithm algorithm, ExportType exportType, int bufferSize) 
    throws UnsupportedEncodingException {
    byte[] bytes = digestCore(msg, algorithm, bufferSize);
    BinaryEncodeStrategy strategy = new BinaryEncodeStrategy(exportType);
    return strategy.encode(bytes);
  }
}
