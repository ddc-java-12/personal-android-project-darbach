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
            entity = Die.class,
            parentColumns = "die_id", childColumns = "die_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Face {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "face_id")
  private long id;

  @ColumnInfo(index = true)
  private String name;

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

  public long getDieId() {
    return dieId;
  }

  public void setDieId(long diceId) {
    this.dieId = diceId;
  }

  @NonNull
  public Die getDie() {
    return die;
  }

  public void setDie(@NonNull Die die) {
    this.die = die;
  }
}
