package com.iespino.pets.vistaFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.DB.BaseDatos;
import com.iespino.pets.R;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.presentador.IPerfilFragmentPresenter;
import com.iespino.pets.presentador.IRecyclerViewFragmentPresenter;
import com.iespino.pets.presentador.PerfilFragmentPresenter;
import com.iespino.pets.presentador.RecyclerViewFragmentPresenter;
import com.iespino.pets.restApi.deserializador.ProfilePhotoDeserializador;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PerfilFragment extends Fragment implements IPerfilFragmentView
{
    private RecyclerView recycler;
    private ArrayList<Pets> items;
    private IPerfilFragmentPresenter presentador;
    public String statusPreferencia;
    public static TextView tvPerfil;
    public ImageView imgPerfil;

    public static String nombre;
    BaseDatos acc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        presentador = new PerfilFragmentPresenter(this, getContext());
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.reciclador);
        tvPerfil = (TextView) v.findViewById(R.id.tvPerfil);
        tvPerfil.setText(String.valueOf(ProfilePhotoDeserializador.nombreCompleto));

        imgPerfil = (ImageView) v.findViewById(R.id.imgPerfil);

        Picasso.with(getActivity())
                .load(ProfilePhotoDeserializador.urlFoto)
                .placeholder(R.drawable.pet2)   //en caso de que no traiga imagen mostrar esta
                .into(imgPerfil);
        return  v;

    }

    @Override
    public void generarLinearLayoutVertical()
    {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recycler.setLayoutManager(llm);
    }

    @Override
    public void generarGridLayout()
    {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(gridLayoutManager);
    }

    @Override
    public Adapter crearAdaptador(ArrayList<Pets> items)
    {
        // Crear un nuevo adaptador
        Adapter adapter = new Adapter(items, getActivity());
        return adapter;
    }

    @Override
    public void inicializarAdaptadorRV(Adapter adapter)
    {
        recycler.setAdapter(adapter);
    }
}