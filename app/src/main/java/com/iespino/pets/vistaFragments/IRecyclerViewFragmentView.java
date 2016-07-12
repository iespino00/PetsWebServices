package com.iespino.pets.vistaFragments;

import com.iespino.pets.Adapter.Adapter;
import com.iespino.pets.pojo.Pets;

import java.util.ArrayList;

/**
 * Created by SAP RH on 2016/06/23.
 */
public interface IRecyclerViewFragmentView
{
    public void generarLinearLayoutVertical();

    public void generarGridLayout();

    public Adapter crearAdaptador(ArrayList<Pets> items);

    public void inicializarAdaptadorRV (Adapter adapter);
}
