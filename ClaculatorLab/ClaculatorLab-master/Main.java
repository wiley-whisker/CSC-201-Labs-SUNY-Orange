public class Main {
  public static void main(String[] args) {
    ExpressionEvaluator evaluator = new ExpressionEvaluator();
    String goodEx = "5+3^2-1";
    String badEx = "4+9(4-3)o1";
    String simpleEx = "2+3*5";
    try {
      System.out.println("FINAL RESULT: " + evaluator.evaluate(goodEx));
      System.out.println("Good expression");
    } catch (InvalidExpressionException e) {
      e.printStackTrace();
    }
  }
}
