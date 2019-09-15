public class InvalidExpressionException extends Exception {
  String badEx;
  public int badIndex;

  public InvalidExpressionException(String badE, int badI) {
    badEx = badE;
    badIndex = badI;
  }

  @Override
  public void printStackTrace() {
    super.printStackTrace();
    int spacesNeeded = badIndex;
    String topLine = "v";
    while (spacesNeeded > 0) {
      topLine = " " + topLine;
      spacesNeeded--;
    }
    System.out.println("Bad expression. Error found at index " + badIndex);
    System.out.println(topLine + "\n" + badEx);
  }
}
