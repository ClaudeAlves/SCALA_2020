package Data

object Products {
  // TODO: step 2 - here your will have an attribute that will contain the products (e.g. "bière"), their types (e.g. "Boxer"), and their prices (e.g. 2.0).
  // TODO: step 2 - You will also have to find a way to store the default type/brand of a product.

  // TODO: JE SUIS PAS SUR DE MON IDEE LA, FAUT PTETR REGARDER LE COURS POUR ETRE SUR

  def getProduct (product :String, t :String) = product match{
    case "biere" => Beers.getBeersByType(t)
    case "croissant" => Croissants.getCroissantByType(t)
  }
}

object Beers{
  def getBeersByType(t:String) = t match {
    case "farmer" => ("farmer", 1)
    case "wittekop" => ("wittekop", 2)
    case "punkipa" => ("punkipa", 3)
    case "jackhammer" => ("jackhammer", 3)
    case "tenebreuse" => ("tenebreuse", 4)
    case _ => ("boxer", 1)
  }
}

object Croissants{
  def getCroissantByType(t:String) = t match{
    case "cailler" => ("croissant cailler", 2)
    case _ => ("croissant maison", 2)
  }
}