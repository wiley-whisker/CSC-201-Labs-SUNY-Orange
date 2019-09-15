import java.nio.charset.CharacterCodingException;
import java.util.NoSuchElementException;

public class ExpressionEvaluator {
  String ex;

  public ExpressionEvaluator() {}

  public double evaluate(String input) throws InvalidExpressionException {
    MyStack<Character> operators = new MyStack<>();
    MyStack<Double> operands = new MyStack<>();
    ex = preliminaryParse(input); // Get rid of whitespace and throw error if bad symbols found.
    for (int i = 0; i < ex.length(); i++) {
      Character token = ex.charAt(i);
      if (Character.isDigit(token)) {
        operands.push(Double.parseDouble(token + ""));
      } else {
        operators.push(token);
      }
    }
    return eval(operators, operands);
  }

  private Double eval(MyStack<Character> syms,MyStack<Double> nums) {
    if (syms.isEmpty()) { //  Base Case
      return nums.pop();
    } // Recursive case.
    Double first = nums.pop();
    Character sym = syms.pop();
    if (sym == ')') {
      return evalPar(syms, nums);
    }
    if (getPrecedence(syms.peek()) < getPrecedence(sym)) {
      eval(syms, nums);
    }
    else {
      // TODO
    }
    return 1.0;
  }

  private Double evalPar(MyStack<Character> syms, MyStack<Double> nums) {
    return 1.0;
  }

  //private static

  private static int getPrecedence(char c) {
    if (c == '#' || c == ')') {
      return 0;
    }
    else if (c == '+' || c == '-') {
      return 1;
    }
    else if (c == '*' || c == '/') {
      return 2;
    }
    else if (c == '^') {
      return 3;
    }
    else {
      return 4; // c == ( or c == ) MAYBE NOT )
    }
  }

  private static String preliminaryParse(String input) throws InvalidExpressionException {
    input = input.replace(" ", ""); // Remove whitespace from expression.
    if(!input.matches("^[0-9+\\-\\*/()\\^]*$")) {
      for (int i = 0; i < input.length(); i++) {
        String currentToken = input.charAt(i) + "";
        if (!currentToken.matches("^[0-9+\\-\\*/()\\^]*$")) {
          throw new InvalidExpressionException(input, i);
        }
      }
    }
    return input;
  }

}
