import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Reader {
  public static void main(String[] args) {
    File f = new File("the_odyssey1.txt");
    String[] tokens;
    try {
      Scanner s = new Scanner(f);
      String story = "";
      while (s.hasNext()) {
        story = story + s.next() + " ";
      }
      s.close();
      story = story.replaceAll("[,.?!;:\"]|(\\-{1,3})", " ");
      story = story.replaceAll("( {1,5})", " ");
      tokens = story.split(" ");
      System.out.println(tokens.length);
      for (int i = 0; i < tokens.length; i++) {
        System.out.println(tokens[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
