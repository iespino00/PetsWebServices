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

public class FollowsMediaRecentDeserializador implements JsonDeserializer<PetsResponse>
{

    @Override
    public PetsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        Gson gson = new Gson();
        PetsResponse petsResponse = gson.fromJson(json, PetsResponse.class);

        JsonArray petsResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        petsResponse.setMascotas(deserealizarMediaFollowsDeJson(petsResponseData));
        return petsResponse;
    }

    private ArrayList<Pets> deserealizarMediaFollowsDeJson (JsonArray petsResponseData)
    {
        ArrayList<Pets> mascotas = new ArrayList<>();

        for (int i = 0; i <petsResponseData.size() ; i++)
        {
         JsonObject petsResponseDataObject = petsResponseData.get(i).getAsJsonObject(); //entramos a los objetos dentro de data
            JsonObject userJson                   = petsResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id                             = userJson.get(JsonKeys.USER_ID).getAsString();

            JsonObject imageJson                  = petsResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson          = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String     urlFoto                    = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson                  = petsResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int        likes                      = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

         Pets MediafollowActual = new Pets();
         MediafollowActual.setId(id);
         MediafollowActual.setUrlFoto(urlFoto);
         MediafollowActual.setLikes(likes);

         mascotas.add(MediafollowActual);
        }
        return mascotas;
    }
}
