package Chat

// TODO - step 3
object Tree {

  /**
    * This sealed trait represents a node of the tree and contains methods to compute it and write its output text in console.
    */
  sealed trait ExprTree {
    /**
      * Compute the price of the current node, then returns it. If the node is not a computational node, the method
      * returns 0.0.
      * For example if we had a "+" node, we would add the values of its two children, then return the result.
      * @return the result of the computation
      */
    def computePrice: Double = this match {
      case Or(lchild: ExprTree, rchild:ExprTree) => Math.min(lchild.computePrice, rchild.computePrice)
      case And(lchild: ExprTree, rchild:ExprTree) => lchild.computePrice + rchild.computePrice
      case Product(amount:Int, prod:String, brand:String) => amount * Data.Products.getProduct(prod, brand)._2
    }

    /**
      * Return the output text of the current node, in order to write it in console.
      * @return the output text of the current node
      */
    def reply: String = this match {
      // Example cases
      case Thirsty() => "Eh bien, la chance est de votre côté, car nous offrons les meilleures bières de la région !"
      case Hungry() => "Pas de soucis, nous pouvons notamment vous offrir des croissants faits maisons !"
      case Order(order: ExprTree) => order.reply + order.computePrice
      case Product(amount:Int, prod:String, brand:String) => amount.toString + Data.Products.getProduct(prod, brand)._1
    }
  }

  /**
    * Declarations of the nodes' types.
    */
  // Example cases
  case class Order(value: ExprTree) extends ExprTree
  case class Thirsty() extends ExprTree
  case class Hungry() extends ExprTree
  case class Or(lchild: ExprTree, rchild:ExprTree) extends ExprTree
  case class And(lchild: ExprTree, rchild:ExprTree) extends ExprTree
  case class Product(amount:Int, prod:String, brand:String) extends ExprTree
}
