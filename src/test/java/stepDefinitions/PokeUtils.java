package stepDefinitions;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONException;

public class PokeUtils {

	public static String getRandomPokemonName() {
		
		String pokemonNamesString = "bulbasaur,ivysaur,venusaur,charmander,charmeleon,charizard,squirtle,wartortle,blastoise,"
				+ "caterpie,metapod,butterfree,weedle,kakuna,beedrill,pidgey,pidgeotto,pidgeot,rattata,raticate,spearow,"
				+ "fearow,ekans,arbok,pikachu,raichu,sandshrew,sandslash,nidoran,nidorina,nidoqueen,nidoran,nidorino,"
				+ "nidoking,clefairy,clefable,vulpix,ninetales,jigglypuff,wigglytuff,zubat,golbat,oddish,gloom,vileplume,"
				+ "paras,parasect,venonat,venomoth,diglett,dugtrio,meowth,persian,psyduck,golduck,mankey,primeape,growlithe,"
				+ "arcanine,poliwag,poliwhirl,poliwrath,abra,kadabra,alakazam,machop,machoke,machamp,bellsprout,weepinbell,"
				+ "victreebel,tentacool,tentacruel,geodude,graveler,golem,ponyta,rapidash,slowpoke,slowbro,magnemite,magneton,"
				+ "farfetch'd,doduo,dodrio,seel,dewgong,grimer,muk,shellder,cloyster,gastly,haunter,gengar,onix,drowzee,hypno,"
				+ "krabby,kingler,voltorb,electrode,exeggcute,exeggutor,cubone,marowak,hitmonlee,hitmonchan,lickitung,koffing,"
				+ "weezing,rhyhorn,rhydon,chansey,tangela,kangaskhan,horsea,seadra,goldeen,seaking,staryu,starmie,mr. mime,scyther,"
				+ "jynx,electabuzz,magmar,pinsir,tauros,magikarp,gyarados,lapras,ditto,eevee,vaporeon,jolteon,flareon,porygon,omanyte,"
				+ "omastar,kabuto,kabutops,aerodactyl,snorlax,articuno,zapdos,moltres,dratini,dragonair,dragonite,mewtwo,mew";
		
		JSONArray pokemonNames = new JSONArray(Arrays.asList(pokemonNamesString.split(",")));
		try {
			return pokemonNames.get(getRandomInt(1, 150)-1).toString();
			
		} catch (JSONException e) {
			return "bulbasaur";
		}
	}

	public static int getRandomInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min,max+1);
	}
	
	public static String concatenateParams(String api, JSONArray params) {
		System.out.println(params);
		if(params.length() > 0) {
			try {
				api = api.replaceAll("/$", "");
				api = api + "?";
				for (int i=0; i<params.length();i++) {
					if (i!=0)
						api = api+"&";
					api=api+params.get(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		System.out.println(api);
		return api;
	}
}
