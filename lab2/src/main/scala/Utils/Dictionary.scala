package Utils

/**
* Contains the dictionary of the application, which is used to validate, correct and normalize words entered by the
* user.
*/
object Dictionary {
  // This dictionary is a Map object that contains valid words as keys and their normalized equivalents as values (e.g.
  // we want to normalize the words "veux" and "aimerais" in on unique term: "vouloir").
  val dictionary: Map[String, String] = Map(
    "bonjour" -> "bonjour",
    "hello" -> "bonjour",
    "yo" -> "bonjour",
    "je" -> "je",
    "j" -> "je",
    "suis" -> "etre",
    "veux" -> "vouloir",
    "aimerais" -> "vouloir",
    "bière" -> "biere",
    "bières" -> "biere",
    "croissant" -> "croissant",
    "croissants" -> "croissant",
    "et" -> "et",
    "ou" -> "ou",
    "assoiffé" -> "assoiffe",
    "assoiffée" -> "assoiffe",
    "affamé" -> "affame",
    "affamée" -> "affame",
    "maison" -> "maison",
    "cailler" -> "cailler",
    "farmer" -> "farmer",
    "boxer" -> "boxer",
    "wittekop" -> "wittekop",
    "punkipa" -> "punkipa",
    "jackhammer" -> "jackhammer",
    "tenebreuse" -> "tenebreuse",
    "me" -> "me",
    "appelle" -> "appelle",
    "commander" -> "commander",
    "connaitre" -> "connaitre",
    "connaître" -> "connaitre",
    "mon" -> "mon",
    "solde" -> "solde",
    "combien" -> "combien",
    "coute" -> "coute",
    "coûte" -> "coute",
    "coutent" -> "coute",
    "coûtent" -> "coute",
    "quel" -> "quel",
    "est" -> "etre",
    "le" -> "le",
    "prix" -> "prix",
    "de" -> "de",
  )
}
