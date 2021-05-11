@PokeTest
Feature: Poke Test

	Scenario: Get an existent specific Pokemon Information by Name
		Given I want to get "charmander" information
		And I build a payload using Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain Pokemon's name

	Scenario: Get an existent Pokemon Information by Name
		Given I get an existent Pokemon Name
		And I build a payload using Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain Pokemon's name

	Scenario: Get a non existent Pokemon Information by Name
		Given I get a non existent Pokemon Name
		And I build a payload using Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 404
		And the response should display Not Found

	Scenario: Get an existent Pokemon Information by Id
		Given I get an existent Pokemon Id
		And I build a payload using Pokemon Id
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain Pokemon's id

	Scenario: Get a non existent Pokemon Information by Id
		Given I get a non existent Pokemon Id
		And I build a payload using Pokemon Id
		When I send a request to retrieve Pokemon's data
		Then the response status should be 404
		And the response should display Not Found

	Scenario: Get Pokemons not using Pokemon Id neither Pokemon Name
		Given I build a payload not using Pokemon Id neither Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain 20 Pokemon
		And the response should contain Pokemons between 1 and 20

	Scenario: Get Pokemons not using Pokemon Id neither Pokemon Name, using limit param
		Given I use "limit" query param as "40"
		And I build a payload not using Pokemon Id neither Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain 40 Pokemon
		And the response should contain Pokemons between 1 and 40

	Scenario: Get Pokemons not using Pokemon Id neither Pokemon Name, using limit & obset param
		Given I use "offset" query param as "20"
		Given I use "limit" query param as "40"
		And I build a payload not using Pokemon Id neither Pokemon Name
		When I send a request to retrieve Pokemon's data
		Then the response status should be 200
		And the response should contain 40 Pokemon
		And the response should contain Pokemons between 20 and 60
	
	