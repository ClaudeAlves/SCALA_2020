package Utils

import Dictionary.dictionary

object SpellChecker {
  /**
    * Calculate the Levenshtein distance between two words.
    * @param s1 the first word
    * @param s2 the second word
    * @return an integer value, which indicates the Levenshtein distance between "s1" and "s2"
    */
  // TODO - Step 2
  def stringDistance(s1: String, s2: String): Int = {
    stringDistanceList(s1.toList, s2.toList, 0)
  }

  def stringDistanceList(s1: List[Char], s2: List[Char], cost: Int): Int = (s1, s2) match {
    case (Nil, _) => s2.length + cost
    case (_, Nil) => s1.length + cost
    case (x::xs, y::ys) => if(x == y) {
      min(stringDistanceList(xs, s2, cost + 1), stringDistanceList(s1, ys, cost + 1), stringDistanceList(xs, ys, cost))
    } else {
      min(stringDistanceList(xs, s2, cost + 1), stringDistanceList(s1, ys, cost + 1), stringDistanceList(xs, ys, cost + 1))
    }
  }

  def min(a: Int, b: Int, c: Int): Int ={
    Math.min(Math.min(a,b), c)
  }

  /**
    * Get the syntactically closest word in the dictionary from the given misspelled word, using the "stringDistance"
    * function. If the word is a number, this function just returns it.
    * @param misspelledWord the misspelled word to correct
    * @return the closest word from "misspelledWord"
    */
  // TODO - Step 2
  def getClosestWordInDictionary(misspelledWord: String): String = {
    if(misspelledWord.head == '_') return misspelledWord
    if(misspelledWord forall Character.isDigit) return misspelledWord
    var min = Int.MaxValue
    var word = ""
    for(k <- Dictionary.dictionary){
      val d = stringDistance(k._1, misspelledWord)
      if(d < min){
        min = d
        word = k._2
      }
    }
    word
  }
}
