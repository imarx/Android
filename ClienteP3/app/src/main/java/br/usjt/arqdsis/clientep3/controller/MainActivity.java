package br.usjt.arqdsis.clientep3.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import br.usjt.arqdsis.clientep3.R;
import br.usjt.arqdsis.clientep3.model.Cliente;
import br.usjt.arqdsis.clientep3.model.ClienteRequester;

public class MainActivity extends AppCompatActivity {
    private EditText nome;
    ArrayList<Cliente> lista;
    ClienteRequester requester;
    Intent intent;
    String chave;
    ProgressBar progressBar;
    public static final String SERVIDOR = "http://192.168.1.104:8080";
    public static final String APLICACAO = "/arqdsis_poetas";
    private final String RECURSO = "/cliente";

    //private final String SERVIDOR = "http://10.0.2.2:8080/arqdsis_poetas/cliente";
    //private final String SERVIDOR = "http://www.qpainformatica.com.br/exemplorest/rest/poeta/todos";
    public static final String LISTA = "br.usjt.arqdsis.clientep2.lista";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = (EditText)findViewById(R.id.busca_nome_cliente);
        progressBar = (ProgressBar)findViewById(R.id.timer);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void buscarClientes(View view){
        chave = nome.getText().toString();
        requester = new ClienteRequester();
        intent = new Intent(this, ListaClientesActivity.class);
        if(requester.isConnected(this)) {
            progressBar.setVisibility(View.VISIBLE);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = requester.get(SERVIDOR + APLICACAO + RECURSO, chave);
                        runOnUiThread(new Runnable(){
                            @Override
                            public void run(){
                                intent.putExtra(LISTA, lista);
                                startActivity(intent);
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível", Toast.LENGTH_LONG);
            toast.show();
        }
    }

}
