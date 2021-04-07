package edu.cnm.deepdive.dicecrunch.service;

import edu.cnm.deepdive.dicecrunch.model.type.Operator;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormulaService {

  private final static Map<String, Operator> symbolMap = new HashMap<>();

  private final static String POSTFIX_SEPARATOR = " ";
  private final static String OPERAND_REGEX = "^(\\d+|[A-Z]{1,2})";
  private final static String RIGHT_PARENTHESIS_REGEX = "^\\)";

  private final static Pattern operandPattern = Pattern.compile(OPERAND_REGEX);
  private final static Pattern plusPattern = Pattern.compile("^" + Operator.PLUS.getRegex());
  private final static Pattern minusPattern = Pattern.compile("^" + Operator.MINUS.getRegex());
  private final static Pattern multiplyPattern = Pattern.compile(Operator.MULTIPLY.getRegex());
  private final static Pattern dividePattern = Pattern.compile(Operator.DIVIDE.getRegex());
  private final static Pattern dropHighestPattern = Pattern
      .compile(Operator.DROP_HIGHEST.getRegex());
  private final static Pattern diceRollPattern = Pattern.compile(Operator.DICE_ROLL.getRegex());
  private final static Pattern leftParenthesisPattern = Pattern
      .compile(Operator.LEFT_PARENTHESIS.getRegex());
  private final static Pattern rightParenthesisPattern = Pattern.compile(RIGHT_PARENTHESIS_REGEX);

  private final Pattern operatorPattern; //Regex pattern should match ^(\+|-|\*|/|dh|dl|d|\()

  private String postfix;

  private FormulaService() {
    Operator[] operatorsArray = Operator.values();
    StringBuilder regexWork = new StringBuilder();
    regexWork.append("^(");
    for (int i = 0; i < operatorsArray.length; i++) {
      Operator op = operatorsArray[i];
      symbolMap.put(op.getSymbol(), op);
      regexWork.append(op.getSymbol());
      if (i != operatorsArray.length - 1) {
        regexWork.append("|");
      }
    }
    regexWork.append(")");
    operatorPattern = Pattern.compile(regexWork.toString());
  }

  public static FormulaService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Accept a dice formula as an infix operator string, and output a dice roll result string.
   *
   * @param formula an infix operator formula String
   * @return the result of evaluating the input formula
   */
  public String parse(String formula) {
    Deque<Operator> operatorStack = new ArrayDeque<>();

    Matcher operandToken;
    Matcher operatorToken;
    Matcher leftParenthesisToken;
    Matcher rightParenthesisToken;

    String formulaWork = new String(formula);
    StringBuilder postfixOutput = new StringBuilder();

    // Shunting-Yard algorithm http://mathcenter.oxford.emory.edu/site/cs171/shuntingYardAlgorithm/
    while (formulaWork.length() > 0) {
      operandToken = operandPattern.matcher(formulaWork);
      leftParenthesisToken = leftParenthesisPattern.matcher(formulaWork);
      rightParenthesisToken = rightParenthesisPattern.matcher(formulaWork);
      operatorToken = operatorPattern.matcher(formulaWork);
      if (operandToken.find()) {
        // If the incoming token is an operand, place it in the output.
        postfixOutput.append(operandToken.group(1));
        postfixOutput.append(POSTFIX_SEPARATOR);
        formulaWork = formulaWork.replace(OPERAND_REGEX, "");
      } else if (leftParenthesisToken.find()) {
        // If the incoming token is a left parenthesis, push it on the stack.
        operatorStack.add(Operator.LEFT_PARENTHESIS);
        formulaWork = formulaWork.replace(Operator.LEFT_PARENTHESIS.getRegex(), "");
      } else if (rightParenthesisToken.find()) {
        // If the incoming symbol is a right parenthesis: discard the right parenthesis, pop stack
        // symbols to output until you see a left parenthesis. Pop the left parenthesis and discard it.
        Operator current = operatorStack.pop();
        while (current != Operator.LEFT_PARENTHESIS) {
          postfixOutput.append(current.getSymbol());
          postfixOutput.append(" ");
          current = operatorStack.pop();
        }
        formulaWork = formulaWork.replace(RIGHT_PARENTHESIS_REGEX, "");
      } else if (operatorToken.find()
          && (operatorStack.size() == 0 || operatorStack.peek() == Operator.LEFT_PARENTHESIS)) {
        // If the incoming symbol is an operator and the stack is empty or contains a left
        // parenthesis on top, push the incoming operator onto the stack.
        operatorStack.push(symbolMap.get(operatorToken.group(1)));
      } else if (operatorToken.find()) {
        Operator incomingOperator = symbolMap.get(operatorToken.group(1));
        int incomingPriority = incomingOperator.getPriority();
        int stackPriority = operatorStack.peek().getPriority();
        if (incomingPriority > stackPriority) {
          // If the incoming symbol is an operator and has either higher precedence than the operator
          // on the top of the stack, or has the same precedence as the operator on the top of the
          // stack and is right associative -- push it on the stack.
          operatorStack.push(incomingOperator);
        } else if (incomingPriority <= stackPriority) {
          // If the incoming symbol is an operator and has either lower precedence than the operator
          // on the top of the stack, or has the same precedence as the operator on the top of the
          // stack and is left associative -- continue to pop the stack until this is not true. Then,
          // push the incoming operator.
          while (incomingPriority <= stackPriority) {
            postfixOutput.append(operatorStack.pop().getSymbol());
            postfixOutput.append(POSTFIX_SEPARATOR);
            stackPriority = operatorStack.peek().getPriority();
          }
        }
      } else { // formula input string is empty
        // At the end of the expression, pop and print all operators on the stack. (No parentheses
        // should remain.)
        while (!operatorStack.isEmpty()) {
          postfixOutput.append(operatorStack.pop().getSymbol());
          postfixOutput.append(POSTFIX_SEPARATOR);
        }
      }
    }
    return evaluate(postfixOutput.toString().trim());
  }

  /* Accept a dice formula as a postfix operator string and output a dice roll result string. Uses
   * Recursive Descent Parsing for evaluation algorithm.
   * (see http://math.hws.edu/javanotes/c9/s5.html#recursion.5.2)
   *
   * @param postfixFormula a parsed postfix operator formula string
   * @return the result of evaluating the input formula
   */
  private String evaluate(String postfixFormula) {


    return null;
  }

  private static class InstanceHolder {

    private static final FormulaService INSTANCE = new FormulaService();

  }

}
