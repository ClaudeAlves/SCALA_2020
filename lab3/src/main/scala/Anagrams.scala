import java.util

import scala.collection.immutable._


object Anagrams extends App {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** A fingerprint is a string which represents a sorted sequence of characters:
   *  Examples: 
   *
   *    "aaccx"
   *    "abyz"
   *    "ppp"
   *    ""
   */

  type FingerPrint = String


  /** The dictionary is simply a sequence of words.
   *  You can begin your development with this simple example. 
   *  A dictionary of English words is given to you as an external file (linuxwords.txt)  
   *  that you can load to use with your program  
   */

  val dictionary: List[Word] =    
    List("ate", "eat", "tea", "pot", "top", "sonja", "jason", "normal",
         "I", "love", "you", "olive")


  /** Converts a word/sentence into its fingerprint.
   *  The fingerprint has the same characters as the word, with the same
   *  number of occurrences, but the characters appear in sorted order.
   */

  def fingerPrint(s: Word): FingerPrint = (s.toLowerCase()).sortWith(_ < _)
  def fingerPrint(s: Sentence): FingerPrint = s.map(w => fingerPrint(w)).foldLeft("")(_ + _).sortWith(_ < _)


  /** `matchingWords` is a `Map` from fingerprints to a sequence of all
   *  the words that have that fingerprint.
   *  This map serves as an easy way to obtain all the anagrams of a word given its fingerprint.
   *
   *  For example, the word "eat" has the fingerprint "aet".
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `matchingWords` map will contain an entry:
   *
   *   "aet"-> List("ate", "eat", "tea")
   */

  val matchingWords: Map[FingerPrint, List[Word]] = generateMap()

  def generateMap(): Map[FingerPrint, List[Word]] = {
    var newMap: Map[FingerPrint, List[Word]] = Map()
    for (word <- dictionary) yield {
      if(newMap.contains(fingerPrint(word))){
        newMap += (fingerPrint(word) -> newMap(fingerPrint(word)).::(word))
      } else {
        newMap += (fingerPrint(word) -> List(word))
      }
    }
    newMap
  }


  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = matchingWords(fingerPrint(word))


  // Test code with for example:
  println(wordAnagrams("eta"))
  //println(wordAnagrams("jbdikb"))


  /** Returns the list of all subsequences of a fingerprint.
   *  This includes the fingerprint itself, i.e.
   *  "ko" is a subsequence of "kkoo". It also always includes
   *  the empty string "".
   *
   *  Example: the subsequences of the fingerprint "abbc" are
   *
   *    List("", "c", "b", "bc", "bb", "bbc", "a", "ac", "ab", "abc", "abb", "abbc")
   *
   *  Note that the order of the subsequences does not matter -- the subsequences
   *  in the example above could have been displayed in some other order.
   */

  def subseqs(fp: FingerPrint): List[FingerPrint] = {
    var result: List[FingerPrint] = List("")
    for (i <- 0 to fp.length) {
      for(j <- i to fp.length-1){
        result = fp.substring(i, j+1) :: result
      }
    }
    result
  }


  // Test code with for example:
  println(subseqs("aabbc"))


  /** Subtracts fingerprint `y` from fingerprint `x`.
   *
   *  The precondition is that the fingerprint `y` is a subsequence of
   *  the fingerprint `x` -- any character appearing in `y` must
   *  appear in `x`.
   */

  def subtract(x: FingerPrint, y: FingerPrint): FingerPrint = ???

  // Test code with for example:
  // println(subtract("aabbcc", "abc"))


  /** Returns a list of all anagram sentences of the given sentence.
   *
   *  An anagram of a sentence is formed by taking the fingerprint of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive","you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = ???

  // Test code with for example:
  // println(sentenceAnagrams(List("eat", "tea")))
  // println(sentenceAnagrams(List("you", "olive")))
  // println(sentenceAnagrams(List("I", "love", "you")))

}