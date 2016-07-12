package com.iespino.pets.restApi.adapter;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iespino.pets.restApi.ConstantesRestApi;
import com.iespino.pets.restApi.EndpointsApi;
import com.iespino.pets.restApi.deserializador.FollowsDeserializador;
import com.iespino.pets.restApi.deserializador.FollowsMediaRecentDeserializador;
import com.iespino.pets.restApi.deserializador.PerfilDeserializador;
import com.iespino.pets.restApi.deserializador.ProfilePhotoDeserializador;
import com.iespino.pets.restApi.model.PetsResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter
{
    public EndpointsApi establecerConexionRestApiInstagram(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetsResponse.class, new PerfilDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorInfoProfile()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetsResponse.class, new ProfilePhotoDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollows()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetsResponse.class, new FollowsDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowsMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PetsResponse.class, new FollowsMediaRecentDeserializador());

        return gsonBuilder.create();
    }

}
