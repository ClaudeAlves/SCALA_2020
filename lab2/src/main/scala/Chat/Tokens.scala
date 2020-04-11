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
  // Utils
  val PSEUDO: Token      = 9
  val NUM: Token         = 10
  val UNKNOWN: Token     = 11
  val EOL: Token         = 12
  // State of mind
  val ASSOIFFE : Token   = 13
  val AFFAME : Token     = 14
}
