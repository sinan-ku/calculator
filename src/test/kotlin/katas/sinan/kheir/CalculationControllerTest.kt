package katas.sinan.kheir

import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockkObject
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(CalculationController::class)
class CalculationControllerTest(@Autowired val mockMvc: MockMvc) {

    val prefixCalculator = mockkObject(PrefixCalculator)

    @Test
    fun `given expression, when called, should pass the expression to calculator and return result`() {
        val payload = HashMap<String, String>()
        payload["expression"] = "some expression"

        every { PrefixCalculator.calculate("some expression") } returns "5"

        mockMvc.perform(
            MockMvcRequestBuilders.post("/calculation/prefix")
                .content(ObjectMapper().writeValueAsString(payload))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("5"))

        verify { PrefixCalculator.calculate("some expression") }
    }

}
