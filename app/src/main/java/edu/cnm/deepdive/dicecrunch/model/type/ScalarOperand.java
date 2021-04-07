package edu.cnm.deepdive.dicecrunch.model.type;

import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;

public class ScalarOperand implements Operand {

  private final int value;

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
