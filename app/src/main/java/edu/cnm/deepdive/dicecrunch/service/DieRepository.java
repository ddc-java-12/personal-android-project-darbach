package edu.cnm.deepdive.dicecrunch.service;

import android.content.Context;
import edu.cnm.deepdive.dicecrunch.model.dao.DieDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FaceDao;
import edu.cnm.deepdive.dicecrunch.model.dao.FormulaDao;

public class DieRepository {

  private final Context context;
  private final DieDao dieDao;
  private final FaceDao faceDao;
  private final FormulaDao formulaDao;

  public DieRepository(Context context) {
    this.context = context;
    DiceCrunchDatabase database = DiceCrunchDatabase.getInstance();
    dieDao = database.getDieDao();
    faceDao = database.getFaceDao();
    formulaDao = database.getFormulaDao();
  }


}
