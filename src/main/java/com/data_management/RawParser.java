package com.data_management;

/**
 * Reads raw recorded measurement and handles special formats
 * like percentages.
 */
public class RawParser {

  /**
   * Parses a number as a double, even if it is a percentage.
   * @param raw a string that contains
   * @return  double form of the number represented as raw string
   *
   * @implNote does not use Locals. this is a known limitation
   */
  public static double numeric(String raw) {
    var raw_ = raw.strip();
    // special case percentages (e.g. 67%)
    if (raw_.endsWith("%")) {
        return Double.parseDouble(raw.substring(0, raw_.length() - 1));
    }
    return Double.parseDouble(raw_);
  }

}
