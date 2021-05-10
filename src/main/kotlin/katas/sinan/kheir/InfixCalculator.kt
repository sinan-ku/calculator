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
                        paranthesesCounter = countParantheses(arg, paranthesesCounter)
                        index++
                    }
                    argumentsStack.push(this.calculate(newExpression))
                }
                arguments[index] == ")" -> {
                    if(argumentsStack.size == 1)
                        return argumentsStack.pop()
                    executeTopMostOperationOnStack(argumentsStack)
                    popOpenParanthesesFromStack(paranthesesStack)
                }
                arguments[index].isANumber() || arguments[index].isAnOperator() -> {
                    argumentsStack.push(arguments[index])
                }
            }
            index++
        }
        return argumentsStack.pop()
    }

    private fun countParantheses(arg: String?, paranthesesCounter: Int): Int {
        var paranthesesCounter1 = paranthesesCounter
        if (arg == "(") {
            paranthesesCounter1++
        } else if (arg == ")") {
            paranthesesCounter1--
        }
        return paranthesesCounter1
    }

    private fun popOpenParanthesesFromStack(paranthesesStack: Stack<String>) {
        if (paranthesesStack.peek() == "(")
            paranthesesStack.pop()
    }

    private fun executeTopMostOperationOnStack(argumentsStack: Stack<String>) {
        val arg1 = argumentsStack.pop()
        val op = argumentsStack.pop()
        val arg2 = argumentsStack.pop()
        argumentsStack.push(executeOperation(op, arg1, arg2))
    }
}

private fun String.isANumber() = this.toIntOrNull() != null
private fun String.isAnOperator() = InfixCalculator.listOfOperations().contains(this)
