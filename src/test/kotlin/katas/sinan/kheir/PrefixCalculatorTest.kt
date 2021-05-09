package katas.sinan.kheir

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PrefixCalculatorTest {

    @Test
    fun `given single operation, should calculate and return result`() {
        val expression = "+ 1 2"
        assertThat(PrefixCalculator.calculate(expression) == "3")
    }

    @Test
    fun `given single number, should the number`() {
        val expression = "5"
        assertThat(PrefixCalculator.calculate(expression) == "5")
    }

    @Test
    fun `given nested expression, should calculate with precedence and return result`() {
        val expression = "- / 10 + 1 1 * 1 2"
        assertThat(PrefixCalculator.calculate(expression) == "3")
    }

    @Test
    fun `given expression with negative result, should calculate and return negative result`() {
        val expression = "- 0 3"
        assertThat(PrefixCalculator.calculate(expression) == "-3")
    }

}
