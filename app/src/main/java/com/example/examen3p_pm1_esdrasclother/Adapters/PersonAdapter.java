package com.example.examen3p_pm1_esdrasclother.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen3p_pm1_esdrasclother.InterviewInformationActivity;
import com.example.examen3p_pm1_esdrasclother.R;
import com.example.examen3p_pm1_esdrasclother.Models.Person;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class PersonAdapter extends FirestoreRecyclerAdapter<Person, PersonAdapter.PersonViewHolder> {

    public PersonAdapter(@NonNull FirestoreRecyclerOptions<Person> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull PersonViewHolder holder, int position, @NonNull Person model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_list, parent, false);
        return new PersonViewHolder(view);
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView nombrePeriodista;
        private TextView Descripcion;
        String fecha;
        private Button button;


        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nombrePeriodista = itemView.findViewById(R.id.firstname);
            Descripcion = itemView.findViewById(R.id.lastname);

            button = itemView.findViewById(R.id.button); // Reemplaza "miBoton" con el ID de tu botón en el diseño XML

            // Agregar el ClickListener al botón
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), InterviewInformationActivity.class);
                    intent.putExtra("Periodista", String.valueOf(nombrePeriodista.getText().toString().trim()));
                    intent.putExtra("Descripcion", String.valueOf(Descripcion.getText().toString().trim()));
                    intent.putExtra("fecha", String.valueOf(fecha.toString()));
                    itemView.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Person person) {
            // Bind data to TextViews
            nombrePeriodista.setText(person.getPeriodista());
            Descripcion.setText(person.getDescripcion());
            fecha = (person.getFecha());
        }
    }

}
