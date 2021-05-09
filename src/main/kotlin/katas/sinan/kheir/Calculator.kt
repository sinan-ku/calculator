package katas.sinan.kheir

interface Calculator {

    fun listOfOperations() = listOf("+", "-", "*", "/")

    fun calculate(expression: String): String

    fun executeOperation(opType: String, arg1: String, arg2: String): String {
        return when(opType) {
            "+" -> arg1.toDouble() + arg2.toDouble()
            "-" -> arg1.toDouble() - arg2.toDouble()
            "*" -> arg1.toDouble() * arg2.toDouble()
            "/" -> arg1.toDouble() / arg2.toDouble()
            else -> throw RuntimeException()
        }.toInt().toString()
    }
}
