package br.usjt.arqdsis.clientep2.model;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ClienteAdapter extends BaseAdapter{
    Context context;
    Cliente[] clientes;
    public ClienteAdapter (Activity context, Cliente[] clientes){
        this.context = context;
        this.clientes = clientes;
    }
    @Override
    public int getCount() {
        return clientes.length;
    }

    @Override
    public Object getItem(int i) {
        if (i>=0 && i < clientes.length)
            return clientes[i];
        else
            return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
