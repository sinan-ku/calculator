package katas.sinan.kheir

import org.springframework.stereotype.Component
import java.util.Stack

@Component
object PrefixCalculator : Calculator {

    override fun calculate(expression: String): String {
        val stack = Stack<String>()
        var arguments = ArrayList(expression.split(" "))

        var size = arguments.size
        while(size > 0) {
            val top = arguments[size - 1]

            if(listOfOperations().contains(top)) {
                val first = stack.pop()
                val second = stack.pop()
                stack.push(this.executeOperation(top, first, second))
            } else {
                stack.push(top);
            }
            arguments.removeAt(size - 1)
            size--
        }

        return stack.pop();
    }
}
