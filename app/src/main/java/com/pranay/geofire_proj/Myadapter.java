package com.pranay.geofire_proj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<Myadapter.Myholder>
{

    ArrayList<Mylist> list;
    Context context;

    public Myadapter(ArrayList<Mylist> list,Context context)
    {
        this.list=list;
        this.context=context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.my_card,parent,false);
        return new Myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, int position) {
        Mylist mylist=list.get(position);

        holder.id.setText(mylist.getId());
        holder.name.setText(mylist.getnama());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder {
        TextView id,name;
        public Myholder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);


        }
    }
}
