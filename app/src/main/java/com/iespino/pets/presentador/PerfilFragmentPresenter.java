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
import com.iespino.pets.vistaFragments.IPerfilFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragmentPresenter implements IPerfilFragmentPresenter
{
    private IPerfilFragmentView iPerfilFragmentView;
    private  Context context;
    private ConstructorPets constructorPets;
    private ArrayList<Pets> items;
    private BaseDatos db;
    public static  String id,m;
    SharedPreferences sPreferenciaID;
    public static String v1,v2;



    public PerfilFragmentPresenter (IPerfilFragmentView iPerfilFragmentView, Context context)
    {
        this.iPerfilFragmentView= iPerfilFragmentView;
        this.context=context;
        sPreferenciaID = context.getSharedPreferences("IdSearch", Context.MODE_PRIVATE);
        obtenerInfoProfile();
        obtenerMediosRecientes();
    }

    @Override
    public void InicializarMascotas()
    {
        constructorPets = new ConstructorPets(context);
        items = constructorPets.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMediosRecientes()
    {

       // String par = sPreferenciaID.getString("id","");
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<PetsResponse> mascotaResponseCall = endpointsApi.getRecentMedia(sPreferenciaID.getString("id","3439512068"));

        mascotaResponseCall.enqueue(new Callback<PetsResponse>()
        {
            @Override
            public void onResponse(Call<PetsResponse> call, Response<PetsResponse> response)
            {
                //response, trae lo que esta contenido en body, en su caso el data del json
                // y eso lo pasamos a ContactoResponse...nuestro modelo de mascotas
                PetsResponse petsResponse = response.body();
                items =  petsResponse.getMascotas();
                mostrarMascotasRV();
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
    public void obtenerInfoProfile()
    {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonInfoProfile = restApiAdapter.construyeGsonDeserializadorInfoProfile();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonInfoProfile);
        Call<PetsResponse> mascotaResponseCall = endpointsApi.getProfile(sPreferenciaID.getString("id","3439512068"));

        mascotaResponseCall.enqueue(new Callback<PetsResponse>()
        {
            @Override
            public void onResponse(Call<PetsResponse> call, Response<PetsResponse> response)
            {
                PetsResponse petsResponse = response.body();
                items =  petsResponse.getMascotas();
            }

            @Override
            public void onFailure(Call<PetsResponse> call, Throwable t)
            {
                Toast.makeText(context, "ERROR AL CARGAR FOTO DE PERFIL", Toast.LENGTH_LONG).show();
                Log.e("Fallo la Conexión", t.toString());
            }
        });
    }


    @Override
    public void mostrarMascotasRV()
    {
        iPerfilFragmentView.inicializarAdaptadorRV(iPerfilFragmentView.crearAdaptador(items));
        iPerfilFragmentView.generarGridLayout();
    }


}
