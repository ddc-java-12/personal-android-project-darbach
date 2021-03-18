package edu.cnm.deepdive.dicecrunch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.entity.Face;
import java.util.LinkedList;
import java.util.List;

public class DieWithFaces extends Die {

  @Relation(
      entity = Face.class,
      parentColumn = "die_id",
      entityColumn = "die_id"
  )
  private List<Face> faces = new LinkedList<>();

  public List<Face> getFaces() {
    return faces;
  }

  public void setFaces(List<Face> faces) {
    this.faces = faces;
  }

}
