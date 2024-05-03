package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parcial2.adaptadores.UsuarioAdaptador;
import com.example.parcial2.clases.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements UsuarioAdaptador.OnItemClickListener{

    public static final String dataUser = "dataUser";
    private static final int modoPrivate = Context.MODE_PRIVATE;
    TextView txtUser;
    String dato;
    RecyclerView rcv_personajes;
    List<Person> personajeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtUser = findViewById(R.id.txtUser);
        dato = getApplicationContext().getSharedPreferences(dataUser, modoPrivate).getString("user", "0");
        if (!dato.equals("0"))
            txtUser.setText(dato);
        else
            txtUser.setText("sin informacion");

        rcv_personajes = findViewById(R.id.rcv_personajes);

        Person user1 = new Person("https://s.hs-data.com/bilder/spieler/gross/13029.jpg", "cristiano", "al-nassar");
        Person user2 = new Person("https://s.france24.com/media/display/451ed2b8-eed6-11ea-afdd-005056bf87d6/w:1280/p:4x3/messi-1805.jpg", "messi", "barcelona");
        Person per3 = new Person("https://assets.goal.com/images/v3/blt3959cad0aaf9e1b0/twitter_F3l7Vq4WYAIOMHb_(1).jpg?auto=webp&format=pjpg&width=3840&quality=60", "neymar", "al-hilal");
        Person per4 = new Person("https://static.independent.co.uk/2024/02/14/15/newFile-3.jpg", "palmer", "chelsea");


        personajeList.add(user1);
        personajeList.add(user2);
        personajeList.add(per3);
        personajeList.add(per4);


        rcv_personajes.setLayoutManager(new LinearLayoutManager(this));
        rcv_personajes.setAdapter(new UsuarioAdaptador(personajeList));

        UsuarioAdaptador adaptador = new UsuarioAdaptador(personajeList);
        rcv_personajes.setAdapter(adaptador);
        adaptador.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(Person personaje) {
        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        i.putExtra("personaje_nombre", personaje.getNombre());
        i.putExtra("personaje_programa", personaje.getPrograma());
        i.putExtra("personaje_imagen", personaje.getImagen());
        startActivity(i);
        finish();
    }
}