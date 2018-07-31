package com.victoriaramirezcharles.pelicula;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adaptador extends  RecyclerView.Adapter<Adaptador.MyviewHolder>{

    private OnItemClickListener mListener;
    Context context;
    List<Pelicula> listaPeliculas;

    public Adaptador(Context context, List<Pelicula> listaPeliculas) {
        this.context = context;
        this.listaPeliculas = listaPeliculas;
    }

    public void setListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
        notifyDataSetChanged();
    }

    @Override
    public Adaptador.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_adaptador, parent, false);
        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(Adaptador.MyviewHolder holder, int position) {



        Glide.with(context).load(listaPeliculas.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
        Pelicula currentItem = listaPeliculas.get(position);

        String imageUrl = currentItem.getImageUrl();
        String titl = currentItem.getTitle();


        holder.tvTitle.setText(titl);

        Picasso.with(context).load(imageUrl).fit().centerCrop().into(holder.image);
    }



    @Override
    public int getItemCount() {
        if (listaPeliculas != null) {
            return listaPeliculas.size();
        }
        return 0;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView image;

        public MyviewHolder(View itemView) {
            super(itemView);
            tvTitle =  itemView.findViewById(R.id.title);
            image =  itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}


