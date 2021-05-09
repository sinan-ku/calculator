package katas.sinan.kheir

import org.springframework.stereotype.Component
import java.util.Stack

@Component
object InfixCalculator : Calculator {

    override fun calculate(expression: String): String {
        val paranthesesStack = Stack<String>()
        val argumentsStack = Stack<String>()
        var arguments = ArrayList(expression.split(" "))

        if(arguments[0].isANumber())
            return arguments[0]

        var numberOfArguments = arguments.size
        paranthesesStack.push(arguments[0])
        var index = 1
        while(index < numberOfArguments) {
            when {
                arguments[index] == "(" -> {
                    var paranthesesCounter = 1
                    var newExpression = "("
                    while(paranthesesCounter > 0) {
                        val arg = arguments[index+1]
                        newExpression = "$newExpression $arg"
                        if(arg == "(") {
                            paranthesesCounter++
                        } else if(arg == ")") {
                            paranthesesCounter--
                        }
                        index++
                    }
                    argumentsStack.push(this.calculate(newExpression))
                }
                arguments[index] == ")" -> {
                    if(argumentsStack.size == 1)
                        return argumentsStack.pop()
                    val arg1 = argumentsStack.pop()
                    val op = argumentsStack.pop()
                    val arg2 = argumentsStack.pop()
                    argumentsStack.push(executeOperation(op, arg1, arg2))
                    if(paranthesesStack.peek() == "(")
                        paranthesesStack.pop()
                }
                arguments[index].isANumber() -> {
                    argumentsStack.push(arguments[index])
                }
                arguments[index].isAnOperator() -> {
                    argumentsStack.push(arguments[index])
                }
            }
            index++
        }
        return argumentsStack.pop()
    }
}

private fun String.isANumber() = this.toIntOrNull() != null
private fun String.isAnOperator() = InfixCalculator.listOfOperations().contains(this)
