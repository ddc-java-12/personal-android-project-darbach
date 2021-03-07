package edu.cnm.deepdive.dicecrunch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Dice.class,
            parentColumns = "dice_id", childColumns = "dice_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Formula {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "formula_id")
  private long id;

  @ColumnInfo(name = "dice_id", index = true)
  private long diceId;

  private String name;

  private String formula;

  private String save_path;


  @SuppressWarnings("NotNullFieldNotInitialized")
  @NonNull
  @Ignore
  private Dice dice;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDiceId() {
    return diceId;
  }

  public void setDiceId(long diceId) {
    this.diceId = diceId;
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

  @NonNull
  public Dice getDice() {
    return dice;
  }

  public void setDice(@NonNull Dice dice) {
    this.dice = dice;
  }
}
