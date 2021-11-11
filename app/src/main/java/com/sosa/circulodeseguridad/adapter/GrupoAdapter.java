package com.sosa.circulodeseguridad.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sosa.circulodeseguridad.R;
import com.sosa.circulodeseguridad.entidades.Grupo;

import java.util.List;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.MiViewHolder>{
private List<Grupo> lista;
private View root ;
private LayoutInflater inflater;

public GrupoAdapter(List<Grupo> lista, View root, LayoutInflater inflater) {
        this.lista = lista;
        this.root = root;
        this.inflater = inflater;
        }


@NonNull
@Override
public GrupoAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_grupo, parent, false);
        return new GrupoAdapter.MiViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull GrupoAdapter.MiViewHolder holder, int position) {

    Grupo i = lista.get(position);
    holder.TVDetalle.setText(i.Nombre);
    Glide.with(root.getContext())//contexto
            .load(i.AvatarGrupo)//url de la imagen
            .diskCacheStrategy(DiskCacheStrategy.ALL)// guarda en el cache
            .into(holder.imagen); // se encarga de setear la imagen

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Bundle bundle = new Bundle();
            bundle.putSerializable("grupo",i);
         //   Navigation.findNavController(root).navigate(R.id.inmuebleDetalleFragment,bundle);

        }
    });

};


@Override
public int getItemCount() {
        return lista.size();
        }

public class MiViewHolder extends RecyclerView.ViewHolder {
    private ImageView imagen;
    private TextView TVDetalle;
    public MiViewHolder(@NonNull View itemView) {
        super(itemView);
        imagen = itemView.findViewById(R.id.TVCVImagen);
        TVDetalle = itemView.findViewById(R.id.TVCVDetalles);

    }
}
}
