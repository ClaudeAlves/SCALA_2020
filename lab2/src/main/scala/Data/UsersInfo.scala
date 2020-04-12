package Data

import java.util.NoSuchElementException

import scala.collection.mutable

object UsersInfo {

  // Will contain the name of the currently active user; default value is null.
  private var _activeUser: String = _

  // TODO: step 2 - create an attribute that will contain each user and its current balance.
  private var accounts = scala.collection.mutable.Map[String, Double]()

  def addUser(name: String) {
    _activeUser = name
    if(!accounts.contains(name)) accounts.put(name, 30.0)
  }

  /**
    * Update an account by decreasing its balance.
    * @param user the user whose account will be updated
    * @param amount the amount to decrease
    * @return the new balance
    */
  // TODO: step 2
  @throws(classOf[NoSuchElementException])
  def purchase(amount: Double): Double = {
    val newAmount = (accounts.get(_activeUser).get - amount)
    accounts = accounts + (_activeUser -> newAmount)
    newAmount
  }

  @throws(classOf[NoSuchElementException])
  def getSolde(): Double = {
    return accounts.get(_activeUser).get
  }
}
