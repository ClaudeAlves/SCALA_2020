package Utils

object ClinksCalculator {
  /**
    * Calculate the factorial of a given number
    * @param n the number to compute
    * @return n!
    */
  def factorial(n: Int): Int = {
    /**
      * tail-recursive implementation
      * @param n the number to evaluate
      * @param acc accumulator
      * @return n!
      */
    def facto_r(n: Int, acc: Int): Int = n match {
      case 0 => acc
      case _ => facto_r(n - 1, n * acc)
    }

    facto_r(n, 1)
  }

  def calculateCombination(n: Int, k: Int): Int = 
    if (n < k) 0 
    else factorial(n) / (factorial(k) * factorial(n - k))
}
