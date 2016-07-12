package com.iespino.pets.presentador;

/**
 * Created by SAP RH on 2016/06/23.
 */
public interface IRecyclerViewFragmentPresenter
{
    public void InicializarMascotas();
    public void mostrarMascotasRV();
    public void arranques();

    public void obtenerFollows();
    public void obtenerMediaRecentFollows();

    public void crearPreferencia();
}
