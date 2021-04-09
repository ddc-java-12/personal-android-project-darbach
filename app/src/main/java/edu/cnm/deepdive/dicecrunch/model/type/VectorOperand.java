package edu.cnm.deepdive.dicecrunch.model.type;

import androidx.annotation.NonNull;
import java.util.Arrays;
import java.util.stream.IntStream;
import org.jetbrains.annotations.NotNull;

/**
 * An operand consisting of vector data to represent the result of rolling multiple dice at the
 * same time.
 */
public class VectorOperand implements Operand {

  private final int[] values;

  /**
   * Constructor that populates the state, consisting of all the results from rolling a set of dice.
   *
   * @param values The results from rolling a set of dice.
   */
  public VectorOperand(int... values) {
    this.values = values;
  }

  /**
   * Returns the results from rolling a set of dice.
   *
   * @return
   */
  public int[] getValues() {
    return values;
  }

  @Override
  public int value() {
    return IntStream.of(values).sum();
  }

  @NonNull
  @Override
  public String toString() {
    return Arrays.toString(values);
  }

}
