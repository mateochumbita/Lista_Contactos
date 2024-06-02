package com.mateo.Fuentes686Chumbita782.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mateo.Fuentes686Chumbita782.R;
import com.mateo.Fuentes686Chumbita782.VerContacto;
import com.mateo.Fuentes686Chumbita782.clases.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {
    ArrayList<Contacto> listaContactos;
    ArrayList<Contacto> listaOriginal;

    public ListaContactosAdapter(ArrayList<Contacto> listaContactos){
        this.listaContactos = listaContactos;
        this.listaOriginal = new ArrayList<>();
        this.listaOriginal.addAll(listaContactos);
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);

        holder.nameTextView.setText(contacto.getNombre());
        holder.surnameTextView.setText(contacto.getApellido());
        holder.numberTextView.setText(contacto.getNumero());

        // Asigna el color al avatar basándote en el color del contacto
        try {
            holder.avatar.setColorFilter(Color.parseColor(contacto.getColor()));
        } catch (IllegalArgumentException e) {
            // En caso de que el color no sea válido, se puede asignar un color por defecto
            holder.avatar.setColorFilter(Color.GRAY);
        }
    }


    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud == 0){
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contacto> coleccion = listaContactos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) || i.getApellido().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            }else{//alternativa si version de android no es la N
                for(Contacto contacto : listaContactos){
                    if(!contacto.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) && !contacto.getApellido().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaContactos.add(contacto);
                    }
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, surnameTextView, numberTextView;
        ImageView avatar;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            surnameTextView = itemView.findViewById(R.id.surnameTextView);
            numberTextView = itemView.findViewById(R.id.numberTextView);
            avatar = itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerContacto.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
