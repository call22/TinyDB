package cn.edu.thssdb.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

public class Row implements Serializable {
  private static final long serialVersionUID = -5809782578272943999L;
  protected ArrayList<Entry> entries;

  public Row() {
    this.entries = new ArrayList<>();
  }

  public Row(Entry[] entries) {
    this.entries = new ArrayList<>(Arrays.asList(entries));
  }

  public ArrayList<Entry> getEntries() {
    return entries;
  }

  public void appendEntries(ArrayList<Entry> entries) {
    this.entries.addAll(entries);
  }

  @Override
  public String toString() {
    if (entries == null)
      return "EMPTY";

    StringBuilder builder = new StringBuilder();
    builder.append("| ");
    for(Entry e : entries){
      if(e==null){
        builder.append("null").append(" | ");
      }else{
        builder.append(e.toString()).append(" | ");
      }
    }
    return builder.toString();
  }

}
