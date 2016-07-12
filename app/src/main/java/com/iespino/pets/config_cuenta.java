package com.iespino.pets;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iespino.pets.presentador.IPerfilFragmentPresenter;
import com.iespino.pets.presentador.PerfilFragmentPresenter;
import com.iespino.pets.vistaFragments.PerfilFragment;

public class config_cuenta extends AppCompatActivity
{
    private Context context;
    private TextView txtbar;
    ImageView imgTop;
    EditText tiUsuario;
    public static String usuario;
    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_cuenta);
        txtbar     =(TextView) findViewById(R.id.txtbar);
        txtbar.setText("Configuración de la Cuenta");
        imgTop = (ImageView) findViewById(R.id.imgtop);
        imgTop.setVisibility(View.INVISIBLE);
        Toolbar ActionBar= (Toolbar)findViewById(R.id.ActionBar);
        setSupportActionBar(ActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.cat_footprint_48);
    }


    public void buscarUsuario(View view)
    {
        tiUsuario  = (EditText) findViewById(R.id.tiUsuario);
        usuario = tiUsuario.getText().toString();
        readFollows();
    }

    public void readFollows()
    {   SharedPreferences FollowsPreferencia= getSharedPreferences("FollowsSandbox", Context.MODE_PRIVATE);
        String ArregloUserName[]= FollowsPreferencia.getString("username","").split(",");
        String ArregloId[]= FollowsPreferencia.getString("id","").split(",");

        for (int i = 0; i <ArregloUserName.length ; i++)
        {    x= 1;
            if (usuario.equals(ArregloUserName[i]))
            {    PerfilFragmentPresenter.id = ArregloId[i];
               // Toast.makeText(config_cuenta.this, PerfilFragmentPresenter.id, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(config_cuenta.this, MainActivity.class);
                intent.putExtra("id",ArregloId[i]);
                startActivity(intent);
                finish();

              //  Intent intent = new Intent(ConfigurarCuentaActivity.this, MainActivity.class);
             //   startActivity(intent);
            }
            else
            {
                x= 0;
            }
        }
        if(x == 0)
        {
            Toast.makeText(config_cuenta.this," No existe: "+usuario+ " como usuario Sandbox", Toast.LENGTH_LONG).show();
        }

    }


}
