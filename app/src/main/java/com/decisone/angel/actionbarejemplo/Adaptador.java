package com.decisone.angel.actionbarejemplo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by angel on 22/04/15.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.myDrawer> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private String name;        //String Resource for header View Name
    private int profile;        //int Resource for header view profile picture
    private String email;
    String titulos [];
    int iconos[];

    public Adaptador(String Titles[],int Icons[],String Name,String Email, int Profile){
        name=Name;
        profile=Profile;
        email=Email;
        titulos = Titles;
        iconos = Icons;
    }

    public static class myDrawer extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        boolean cabecera = true;

        public myDrawer(View v,int tipo){
            super(v);
            if(tipo == TYPE_HEADER){
                Name = (TextView)v.findViewById(R.id.name);
                email = (TextView)v.findViewById(R.id.email);
                profile = (ImageView)v.findViewById(R.id.circleView);
                cabecera=true;
            }else{
                textView = (TextView) v.findViewById(R.id.rowText);
                imageView = (ImageView) v.findViewById(R.id.rowIcon);
                cabecera = false;

            }

        }
    }

    @Override
    public myDrawer onCreateViewHolder(ViewGroup vG, int i){

        if(i==TYPE_ITEM) {
            View v = LayoutInflater.from(vG.getContext()).inflate(R.layout.item_row, vG, false);
            return new myDrawer(v,i);
        }else {
            View va = LayoutInflater.from(vG.getContext()).inflate(R.layout.header,vG,false);
            return new myDrawer(va,i);
        }

    }

    @Override
    public void onBindViewHolder(myDrawer holder, int position) {
        System.out.print("Posicion: "+position);

        if(holder.cabecera) {
            holder.profile.setImageResource(profile);           // Similarly we set the resources for header view
            holder.Name.setText(name);
            holder.email.setText(email);
        }else {
            holder.textView.setText(titulos[position-1]);
            holder.imageView.setImageResource(iconos[position-1]);
        }
    }
    @Override
    public int getItemCount() {
        return titulos.length+1; // the number of items in the list will be +1 the titles including the header view.
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }
}
