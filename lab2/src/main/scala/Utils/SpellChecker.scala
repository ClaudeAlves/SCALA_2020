package Utils

import Utils.Dictionary.dictionary

object SpellChecker {
  /*
  def stringDistanceRec(s1: String, s2: String): Int ={

    def intEditDistance(s1:String, s2:String, n:Int, m:Int ):Int
    {
      /* Here n = len(s1) m = len(s2) */

       if(n == 0 && m == 0)  0
       else if(n == 0) m
       else if( m == 0 ) n

       /* Recursive Part */
       else {
         var a = dit_Distance (s1, s2, n - 1, m - 1) + (s1[n - 1] != s2[m - 1] );
         var b = Edit_Distance (s1, s2, n - 1, m) + 1; //Deletion
         var c = Edit_Distance (s1, s2, n, m - 1) + 1; //Insertion

        min (a, b, c)
      }
    }

    intEditDistance(s1:String, s2:String, s1.size, s2.size ):Int
  }
  */

  def stringDistance(s1: String, s2: String): Int = {
    val memo = scala.collection.mutable.Map[(String, String), Int]()
    def min(a: Int, b: Int, c: Int) = Math.min(Math.min(a, b), c)

    def sd(s1: String, s2: String): Int = {
      if (!memo.contains((s1, s2))) {
        memo((s1, s2)) = (s1, s2) match {
          case (_, "") => s1.length
          case ("", _) => s2.length
          case (x, y) =>
            val c1 = x.charAt(0)
            val t1 = x.substring(1, x.length)
            val c2 = y.charAt(0)
            val t2 = y.substring(1, y.length)

            min(sd(t1, s2) + 1, sd(s1, t2) + 1, sd(t1, t2) + (if (c1 == c2) 0 else 1))
        }
      }

      memo((s1, s2))
    }

    sd(s1, s2)
  }

  def getClosestWordInDictionary(misspelledWord: String): String = {
    // If the word is a number we don't want to replace it so we just return it.
    if (misspelledWord.forall(Character.isDigit) || misspelledWord.startsWith("_")) {
      misspelledWord
    } else {
      // Build a list of tuples that contains the Levenshtein score for each of the dictionary's keys, according to the
      // given misspelled word, e.g. (("bonjour", 4), ("hello", 1), (...))
      val dictionaryDistanceScores = dictionary
        .map(x => (x._1, stringDistance(x._1, misspelledWord)))
        .toSeq

      // Sort the list by ascending score and return the top element's value (which is the closest word).
      dictionary.getOrElse(
        dictionaryDistanceScores.sortBy(_._1).minBy(_._2)._1, 
        throw new Error("Unable to find an occurence in the dictionary.")
      )
    }
  }
}
