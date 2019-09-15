import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  /**
   * Loads the dictionary from the file and formats as an array
   * @return A String[] of every word in the dictionary.
   * @throws Exception
   */
  public String[] getDictionary() throws Exception {
    File f = new File("dictionary.txt");
    String[] tokens;
    try {
      Scanner s = new Scanner(f);
      String words = "";
      while (s.hasNext()) {
        words = words + s.next() + " ";
      }
      s.close();
      String[] dict = words.split(" ");
      return dict;
    } catch (IOException e) {
      e.printStackTrace();
      throw new Exception("Error loading dict file."); // this should never happen.
    }
  }


  /**
   * Extracts the story from a file, removes unuseful characters, and format as an array.
   * @param fName Fame of file
   * @return A string[] of every word in the story.
   * @throws Exception
   */
  public String[] getStory(String fName) throws Exception {
    try {
      File f = new File(fName);
      String[] tokens;
      Scanner s = new Scanner(f);
      String story = "";
      while (s.hasNext()) {
        story = story + s.next() + " ";
      }
      s.close();
      story = story.replaceAll("[,.?!;:\"]|(\\-{1,3})", " ");
      story = story.replaceAll("( {1,5})", " ");
      tokens = story.split(" ");
      return tokens;
    } catch (IOException e) {
      e.printStackTrace();
      throw new Exception("Error loading story file."); // this should never happen.
    }
  }

  public static void main(String[] args) throws Exception {
    Main main = new Main();
    String[] dict = main.getDictionary(); // get dict as array

    SpellChecker sc = new SpellChecker();
    sc.loadDict(dict); // load dict into hashmap.

    System.out.println(sc.dict.getCollisions()); // display how many collisions there were.
    System.out.println(dict.length); // display how many words in dictionary

    // Error check the first story
    String[] story1 = main.getStory("the_odyssey1.txt"); // load story 1 from file
    String[] errors1 = sc.findErrors(story1); // find errors
    for (int i = 0; i < errors1.length; i++) {
      System.out.println(errors1[i]); // print out every error
    }
    System.out.println("Errors in file 1: " + errors1.length); // display how many errors

    // error check the second story
    String[] story2 = main.getStory("the_odyssey2.txt"); // load second story from file
    String[] errors2 = sc.findErrors(story2); // find the errors
    for (int i = 0; i < errors1.length; i++) {
      System.out.println(errors1[i]); // print out all the errors
    }
    System.out.println("Errors in file 2: " + errors2.length); // display how many errors
  }
}
