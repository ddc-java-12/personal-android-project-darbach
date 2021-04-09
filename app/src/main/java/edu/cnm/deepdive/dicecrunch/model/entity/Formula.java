package edu.cnm.deepdive.dicecrunch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Deque;

/**
 * Database entity corresponding to the Formula table.
 */
@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Die.class,
            parentColumns = "die_id", childColumns = "die_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Formula {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "formula_id")
  private long id;

  @ColumnInfo(index = true)
  private String name;

  private String formula;

  private String save_path;

  @ColumnInfo(name = "die_id", index = true)
  private long dieId;

  @SuppressWarnings("NotNullFieldNotInitialized")
  @NonNull
  @Ignore
  private Die die;

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

  public String getFormula() {
    return formula;
  }

  public void setFormula(String formula) {
    this.formula = formula;
  }

  public String getSave_path() {
    return save_path;
  }

  public void setSave_path(String save_path) {
    this.save_path = save_path;
  }

  public long getDieId() {
    return dieId;
  }

  public void setDieId(long dieId) {
    this.dieId = dieId;
  }

  @NonNull
  public Die getDie() {
    return die;
  }

  public void setDie(@NonNull Die die) {
    this.die = die;
  }
}
