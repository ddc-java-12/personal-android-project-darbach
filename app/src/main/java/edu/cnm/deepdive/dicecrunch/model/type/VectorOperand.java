package edu.cnm.deepdive.dicecrunch.model.type;

import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

public class VectorOperand implements Operand {

  private final int[] values;

  public VectorOperand(int... values) {
    this.values = values;
  }

  @Override
  public int value() {
    return IntStream.of(values).sum();
  }

  public int[] getValues() {
    return values;
  }

  @NonNull
  @Override
  public String toString() {
    return Arrays.toString(values);
  }
}
