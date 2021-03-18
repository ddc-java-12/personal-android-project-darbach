package edu.cnm.deepdive.dicecrunch.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;

public class FaceWithDie extends Face {

  @Relation(
      entity = Die.class,
      parentColumn = "die_id",
      entityColumn = "die_id"
  )
  private Die die;

  @NonNull
  @Override
  public Die getDie() {
    return die;
  }

  @Override
  public void setDie(@NonNull Die die) {
    this.die = die;
  }
}
