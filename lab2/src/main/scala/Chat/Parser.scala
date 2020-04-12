package Chat

import Chat.Tokens._
import Tree._

// TODO - step 4
class  Parser(tokenizer: Tokenizer) {
  import tokenizer._

  var curTuple: (String, Token) = ("unknown", UNKNOWN)
  
  def curValue: String = curTuple._1
  def curToken: Token = curTuple._2

  /** Reads the next token and assigns it into the global variable curTuple */
  def readToken(): Unit = curTuple = nextToken()

  /** "Eats" the expected token, or terminates with an error. */
  private def eat(token: Token): Unit = if (token == curToken) readToken() else expected(token)

  /** Complains that what was found was not expected. The method accepts arbitrarily many arguments of type TokenClass */
  // TODO (BONUS): find a way to display the string value of the tokens (e.g. "BIERE") instead of their integer value (e.g. 6).
  private def expected(token: Token, more: Token*): Nothing =
    fatalError(" expected: " +
      (token :: more.toList).mkString(" or ") +
      ", found: " + curToken)

  def fatalError(msg: String): Nothing = {
    println("Fatal error", msg)
    new Exception().printStackTrace()
    sys.exit(1)
  }

  /** the root method of the parser: parses an entry phrase */
  def parsePhrases() : ExprTree = {
    if (curToken == BONJOUR) eat(BONJOUR)
    if (curToken == JE) {
      eat(JE)
      if (curToken == ETRE) {
        eat(ETRE)
        if (curToken == ASSOIFFE) {
          // Here we do not "eat" the token, because we want to have a custom 2-parameters "expected" if the user gave a wrong token.
          readToken()
          State(Thirsty())
        }
        else if (curToken == AFFAME) {
          readToken()
          State(Hungry())
        }
        else if (curToken == PSEUDO){
          val pseudo = curValue
          readToken()
          Id(pseudo)
        }
        else expected(ASSOIFFE, AFFAME, PSEUDO)
      }
      else if (curToken == VOULOIR){
        eat(VOULOIR)
        if (curToken == COMMANDER){
          eat(COMMANDER)
          parseOrder()
        }
        else if (curToken == CONNAITRE){
          eat(CONNAITRE)
          parseSolde()
        } else expected(CONNAITRE, COMMANDER)

      }
      else if (curToken == ME){
        eat(ME)
        eat(APPELLE)
        if(curToken == PSEUDO){
          val pseudo = curValue
          readToken()
          Id(pseudo)
        } else expected(PSEUDO)

      } else expected(ME)

    }
    // PRIX
    else if (curToken == COMBIEN) {
      eat(COMBIEN)
      eat(COUTE)
      parsePrice()
    }
    else if (curToken == QUEL){
      eat(QUEL)
      eat(ETRE)
      eat(LE)
      eat(PRIX)
      eat(DE)
      parsePrice()
    }
    else expected(JE, COMBIEN, QUEL)
  }

  def parseOrder(): ExprTree = {
    val product = parseProduct()
    if(curToken == ET){
      eat(ET)
      return Order(And(product, parseProduct()))
    }
    else if (curToken == OU){
      eat(OU)
      return Order(Or(product, parseProduct()))
    }
    Order(product)
  }

  def parseSolde(): ExprTree = {
    if(curToken == MON){
      eat(MON)
      if(curToken == SOLDE){
        readToken()
        Solde()
      }
      else expected(SOLDE)
    }
    else expected(MON)
  }

  def parsePrice(): ExprTree = {
    val product = parseProduct()
    if(curToken == ET){
      eat(ET)
      return Price(And(product, parseProduct()))
    } else if (curToken == OU){
      eat(OU)
      return Price(Or(product, parseProduct()))
    }
    Price(product)
  }

  def parseProduct(): ExprTree = {
    if(curToken == NUM){
      val amount = curValue.toInt
      readToken()
      if(curToken == BIERE || curToken == CROISSANT){
        val product = curValue
        readToken()
        if(curToken == MARQUE){
          val brand = curValue
          readToken()
          Product(amount, product, brand)
        } else {
          readToken()
          Product(amount, product, "")
        }
      } else expected(BIERE, CROISSANT)
    } else expected(NUM)
  }

  // Start the process by reading the first token.
  readToken()
}


