package edu.cnm.deepdive.dicecrunch.model.type;

/**
 * Represent the result of a single die being rolled.
 */
public class DieRoll {

  private final int result;
  private final int faces;

  /**
   * A constructor that populates immutable fields, as a single die being rolled can never be
   * changed.
   *
   * @param result Which face came up on the roll.
   * @param faces Number of possible faces for this die.
   */
  public DieRoll(int result, int faces) {
    this.result = result;
    this.faces = faces;
  }

  /**
   * Returns the result of the roll when the die was cast.
   *
   * @return
   */
  public int getResult() {
    return result;
  }

  /**
   * Returns the number of faces on the die that was cast.
   *
   * @return
   */
  public int getFaces() {
    return faces;
  }
}
