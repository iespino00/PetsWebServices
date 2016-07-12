package com.iespino.pets.restApi.model;


import com.iespino.pets.pojo.Pets;

import java.util.ArrayList;

public class PetsResponse
{
ArrayList <Pets> mascotas;


    public ArrayList<Pets> getMascotas()
    {
        return mascotas;
    }

    public void setMascotas(ArrayList<Pets> mascotas)
    {
        this.mascotas = mascotas;
    }
}
