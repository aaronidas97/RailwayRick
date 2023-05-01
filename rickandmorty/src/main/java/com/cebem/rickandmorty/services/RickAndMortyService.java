package com.cebem.rickandmorty.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebem.rickandmorty.models.CharacterModel;
import com.cebem.rickandmorty.models.CharactersModel;
import com.cebem.rickandmorty.models.CharactersSize;
import com.cebem.rickandmorty.utils.Utils;

@Service
public class RickAndMortyService {

    @Autowired
    RestTemplate restTemplate;

    final static String BASE_API = "https://rickandmortyapi.com/api";
    public CharacterModel getCharacterRandom() {
        final int TOTAL_CHARACTERS = 826;
        int random = Utils.getRandom(TOTAL_CHARACTERS);
        String url = "/character/" + random;
        CharacterModel datos = restTemplate.getForObject(BASE_API +url, CharacterModel.class);
        return datos;
    }

    public CharactersModel getAllCharacters() {
        String url = "/character/";
        CharactersModel chsm = restTemplate.getForObject(BASE_API +url, CharactersModel.class);
        return chsm;
    }
    // public CharactersModel getAllCharactersPage() {
    //     String url = "https://rickandmortyapi.com/api/character/?page=";
    //     url += 
    //     CharactersModel chsm = restTemplate.getForObject(url, CharactersModel.class);
    //     return chsm;
    // }

    public int getCharactersCount() {
        String url = "/character/";
        CharactersSize cs = restTemplate.getForObject(BASE_API +url, CharactersSize.class);
        return cs.info.count;
    }

}
