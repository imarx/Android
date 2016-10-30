package br.usjt.arqdsis.clientep3.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.usjt.arqdsis.clientep3.R;
import br.usjt.arqdsis.clientep3.model.Cliente;
import br.usjt.arqdsis.clientep3.model.ClienteAdapter;
import br.usjt.arqdsis.clientep3.model.ClienteRequester;

public class ListaClientesActivity extends AppCompatActivity {

    public static final String NOME = "br.usjt.arqdsis.clientep2.nome";
    public static final String EMAIL = "br.usjt.arqdsis.clientep2.email";
    public static final String FONE = "br.usjt.arqdsis.clientep2.fone";
    Activity atividade;
    ArrayList<Cliente> lista;
    ClienteRequester requester = new ClienteRequester();
    String chave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);
        atividade = this;
        Intent intent = getIntent();
        lista = (ArrayList<Cliente>)intent.getSerializableExtra(MainActivity.LISTA);
        BaseAdapter adapter = new ClienteAdapter(this, lista.toArray(new Cliente[0]));
        ListView listView = (ListView) findViewById(R.id.lista_clientes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // manda para a tela de detalhe
                Intent intent = new Intent(atividade, DetalheClienteActivity.class);
                intent.putExtra(NOME, lista.get(position).getNome());
                intent.putExtra(FONE, lista.get(position).getFone());
                intent.putExtra(EMAIL, lista.get(position).getEmail());

                startActivity(intent);

            }

        });

    }


}
