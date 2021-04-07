package edu.cnm.deepdive.dicecrunch.model.type;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

  PLUS(10, "+", "^\\+"),
  MINUS(10, "-", "^-"),
  MULTIPLY(20, "*", "^\\*"),
  DIVIDE(20, "/", "^/"),        // 4/2 -> 4 2 /
  DROP_HIGHEST(30, "dh", "^dh"), // dh(4d6) -> 4 6 d dh -> (~6~, 3, 4, 3) = 10
  DROP_LOWEST(30, "dl", "^dl"),  // dl(4d6) -> 4 6 d dl -> (6, ~3~, 4, 3) = 13
  DICE_ROLL(40, "d", "^d"),  // 4d6 -> 4 6 d, // 3dHQ -> 3 HQ d
  LEFT_PARENTHESIS(50,"(", "^\\(");

  private final int priority;
  private final String symbol;
  private final String regex;

  Operator(int priority, String symbol, String regex) {
    this.priority = priority;
    this.symbol = symbol;
    this.regex = regex;
  }

  public int getPriority() {
    return this.priority;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getRegex() {
    return regex;
  }

}
