package edu.cnm.deepdive.dicecrunch.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Dice.class,
            parentColumns = "dice_id", childColumns = "dice_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Face {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "face_id")
  private long id;

  @ColumnInfo(name = "dice_id", index = true)
  private long diceId;

  private String name;

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

  @NonNull
  public Dice getDice() {
    return dice;
  }

  public void setDice(@NonNull Dice dice) {
    this.dice = dice;
  }
}
