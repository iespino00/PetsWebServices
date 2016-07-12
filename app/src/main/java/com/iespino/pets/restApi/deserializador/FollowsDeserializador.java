package com.iespino.pets.restApi.deserializador;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.restApi.JsonKeys;
import com.iespino.pets.restApi.model.PetsResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FollowsDeserializador implements JsonDeserializer<PetsResponse>
{

    @Override
    public PetsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        Gson gson = new Gson();
        PetsResponse petsResponse = gson.fromJson(json, PetsResponse.class);

        JsonArray petsResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        petsResponse.setMascotas(deserealizarFollowsDeJson(petsResponseData));
        return petsResponse;

    }

    private ArrayList<Pets> deserealizarFollowsDeJson(JsonArray petsResponseData)
    {
        ArrayList<Pets> mascotas = new ArrayList<>();

        for (int i = 0; i <petsResponseData.size() ; i++)
        {
            JsonObject petsResponseDataObject = petsResponseData.get(i).getAsJsonObject(); //entramos a los objetos dentro de data
            String id                         = petsResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username                   = petsResponseDataObject.get(JsonKeys.USERNAME).getAsString();


       Pets followActual = new Pets();
            followActual.setId(id);
            followActual.setNombreCompleto(username);

            mascotas.add(followActual);
        }
        return mascotas;
    }
}
