package com.iespino.pets.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.DB.BaseDatos;
import com.iespino.pets.DB.ConstructorPets;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.restApi.EndpointsApi;
import com.iespino.pets.restApi.adapter.RestApiAdapter;
import com.iespino.pets.restApi.model.PetsResponse;
import com.iespino.pets.vistaFragments.IRecyclerViewFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter
{
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private  Context context;
    private ConstructorPets constructorPets;
    private ArrayList<Pets> items;
    private BaseDatos db;
    public String id;
    public String username;
    SharedPreferences PreferenciaFollows;
    SharedPreferences.Editor editor;
    private  ArrayList<Pets> FollowsMedia;

    public RecyclerViewFragmentPresenter (IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context, int arra)
    {
        this.iRecyclerViewFragmentView= iRecyclerViewFragmentView;
        this.context=context;
        obtenerFollows();

    }


    @Override
    public void InicializarMascotas()
    {
        constructorPets = new ConstructorPets(context);
        items = constructorPets.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV()
    {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(items));
        iRecyclerViewFragmentView.generarGridLayout();
    }

    @Override
    public void arranques()
    {
        db = new BaseDatos(context);
        items = db.getAllPets();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerFollows()
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollows = restApiAdapter.construyeGsonDeserializadorFollows();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollows);
        Call<PetsResponse> FollowsResponseCall = endpointsApi.getFollows();

        FollowsResponseCall.enqueue(new Callback<PetsResponse>()
        {
            @Override
            public void onResponse(Call<PetsResponse> call, Response<PetsResponse> response)
            {
                PetsResponse petsResponse = response.body();
                items =  petsResponse.getMascotas();
                //Toast.makeText(context, "Se obtuvieron los Follows", Toast.LENGTH_LONG).show();
                //Creo la preferencia para almacenar los ids y nombres de mis follows.
                PreferenciaFollows = context.getSharedPreferences("FollowsSandbox", Context.MODE_PRIVATE);
                editor = PreferenciaFollows.edit();
                editor.putString("id","");
                editor.putString("username","");
                editor.commit();

                for (int i = 0; i <items.size() ; i++)
                {
                    id = items.get(i).getId();
                    username = items.get(i).getNombreCompleto();
                    obtenerMediaRecentFollows();
                    crearPreferencia();
                }
                items.clear();
            }

            @Override
            public void onFailure(Call<PetsResponse> call, Throwable t)
            {
                Toast.makeText(context, "Algo paso en la conexión¡¡ Intenta de Nuevo", Toast.LENGTH_LONG).show();
                Log.e("Fallo la Conexión", t.toString());
            }
        });
    }

    @Override
    public void obtenerMediaRecentFollows()
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonFollowsMediaRecent = restApiAdapter.construyeGsonDeserializadorFollowsMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFollowsMediaRecent);
        Call<PetsResponse> FollowsMediaRecentResponseCall = endpointsApi.getFollowsMediaRecent(id);

        FollowsMediaRecentResponseCall.enqueue(new Callback<PetsResponse>()
        {
            @Override
            public void onResponse(Call<PetsResponse> call, Response<PetsResponse> response)
            {
                PetsResponse petsResponse = response.body();
                FollowsMedia =  petsResponse.getMascotas();
                items.addAll(FollowsMedia);
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<PetsResponse> call, Throwable t)
            {
                Toast.makeText(context, "Error al cargar las fotos recientes de tus amigos", Toast.LENGTH_LONG).show();
                Log.e("Fallo la Conexión", t.toString());
            }
        });
    }

    @Override
    public void crearPreferencia()
    {
        editor.putString("id",PreferenciaFollows.getString("id","")+id+",");
        editor.putString("username",PreferenciaFollows.getString("username","")+username+",");
        editor.commit();
    }
}
