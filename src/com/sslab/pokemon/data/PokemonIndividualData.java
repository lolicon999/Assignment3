

package com.sslab.pokemon.data;

/**
 * Created by jerry on 2017/3/21.
 */
public class PokemonIndividualData {
    //TODO create variables and constructor for this class
    public String nickName;
    public int ID;
    public int [] pokemonValueDate;

    public PokemonIndividualData()
    {
        this.nickName="";
        this.ID = 0;
        this.pokemonValueDate = new int[6];

    }

    public PokemonIndividualData(String nickName,int ID, int[] value)
    {
        this.nickName=nickName;
        this.ID = ID;
        this.pokemonValueDate = value.clone();
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getPokemonValueDate() {
        return pokemonValueDate;
    }

    public void setPokemonValueDate(int[] pokemonValueDate) {
        this.pokemonValueDate = pokemonValueDate;
    }
}
