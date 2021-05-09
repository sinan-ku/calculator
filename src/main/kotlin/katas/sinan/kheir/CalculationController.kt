package katas.sinan.kheir

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
class CalculationController {

    private val EXPRESSION_ATTR_KEY: String = "expression"

    @PostMapping(path = ["/calculation/{notationType}"], consumes = ["application/json"], produces = ["application/json"])
    fun calculate(@RequestBody payload: Map<String, String>, @PathVariable notationType: String): ResponseEntity<String> {

        val expression = payload[EXPRESSION_ATTR_KEY]
            ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please provide a JSON payload with 'expression'")

        return when(notationType) {
            "infix" -> ResponseEntity.of(Optional.of(InfixCalculator.calculate(expression)))
            "prefix" -> ResponseEntity.of(Optional.of(PrefixCalculator.calculate(expression)))
            else -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass 'infix' or 'prefix' as notationType in URL")
        }
    }

}
