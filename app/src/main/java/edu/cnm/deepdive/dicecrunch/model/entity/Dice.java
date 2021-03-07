package edu.cnm.deepdive.dicecrunch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Dice {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "spin_id")
  private long id;

  @ColumnInfo(index = true)
  private String name;

  @ColumnInfo(index = true)
  private String calc_symbol;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCalc_symbol() {
    return calc_symbol;
  }

  public void setCalc_symbol(String calc_symbol) {
    this.calc_symbol = calc_symbol;
  }
}
