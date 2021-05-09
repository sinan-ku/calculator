package katas.sinan.kheir

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class InfixCalculatorTest {

    @Test
    fun `given single operation, should calculate and return result`() {
        val expression = "( 1 + 2 )"
        Assertions.assertThat(InfixCalculator.calculate(expression) == "3.0")
    }

    @Test
    fun `given nested operations, should calculate and return result`() {
        val expression = "( 1 + ( 2 * 3 ) )"
        Assertions.assertThat(InfixCalculator.calculate(expression) == "7.0")
    }

    @Test
    fun `given multiple nested operations, should calculate and return result`() {
        val expression = "( ( 1 + 5 ) + ( 1 + 2 ) )"
        Assertions.assertThat(InfixCalculator.calculate(expression) == "9.0")
    }

    @Test
    fun `given expression with multiple nested parantheses, should calculate and return result`() {
        val expression = "( ( 2 * 3 ) )"
        Assertions.assertThat(InfixCalculator.calculate(expression) == "6.0")
    }

}
