package Utils

/**
  * Contains the functions necessary to calculate the number of *clinks* when n people want to cheers.
  */
object ClinksCalculator {
  /**
    * Calculate the factorial of a given number.
    * @param n the number to compute
    * @return n!
    */
  // TODO - Step 1
  def factorial(n: Int): Int = {
    def loop(n: Int, acc: Int): Int =
      if (n == 0) acc
      else loop(n-1, acc*n)
    loop(n, 1)
  }

  /**
    * Calculate the combination of two given numbers.
    * @param n the first number
    * @param k the second number
    * @return n choose k
    */
  // TODO - Step 1

  def calculateCombination(n: Int, k: Int): Int = {
    return factorial(n) / (factorial(n-k)*factorial(k))
  }
}
