package Chat
import java.lang.Exception
import java.util.NoSuchElementException

import Data.UsersInfo
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
      case _ => 0
    }

    /**
      * Return the output text of the current node, in order to write it in console.
      * @return the output text of the current node
      */
    def reply: String = this match {
      // Example cases
      case Thirsty() => "Eh bien, la chance est de votre côté, car nous offrons les meilleures bières de la région !"
      case Hungry() => "Pas de soucis, nous pouvons vous offrir des croissants faits maisons !"
      case Order(order: ExprTree) => {
        try {
          val price = order.computePrice
          UsersInfo.purchase(price)
          "Voici donc " + order.reply + " ! Cela coûte CHF " + price + " et votre nouveau solde est de CHF " + UsersInfo.getSolde() + "."
        } catch {
          case _:NoSuchElementException => "Veuillez d'abord vous identifier."
        }
      }
      case Product(amount:Int, prod:String, brand:String) => amount.toString + " " + Data.Products.getProduct(prod, brand)._1
      case Id(pseudo: String) => {
            UsersInfo.addUser(pseudo)
            "Bonjour, " + pseudo.substring(1) + "!"
      }
      case State(value: ExprTree) => value.reply
      case Solde() => {
        try {
          "Le montant actuel de votre solde est de CHF " + UsersInfo.getSolde() + "."
        } catch {
          case _:NoSuchElementException => "Veuillez d'abord vous identifier."
        }
      }
      case Price(value: ExprTree) => "Cela coûte CHF " + value.computePrice + "."
      case And(lchild, rchild) => lchild.reply + " et " + rchild.reply
      case Or(lchild, rchild) => lchild.reply + " ou " + rchild.reply
    }
  }

  /**
    * Declarations of the nodes' types.
    */
  // Example cases
  case class Order(value: ExprTree) extends ExprTree
  case class Id(value: String) extends ExprTree
  case class State(value: ExprTree) extends ExprTree
  case class Solde() extends ExprTree
  case class Price(value: ExprTree) extends ExprTree
  case class Thirsty() extends ExprTree
  case class Hungry() extends ExprTree
  case class Or(lchild: ExprTree, rchild:ExprTree) extends ExprTree
  case class And(lchild: ExprTree, rchild:ExprTree) extends ExprTree
  case class Product(amount:Int, prod:String, brand:String) extends ExprTree
}
