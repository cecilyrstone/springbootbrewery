package com.cecilystone.breweryapi;

import com.cecilystone.breweryapi.controller.BreweryController;
import com.cecilystone.breweryapi.model.Brewery;
import com.cecilystone.breweryapi.service.BreweryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@AutoConfigureJsonTesters
@WebMvcTest(BreweryController.class)
class BreweryApiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private BreweryService breweryService;

	@Autowired
	private JacksonTester<Brewery> jsonBrewery;

	@Autowired
	private JacksonTester<Brewery[]> jsonBreweries;

	@Test
	public void canRetrieveByIdWhenExists() throws Exception {
		Brewery testBrewery = new Brewery();
		testBrewery.setId("black-acre");
		testBrewery.setName("Black Acre Brewing Company");
		var testResponse = new ResponseEntity<>(testBrewery, HttpStatus.OK);

		given(breweryService.getBrewery("black-acre"))
				.willReturn(testResponse);

		MockHttpServletResponse response = mvc.perform(
						get("/api/breweries/black-acre")
								.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonBrewery.write(testBrewery).getJson()
		);
	}

	@Test
	public void returnsNotFoundWhenNotExists() throws Exception {
		var testResponse = new ResponseEntity<>((Brewery)null, HttpStatus.NOT_FOUND);

		given(breweryService.getBrewery("black-acre"))
				.willReturn(testResponse);

		MockHttpServletResponse response = mvc.perform(
						get("/api/breweries/black-acre")
								.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEqualTo("");
	}

	@Test
	public void canSearchWhenExists() throws Exception {
		Brewery testBrewery = new Brewery();
		testBrewery.setId("silent-bob-brewing");
		testBrewery.setName("Silent Bob's Brewing Company");
		Brewery testBrewery2 = new Brewery();
		testBrewery2.setId("silent-bobs-cousin-brewing");
		testBrewery2.setName("Silent Bob's Cousin's Brewing Company");
		var breweryTestResults = new Brewery[] {testBrewery, testBrewery2};
		var testResponse = new ResponseEntity<>(breweryTestResults, HttpStatus.OK);

		given(breweryService.searchBreweries("bob"))
				.willReturn(testResponse);

		MockHttpServletResponse response = mvc.perform(
						get("/api/breweries/search/bob")
								.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(
				jsonBreweries.write(breweryTestResults).getJson()
		);
	}

	@Test
	public void returnsNotFoundWhenNoSearchResultsExist() throws Exception {
		var testResponse = new ResponseEntity<>((Brewery[])null, HttpStatus.NOT_FOUND);

		given(breweryService.searchBreweries("bob"))
				.willReturn(testResponse);

		MockHttpServletResponse response = mvc.perform(
						get("/api/breweries/search/bob")
								.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
		assertThat(response.getContentAsString()).isEqualTo("");
	}
}
