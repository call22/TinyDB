package cn.edu.thssdb.type;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * 处理各种Byte数据
 */
public class ByteManager {
  /**
   * 获取columns的固定Byte大小
   * @param columns
   * @return size of byte
   */
  public int columnsByteSize(ArrayList<Column> columns) {
    int columnsByteSize = 0;
    for (Column column : columns) {
      columnsByteSize += getColumnTypeByteSize(column);
    }
    // 可能会出现比long size小而放不下空闲列表指针
    if (columnsByteSize < Long.BYTES) {
      columnsByteSize = Long.BYTES;
    }
    return columnsByteSize;
  }

  public byte[] entryToBytes(Entry entry, Column column) {
    switch (column.getType()) {
      case INT:
        return intToBytes((Integer) entry.value);
      case LONG:
        return longToBytes((Long) entry.value);
      case FLOAT:
        return floatToBytes((Float) entry.value);
      case DOUBLE:
        return doubleToBytes((Double) entry.value);
      case STRING:
        return stringToBytes((String) entry.value, column.getMaxLength());
    }
    return null;
  }
  public Entry bytesToEntry(byte[] bytes, Column column) {
    switch (column.getType()) {
      case INT:
        return new Entry(bytesToInt(bytes));
      case LONG:
        return new Entry(bytesToLong(bytes));
      case FLOAT:
        return new Entry(bytesToFloat(bytes));
      case DOUBLE:
        return new Entry(bytesToDouble(bytes));
      case STRING:
        return new Entry(bytesToString(bytes, column.getMaxLength()));
    }
    return null;
  }

  /*************数据到Bytes的转换****************/
  public byte[] intToBytes(Integer data) {
    byte[] bytes = new byte[Integer.BYTES];
    if (data != null) {
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      buf.putInt(data);
    }
    return bytes;
  }
  public byte[] longToBytes(Long data) {
    byte[] bytes = new byte[Long.BYTES];
    if (data != null) {
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      buf.putLong(data);
    }
    return bytes;
  }
  public byte[] floatToBytes(Float data) {
    byte[] bytes = new byte[Float.BYTES];
    if (data != null) {
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      buf.putFloat(data);
    }
    return bytes;
  }
  public byte[] doubleToBytes(Double data) {
    byte[] bytes = new byte[Double.BYTES];
    if (data != null) {
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      buf.putDouble(data);
    }
    return bytes;

  }
  public byte[] stringToBytes(String data, int maxLength) {
    byte[] bytes = new byte[maxLength];
    if (data != null) {
      System.arraycopy(data.getBytes(), 0, bytes, 0, data.length());
    }
    return bytes;
  }

  /*************Bytes到数据的转换****************/
  public Integer bytesToInt(byte[] bytes) {
    ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
    buf.put(bytes);
    buf.flip();
    return buf.getInt();
  }
  public Long bytesToLong(byte[] bytes) {
    ByteBuffer buf = ByteBuffer.allocate(Long.BYTES);
    buf.put(bytes);
    buf.flip();
    return buf.getLong();
  }
  public Float bytesToFloat(byte[] bytes) {
    ByteBuffer buf = ByteBuffer.allocate(Float.BYTES);
    buf.put(bytes);
    buf.flip();
    return buf.getFloat();
  }
  public Double bytesToDouble(byte[] bytes) {
    ByteBuffer buf = ByteBuffer.allocate(Double.BYTES);
    buf.put(bytes);
    buf.flip();
    return buf.getDouble();
  }
  public String bytesToString(byte[] bytes, int maxLength) {
    // 获取有效位
    int i;
    for (i = 0; i < maxLength && i < bytes.length; i++) {
      if(bytes[i] == 0x0){
        break;
      }
    }
    byte[] valid = new byte[i];
    System.arraycopy(bytes, 0, valid, 0, i);
    return new String(valid);
  }

  public int getColumnTypeByteSize(Column column) {
    switch (column.getType()) {
      case INT:
        return Integer.BYTES;
      case FLOAT:
        return Float.BYTES;
      case LONG:
        return Long.BYTES;
      case DOUBLE:
        return Double.BYTES;
      default:
        return column.getMaxLength();
    }
  }
  public int getTypeByteSize(ColumnType column) {
    int default_len=20;
    switch (column) {
      case INT:
        return Integer.BYTES;
      case FLOAT:
        return Float.BYTES;
      case LONG:
        return Long.BYTES;
      case DOUBLE:
        return Double.BYTES;
      default:
        return default_len;
    }
  }

}

