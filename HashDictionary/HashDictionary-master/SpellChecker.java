import java.util.LinkedList;

public class SpellChecker {
  public HashTable<String> dict;

  public SpellChecker(){}

  /**
   * Loads the dictionary from an array to the hashmap.
   * @param dictArr The String[] of words in the dictionary.
   */
  public void loadDict(String[] dictArr) {
    dict = new HashTable(dictArr.length * 4); // make output space 2x size of dict.
    for (int i = 0; i < dictArr.length; i++) {
      dict.add(dictArr[i], "test");
    }
    System.out.println(dict.getCollisions());
  }

  /**
   * Finds every word in the input array that doesn't show up in the dictionary
   * @param words An array of words
   * @return A String[] of every word that DOESN'T appear in the dictionary.
   */
  public String[] findErrors(String[] words) {
    LinkedList<String> misspelled = new LinkedList();
    int badWords = 0;
    for (int i = 0; i < words.length; i++) { // every word in story
      if (!dict.contains(words[i])) { // if it isn't in the dictionary
        misspelled.add(words[i]); // remember it.
        badWords++;
      }
    }
    if (badWords == 0) { // If there were no bad words.
      return new String[0]; // return an empty array
    } else { // there was at least one bad word
      String[] errors = new String[badWords];
      int i = 0;
      for (String s : misspelled) {
        errors[i] = s;
        i++;
      }
      return errors; // return it as an array
    }
  }
}
