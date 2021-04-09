package edu.cnm.deepdive.dicecrunch.model.type;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

/**
 * A scalar representation of an operand.
 */
public class ScalarOperand implements Operand {

  private final int value;

  /**
   * Create a scalar operand.
   *
   * @param value The numeric value.
   */
  public ScalarOperand(int value) {
    this.value = value;
  }

  @Override
  public int value() {
    return value;
  }

  @NonNull
  @Override
  public String toString() {
    return String.valueOf(value);
  }
}
