package com.iespino.pets;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class Contacto extends AppCompatActivity implements View.OnClickListener
{


    private TextView txtbar;
    ImageView imgTop;
    private String email;
    private String asunto;
    private String mensaje;
    private EditText tiContacto_nombre;
    private EditText tiContacto_email;
    private EditText tiContacto_mensaje;
    private Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        tiContacto_nombre= (EditText) findViewById(R.id.tiContacto_nombre);
        tiContacto_email= (EditText) findViewById(R.id.tiContacto_email);
        tiContacto_mensaje= (EditText) findViewById(R.id.tiContacto_mensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(this);

        txtbar=(TextView) findViewById(R.id.txtbar);
        txtbar.setText("Contacto");

        imgTop = (ImageView) findViewById(R.id.imgtop);
        imgTop.setVisibility(View.INVISIBLE);

        Toolbar ActionBar= (Toolbar)findViewById(R.id.ActionBar);
        setSupportActionBar(ActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setLogo(R.drawable.cat_footprint_48);
    }

    public  void  sendMail()
    {
         email = tiContacto_email.getText().toString().trim();
         asunto = tiContacto_nombre.getText().toString().trim();
         mensaje = tiContacto_mensaje.getText().toString().trim();
        SendMail sm = new SendMail(this, email, asunto, mensaje);
        sm.execute();

        tiContacto_nombre.setText("");
        tiContacto_email.setText("");
        tiContacto_mensaje.setText("");

    }

    @Override
    public void onClick(View v)
    {
     sendMail();

    }
}
