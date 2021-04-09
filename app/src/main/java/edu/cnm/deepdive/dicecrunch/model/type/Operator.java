package edu.cnm.deepdive.dicecrunch.model.type;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A representation of an operator, useful for parsing and evaluating a dice formula expression.
 * For this expression language, the following operators are included:
 *
 *  - Plus (+) for addition.
 *  - Minus (-) for subtraction.
 *  - Multiply (*) for multiplication.
 *  - Divide (/) for division.
 *  - Drop Highest (dh) for dropping the highest value from rolling a set of dice.
 *  - Drop Lowest (dl) for dopping the lowest value from rolling a set of dice.
 *  - Dice Roll (d) for expressing rolling rolling 1 or more polyhedral dice.
 *  - Left Parenthesis ( useful for parsing algorithms, such as Shunting-Yard.
 *
 *  The members of this enum provides operator precedence priorities, symbol text, regex expressions,
 *  each operator's arity (number of operands it requires), and methods to evaluate retrieve a
 *  trace String from the operation..
 */
public enum Operator {

  PLUS(10, "+", "\\+", 2) {
    @Override
    public Operand evaluate(Operand... operands) {
      return new ScalarOperand(
          Arrays.stream(operands)
              .map(Operand::value)
              .mapToInt(Integer::intValue)
              .sum()
      );
    }
    @Override
    public String trace(Operand... operands) {
      List<Operand> operandList = new LinkedList<>();
      Collections.addAll(operandList, operands);
      Collections.reverse(operandList);
      return operandList.stream()
          .map(Operand::toString)
          .collect(Collectors.joining(" + "));
    }
  },
  MINUS(10, "-", "-", 2) {
    @Override
    public Operand evaluate(Operand... operands) {
      return new ScalarOperand(
          operands[1].value() - operands[0].value()
      );
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("%s - %s", operands[1], operands[0]);
    }
  },
  MULTIPLY(20, "*", "\\*", 2) {
    @Override
    public Operand evaluate(Operand... operands) {
      return new ScalarOperand(
          Arrays.stream(operands)
              .map(Operand::value)
              .mapToInt(Integer::intValue)
              .reduce(1, (a, b) -> a * b)
      );
    }
    @Override
    public String trace(Operand... operands) {
      List<Operand> operandList = new LinkedList<>();
      Collections.addAll(operandList, operands);
      Collections.reverse(operandList);
      return operandList.stream()
          .map(Operand::toString)
          .collect(Collectors.joining(" * "));
    }
  },
  DIVIDE(20, "/", "/", 2) {
    @Override
    public Operand evaluate(Operand... operands) {
      return new ScalarOperand(
          operands[1].value() / operands[0].value()
      );
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("%s / %s", operands[1], operands[0]);
    }
  },        // 4/2 -> 4 2 /
  DROP_HIGHEST(30, "dh", "dh", 1) {
    @Override
    public Operand evaluate(Operand... operands) {
      VectorOperand operand = (VectorOperand) operands[0];
      return new VectorOperand(
          IntStream.of(operand.getValues())
              .boxed()
              .sorted(Comparator.reverseOrder())
              .skip(1)
              .mapToInt(Integer::intValue)
              .toArray()
      );
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("dh(%s)", operands[0]);
    }
  }, // dh(4d6) -> 4 6 d dh -> (~6~, 3, 4, 3) = 10
  DROP_LOWEST(30, "dl", "dl", 1) {
    @Override
    public Operand evaluate(Operand... operands) {
      VectorOperand operand = (VectorOperand) operands[0];
      return new VectorOperand(
          IntStream.of(operand.getValues())
              .sorted()
              .skip(1)
              .toArray()
      );
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("dl(%s)", operands[0]);
    }
  },  // dl(4d6) -> 4 6 d dl -> (6, ~3~, 4, 3) = 13
  DICE_ROLL(40, "d", "d", 2) {
    @Override
    public Operand evaluate(Operand... operands) {
      int sides = operands[0].value();
      int dice = operands[1].value();
      return new VectorOperand(
          IntStream.generate(() -> rng.nextInt(sides) + 1)
            .limit(dice)
            .toArray()
      );
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("%sd%s", operands[1], operands[0]);
    }
  },  // 4d6 -> 4 6 d, // 3dHQ -> 3 HQ d
  LEFT_PARENTHESIS(50, "(", "\\(", 1) {
    @Override
    public Operand evaluate(Operand... operands) {
      return operands[0];
    }
    @Override
    public String trace(Operand... operands) {
      return String.format("(%s)", operands[0]);
    }
  };

  private static final String EVALUATION_FORMAT = "^\\s*%s\\s*(.*)$";

  private static Random rng;

  private final int priority;
  private final String symbol;
  private final Pattern regex;
  private final int operands;

  /**
   * The constructor used by the enum to populate internal state.
   *
   * @param priority The arithmetic precedence.
   * @param symbol The text representation of the operand for display.
   * @param regex A regex expression to match the operator.
   * @param operands The operator's arity (number of operands it requires).
   */
  Operator(int priority, String symbol, String regex, int operands) {
    this.priority = priority;
    this.symbol = symbol;
    this.regex = Pattern.compile(String.format(EVALUATION_FORMAT, regex));
    this.operands = operands;
  }

  /**
   * Provide a random number generator for dice rolling operations.
   *
   * @param rng
   */
  public static void setRng(Random rng) {
    Operator.rng = rng;
  }

  /**
   * Return the arithmetic precedence of the operator.
   *
   * @return
   */
  public int getPriority() {
    return this.priority;
  }

  /**
   * Return the text symbol of the operator used for display.
   *
   * @return
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Return the regex expression used for locating the operator in the formula expression.
   *
   * @return
   */
  public Pattern getRegex() {
    return regex;
  }

  /**
   * Return the operator's arity (number of operands it requires).
   *
   * @return
   */
  public int getOperands() {
    return operands;
  }

  /**
   * Performs the operation on the provided set of operands.
   *
   * @param operands The number of operands must match the operator's arity.
   * @return The evaluated result.
   */
  public abstract Operand evaluate(Operand... operands);

  /**
   * Return a text representation of formula expression used for display.
   *
   * @param operands
   * @return
   */
  public abstract String trace(Operand... operands);

}
