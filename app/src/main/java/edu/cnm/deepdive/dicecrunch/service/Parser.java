package edu.cnm.deepdive.dicecrunch.service;

import edu.cnm.deepdive.dicecrunch.model.type.DieRoll;
import edu.cnm.deepdive.dicecrunch.model.type.Operand;
import edu.cnm.deepdive.dicecrunch.model.type.Operator;
import edu.cnm.deepdive.dicecrunch.model.type.ScalarOperand;
import edu.cnm.deepdive.dicecrunch.model.type.VectorOperand;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Parser {

  private final static Pattern LITERAL_PATTERN =
      Pattern.compile("^\\s*(\\d+)\\s*(.*)$"); //[A-Z]{1,2}
  private final static Pattern RIGHT_PARENTHESIS_PATTERN =
      Pattern.compile("^\\s*\\)\\s*(.*)$");
  private final static Pattern DIE_ROLLS_PATTERN =
      Pattern.compile("\\d+d\\d+");
  private final static Pattern RESULT_VECTORS_PATTERN =
      Pattern.compile("\\[(\\d+)(,\\s*\\d+)*\\]");
  private final static Operator[] operators = Operator.values();

  private final String expression;
  private final List<DieRoll> dieRolls;

  private int value;
  private List<String> trace;

  public Parser(String expression) {
    this.expression = expression;
    evaluate();
    dieRolls = new LinkedList<>();
  }

  public int getValue() {
    return value;
  }

  public List<String> getTrace() {
    return trace;
  }

  public String getExpression() {
    return expression;
  }

  public List<DieRoll> getDieRolls() {
    return dieRolls;
  }

  /* Accept a dice formula as a postfix operator string and output a dice roll result string. Uses
   * Recursive Descent Parsing for evaluation algorithm.
   * (see http://math.hws.edu/javanotes/c9/s5.html#recursion.5.2)
   *
   * @param postfixFormula a parsed postfix operator formula string
   * @return the result of evaluating the input formula
   */
  private void evaluate() {

    StringBuilder work = new StringBuilder(expression);
    trace = new LinkedList<>();
    Deque<Operand> operandStack = new LinkedList<>();
    Deque<Operator> operatorStack = new LinkedList<>();

    //while work is not empty
    //for each operator
    //if the operator pattern matches the content of the stringBuilder:
    //delete that matched range from the StringBuilder
    //while the priority of the top operator on the stack is greater than or equal to
    //the priority of the current operator:
    //consume operator and associated operands
    //break; (found a match)

    //if we didn't find a matching operator:
    //if work matches right parenthesis:
    //delete the matched range
    //repeat:
    //consume operator and associated operands
    //until we consume a left parenthesis

    //if work matches a number:
    //delete the matched range
    //push the number onto the operand stack

    mainLoop:
    while (work.length() > 0) {
      for (Operator operator : operators) {
        Matcher matcher = operator.getRegex().matcher(work);
        if (matcher.matches()) {
          work.delete(0, matcher.start(1));
          while (!operatorStack.isEmpty()
              && operatorStack.peekFirst() != Operator.LEFT_PARENTHESIS
              && operatorStack.peekFirst().getPriority() >= operator.getPriority()) {
            operandStack
                .addFirst(processOperator(operatorStack.removeFirst(), operandStack, trace));
          }
          operatorStack.addFirst(operator);
          continue mainLoop;
        }
      }
      Matcher matcher = RIGHT_PARENTHESIS_PATTERN.matcher(work);
      if (matcher.matches()) {
        work.delete(0, matcher.start(1));
        boolean leftParenthesisFound = false;
        do {
          Operator current = operatorStack.removeFirst();
          operandStack.addFirst(processOperator(current, operandStack, trace));
          leftParenthesisFound = current == Operator.LEFT_PARENTHESIS;
        } while (!leftParenthesisFound);
      } else {
        matcher = LITERAL_PATTERN.matcher(work);
        if (matcher.matches()) {
          work.delete(0, matcher.end(1));
          Operand operand = new ScalarOperand(Integer.parseInt(matcher.group(1)));
          operandStack.addFirst(operand);
        } else {
          throw new IllegalArgumentException();
        }
      }
    }
    while (!operatorStack.isEmpty()) {
      operandStack.addFirst(processOperator(operatorStack.removeFirst(), operandStack, trace));
    }
    value = operandStack.removeFirst().value();
  }

  private Operand processOperator(Operator operator, Deque<Operand> operandStack,
      List<String> trace) {
    Operand[] operands = Stream
        .generate(operandStack::removeFirst)
        .limit(operator.getOperands())
        .toArray(Operand[]::new);
    Operand result = operator.evaluate(operands);
    if (operator == Operator.DICE_ROLL) {
//      addToDieRollsList(operands[0].value(), ((VectorOperand) result).getValues());
    }
    trace.add(String.format("%s = %s", operator.trace(operands), result));
    return result;
  }

  private void addToDieRollsList(int faces, int[] results) {
    for (int i = 0; i < results.length; i++) {
      dieRolls.add(new DieRoll(results[i], faces));
    }
  }

}
