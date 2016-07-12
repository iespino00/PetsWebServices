package com.iespino.pets.restApi.deserializador;

import android.util.Log;
import android.widget.Toast;

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
import com.iespino.pets.vistaFragments.PerfilFragment;

import java.lang.reflect.Type;
import java.util.ArrayList;




public class ProfilePhotoDeserializador implements JsonDeserializer<PetsResponse>
{
    public static String nombreCompleto;
    public static String urlFoto;
    @Override
    public PetsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        Gson gson = new Gson();
        PetsResponse petsResponse = gson.fromJson(json, PetsResponse.class);
       // JsonArray petsResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        JsonObject data = json.getAsJsonObject().getAsJsonObject(JsonKeys.DATA);
        petsResponse.setMascotas(deserealizarFotoDeJson(data));
        return petsResponse;
    }

    private ArrayList<Pets> deserealizarFotoDeJson(JsonObject petsResponseData)
    {
        ArrayList<Pets> mascotas = new ArrayList<>();

          nombreCompleto                 = petsResponseData.get(JsonKeys.USER_FULLNAME).getAsString();
          urlFoto                        =petsResponseData.get(JsonKeys.PROFILE_PICTURE).getAsString();


            Pets profile = new Pets();
            profile.setNombreCompleto(nombreCompleto);
            mascotas.add(profile);


        return mascotas;
    }


}
