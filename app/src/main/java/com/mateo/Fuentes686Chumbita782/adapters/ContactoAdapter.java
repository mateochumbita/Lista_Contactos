package com.mateo.Fuentes686Chumbita782.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mateo.Fuentes686Chumbita782.R;
import com.mateo.Fuentes686Chumbita782.classes.Contacto;
import java.util.List;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ViewHolder> {
    private Context context;
    private List<Contacto> listaContactos;

    public ContactoAdapter(Context context, List<Contacto> listaContactos) {
        this.context = context;
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contacto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contacto contacto = listaContactos.get(position);
        holder.bind(contacto);
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public void filterList(List<Contacto> filteredList) {
        listaContactos = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView nombreContacto;
        TextView apellidoContacto;
        TextView numeroContacto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreContacto = itemView.findViewById(R.id.nameTextView);
            apellidoContacto = itemView.findViewById(R.id.surnameTextView);
            numeroContacto = itemView.findViewById(R.id.numberTextView);
            avatar = itemView.findViewById(R.id.iconImageView);

        }

        public void bind(Contacto contacto) {
            nombreContacto.setText(contacto.getNombre());
            apellidoContacto.setText(contacto.getApellido());
            numeroContacto.setText(String.valueOf(contacto.getNumero()));

            int color = Color.parseColor(contacto.getColor());
            avatar.setColorFilter(color);



        }
    }
}
