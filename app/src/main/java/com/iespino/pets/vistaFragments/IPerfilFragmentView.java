package com.iespino.pets.vistaFragments;


import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.pojo.Pets;

import java.util.ArrayList;

public interface IPerfilFragmentView
{
    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public Adapter crearAdaptador(ArrayList<Pets> items);

    public void inicializarAdaptadorRV (Adapter adapter);
}
