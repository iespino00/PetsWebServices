package com.iespino.pets.vistaFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.DB.BaseDatos;
import com.iespino.pets.R;
import com.iespino.pets.pojo.Pets;
import com.iespino.pets.presentador.IRecyclerViewFragmentPresenter;
import com.iespino.pets.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;


public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView
{
    private RecyclerView recycler;
    private ArrayList<Pets> items;
    private IRecyclerViewFragmentPresenter presentador;
    public String statusPreferencia;
    BaseDatos acc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.reciclador);
        presentador = new RecyclerViewFragmentPresenter(this, getContext(),0);

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
