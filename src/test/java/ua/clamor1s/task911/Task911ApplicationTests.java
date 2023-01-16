package ua.clamor1s.task911;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ua.clamor1s.task911.controller.CardController;
import ua.clamor1s.task911.dto.CardDetailsDto;
import ua.clamor1s.task911.dto.RestResponse;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = Task911Application.class)
@AutoConfigureMockMvc
class Task911ApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private CardController cardController;
	@Autowired
	private ObjectMapper objectMapper;

	@AfterEach
	public void afterEach() {
		cardController.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void testCreateCard() throws Exception {
		String name = "Adam";
		String surname = "Merser";
		String code = "8873482437";
		Integer cvv = 23562;
		String creationDate = "2022-11-16";

		String body = """
				{
				    "name": "%s",
				    "surname": "%s",
				    "code": "%s",
				    "cvv": %d,
				    "creationDate": "%s"
				}
				""".formatted(name, surname, code, cvv, creationDate);

		MvcResult mvcResult = mvc.perform(post("/api/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andExpect(status().isCreated())
				.andReturn();

		RestResponse response = parseResponse(mvcResult, RestResponse.class);
		int cardId = Integer.parseInt(response.getResult());
		assertThat(cardId).isGreaterThanOrEqualTo(1);

		try {
			CardDetailsDto dto = cardController.getCardById(cardId);
			assertThat(dto.getName()).isEqualTo(name);
			assertThat(dto.getSurname()).isEqualTo(surname);
			assertThat(dto.getCode()).isEqualTo(code);
			assertThat(dto.getCvv()).isEqualTo(cvv);
			assertThat(dto.getCreationDate().toString()).isEqualTo(creationDate);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	void testCreateCardBadRequest() throws Exception {
		mvc.perform(post("/api/cards")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
				.andExpect(status().isBadRequest());
	}



	private <T>T parseResponse(MvcResult mvcResult, Class<T> c) {
		try {
			return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
		} catch (JsonProcessingException | UnsupportedEncodingException e) {
			throw new RuntimeException("Error parsing json", e);
		}
	}

}
