package Chat

object Tokens {
  type Token = Int

  // Terms
  val BONJOUR: Token     = 0
  val JE: Token          = 1
  // Actions
  val ETRE: Token        = 2
  val VOULOIR: Token     = 3
  // Operators
  val ET: Token          = 4
  val OU: Token          = 5
  // Products
  val BIERE: Token       = 6
  val CROISSANT: Token   = 7
  // Brands
  val MAISON: Token      = 8
  val CAILLER: Token     = 15
  val FARMER: Token      = 16
  val BOXER: Token       = 17
  val WITTEKOP: Token    = 18
  val PUNKIPA: Token     = 19
  val JACKHAMMER: Token  = 20
  val TENEBREUSE: Token  = 21
  // Phrase identifier
  val COMMANDER: Token = 22
  val ME: Token = 23
  val APPELLE: Token = 24
  // Solde
  val CONNAITRE: Token = 25
  val MON: Token = 26
  val SOLDE: Token = 27
  // Prix
  val COMBIEN: Token = 29
  val COUTE: Token = 28
  val QUEL: Token = 30
  val LE: Token = 31
  val PRIX: Token = 32
  val DE: Token = 33
  // Utils
  val PSEUDO: Token      = 9
  val NUM: Token         = 10
  val UNKNOWN: Token     = 11
  val EOL: Token         = 12
  // State of mind
  val ASSOIFFE : Token   = 13
  val AFFAME : Token     = 14
}
