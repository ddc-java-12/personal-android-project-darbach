package edu.cnm.deepdive.dicecrunch.model.type;

/**
 * An operand used in dice rolling expression formulas.
 */
public interface Operand {

  int value();

  @Override
  String toString();

}
