package br.usjt.arqdsis.clientep3.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by asbonato on 10/10/16.
 */

public class ClienteRequester {
    OkHttpClient client = new OkHttpClient();


    public ArrayList<Cliente> get (String url, String chave) throws IOException{
        ArrayList<Cliente> lista = new ArrayList<>();
        System.out.println(url);
       // FormBody formBody = new FormBody.Builder().add("chave", chave).build();
        //System.out.println(formBody);
        //Request request = new Request.Builder().url(url).post(formBody).build();
        Request request = new Request.Builder().url(url).build();
        System.out.println(request);
        Response response = client.newCall(request).execute();
        System.out.println(response);
        String jsonString = response.body().string();
        System.out.println(jsonString);
        try{
            JSONArray root = new JSONArray(jsonString);
            JSONObject item = null;
            for(int i = 0; i < root.length(); i++){
                item = (JSONObject)root.get(i);
                String nome = item.getString("nome");
                String fone = item.getString("fone");
                String email = item.getString("email");
                int id = item.getInt("id");

                lista.add(new Cliente(id, nome, fone, email));
            }
        } catch (JSONException e){
            throw new IOException(e);
        } finally {
            if(lista.size() == 0){
                lista.add(new Cliente());
            }
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException{
        Bitmap img = null;

        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
