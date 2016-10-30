package br.usjt.arqdsis.clientep3.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import br.usjt.arqdesis.clientep3.R;
import br.usjt.arqdesis.clientep3.model.Cliente;
import br.usjt.arqdesis.clientep3.model.ClienteRequester;

public class DetalheClienteActivity extends AppCompatActivity {
    ImageView clienteImageView;
    Cliente cliente;
    ClienteRequester requester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        Intent intent = getIntent();
        cliente = new Cliente(1, intent.getStringExtra(ListaClientesActivity.NOME),
                intent.getStringExtra(ListaClientesActivity.FONE),
                intent.getStringExtra(ListaClientesActivity.EMAIL));
        clienteImageView = (ImageView) findViewById(R.id.cliente_image_view);
        //Drawable drawable = Util.getDrawable(this, cliente.getFoto());

        requester = new ClienteRequester();
        if(requester.isConnected(this)) {
            //usando AsyncTask - veja a class DownloadImageTask abaixo
            new DowloadImageTask().execute();

        } else {
            Toast toast = Toast.makeText(this, "Rede indisponível", Toast.LENGTH_LONG);
            toast.show();
        }
        TextView nome = (TextView)findViewById(R.id.txt_cliente_nome);
        TextView fone = (TextView)findViewById(R.id.txt_cliente_fone);
        TextView email = (TextView)findViewById(R.id.txt_cliente_email);

        nome.setText(cliente.getNome());
        fone.setText(cliente.getFone());
        email.setText(cliente.getEmail());
    }

    private class DowloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap img = null;
            try {
                img = requester.getImage(MainActivity.SERVIDOR +
                        MainActivity.APLICACAO + "/img/" + cliente.getFoto() + ".jpg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return img;
        }

        @Override
        protected void onPostExecute(Bitmap img){
            clienteImageView.setImageBitmap(img);
        }
    }
}


