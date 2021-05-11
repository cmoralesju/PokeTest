package stepDefinitions;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class StepDefinitions {
	String payload, pokemonName,pokemonId,hostName = "https://pokeapi.co/";
	JSONObject responseBodyJson, paramsJson;
	JSONArray params, pokemons;
	ResponseEntity<String> response;

	@Given("^I use \"([^\"]*)\" query param as \"([^\"]*)\"$")
	public void i_use_query_param_as(String param, String value) throws Throwable {
		if (params == null) params = new JSONArray();
		if (paramsJson == null) paramsJson = new JSONObject();
		params.put(param+"="+value);
		paramsJson.put(param, value);
	}

	@Given("^I want to get \"([^\"]*)\" information$")
	public void i_want_to_get_s_information(String name) throws Throwable {
		pokemonName = name;
	}
	
	@Given("^I get an existent Pokemon Name$")
	public void i_get_an_existent_Pokemon_Name() throws Throwable {
		pokemonName = PokeUtils.getRandomPokemonName();
	}

	@Given("^I get a non existent Pokemon Name$")
	public void i_get_a_non_existent_Pokemon_Name() throws Throwable {
		pokemonName = "xxxx";
	}

	@Given("^I get an existent Pokemon Id$")
	public void i_get_an_existent_Pokemon_Id() throws Throwable {
	   pokemonId = String.valueOf(PokeUtils.getRandomInt(1, 150));
	}

	@Given("^I get a non existent Pokemon Id$")
	public void i_get_a_non_existent_Pokemon_Id() throws Throwable {
		pokemonId ="0";
	}

	@Given("^I build a payload using Pokemon Name$")
	public void i_build_a_payload_using_Pokemon_Name() throws Throwable {
		payload = "api/v2/pokemon/"+pokemonName;
	}

	@Given("^I build a payload using Pokemon Id$")
	public void i_build_a_payload_using_Pokemon_Id() throws Throwable {
		payload = "api/v2/pokemon/"+pokemonId;
	}

	@Given("^I build a payload not using Pokemon Id neither Pokemon Name$")
	public void i_build_a_payload_not_using_Pokemon_Id_neither_Pokemon_Name() throws Throwable {
		payload = "api/v2/pokemon/";
		payload = PokeUtils.concatenateParams(payload, params == null?new JSONArray():params);
	}

	@When("^I send a request to retrieve Pokemon's data$")
	public void i_send_a_request_to_retrieve_Pokemon_s_data() throws Throwable {
		//TO DO put this functionality on a separate class
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new CustomResponseErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		response = restTemplate.exchange(hostName + payload, HttpMethod.GET, requestEntity, String.class);
	}

	@Then("^the response status should be (\\d+)$")
	public void the_response_status_should_be(int expStatusCode) throws Throwable {
		//Validate status code
		Assert.assertEquals(expStatusCode, response.getStatusCode().value());
	}

	@Then("^the response should contain Pokemon's name$")
	public void the_response_should_contain_Pokemon_s_name() throws Throwable {
		//Usually We compare against DB but in this case we validate the reponse contais the pokemon name
		responseBodyJson = new JSONObject(response.getBody());
		String actPokemonName = responseBodyJson.getString("name");
		Assert.assertEquals(pokemonName, actPokemonName);
	}

	@Then("^the response should contain Pokemon's id$")
	public void the_response_should_contain_Pokemon_s_id() throws Throwable {
		//Usually We compare against DB but in this case we validate the reponse contais the pokemon id
		responseBodyJson = new JSONObject(response.getBody());
		String actPokemonId = responseBodyJson.getString("id");
		Assert.assertEquals(pokemonId, actPokemonId);
	}

	@Then("^the response should display Not Found$")
	public void the_response_should_display_Not_Found() throws Throwable {
		Assert.assertEquals("Not Found", response.getBody());
	}

	@Then("^the response should contain (\\d+) Pokemon$")
	public void the_response_should_contain_Pokemon(int n) throws Throwable {
		responseBodyJson = new JSONObject(response.getBody());
		pokemons = responseBodyJson.getJSONArray("results");
		Assert.assertEquals(n, pokemons.length());
	}
	
	@Then("^the response should contain Pokemons between (\\d+) and (\\d+)$")
	public void the_response_should_contain_Pokemons_between_and(int expMin, int expMax) throws Throwable {
		if (pokemons == null) {
			responseBodyJson = new JSONObject(response.getBody());
			pokemons = responseBodyJson.getJSONArray("results");
		}
		//find max & min in the response
		JSONArray pokemonIds = new JSONArray();
		int max = 0, min = paramsJson != null && paramsJson.has("offset")? paramsJson.getInt("offset"):1, current;
		for (int i = 0; i < pokemons.length(); i++) {
			current = Integer.parseInt(pokemons.getJSONObject(i).getString("url")
					.replaceFirst("https:\\/\\/pokeapi.co\\/api\\/v2\\/pokemon\\/(\\d*)\\/", "$1"));
			if (max < current)
				max = current;
			if (min > current)
				min = current;
		}
		Assert.assertEquals(expMin,min);
		Assert.assertEquals(expMax,max);
		
	}


}
