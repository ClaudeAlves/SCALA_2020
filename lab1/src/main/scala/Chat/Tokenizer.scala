package Chat

import Tokens._
import Utils.Dictionary.dictionary
import Utils.SpellChecker
import Utils.SpellChecker._
import scala.collection.mutable.ListBuffer

class Tokenizer(input: String) {
  var tokenList = new ListBuffer[(String, Token)]()
  var counter = -1
  /**
    * Separate the user's input into tokens.
    */
  // TODO - Step 3
  def tokenize(): Unit = {
    val inputCleared: String = input.replaceAll("[ ']+", " ").replaceAll("[-+.!?*^:,]","")
    println(inputCleared)
    for(word <- inputCleared.split(" ")){
      if(dictionary.contains(word)){
        val wordCorrected = dictionary.apply(word)
        tokenList.addOne((wordCorrected, getToken(wordCorrected)))
      } else {
        val wordCorrected = SpellChecker.getClosestWordInDictionary(word)
        tokenList.addOne((wordCorrected, getToken(wordCorrected)))
      }
    }
    println(tokenList.toString())
  }

  def getToken(s: String): Token = s match {
    case "bonjour" => BONJOUR
    case "je" => JE
    case "etre" => ETRE
    case "vouloir" => VOULOIR
    case "et" => ET
    case "ou" => OU
    case "biere" => BIERE
    case "croissant" => CROISSANT
    case "\n" => EOL
    case s => if(s forall Character.isDigit) NUM else if(s.head == '_') PSEUDO else UNKNOWN
  }

  /**
    * Get the next token of the user input, or EOL if there is no more token.
  	* @return a tuple that contains the string value of the current token, and the identifier of the token
    */
  // TODO - Step 3
  def nextToken(): (String, Token) = {
    counter+=1
    if(counter < tokenList.size) tokenList.apply(counter) else ("", EOL)
  }
}
