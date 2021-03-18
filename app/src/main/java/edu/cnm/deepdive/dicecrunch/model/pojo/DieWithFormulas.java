package edu.cnm.deepdive.dicecrunch.model.pojo;

import androidx.room.Relation;
import edu.cnm.deepdive.dicecrunch.model.entity.Die;
import edu.cnm.deepdive.dicecrunch.model.entity.Formula;
import java.util.LinkedList;
import java.util.List;

public class DieWithFormulas extends Die {

  @Relation(
      entity = Formula.class,
      parentColumn = "die_id",
      entityColumn = "die_id"
  )
  private List<Formula> formulas = new LinkedList<>();

  public List<Formula> getFormulas() {
    return formulas;
  }

  public void setFormulas(List<Formula> formulas) {
    this.formulas = formulas;
  }

}
