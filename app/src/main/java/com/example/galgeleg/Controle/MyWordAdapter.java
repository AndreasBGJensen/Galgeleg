package com.example.galgeleg.Controle;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.galgeleg.R;
import java.util.ArrayList;

public class MyWordAdapter extends RecyclerView.Adapter<MyWordAdapter.ListViewHolder>  {
    public ArrayList<String> worList;

    public MyWordAdapter(ArrayList<String> worList){
        this.worList=worList;
    }



    @NonNull
    @Override
    public MyWordAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.twoplayer_item, parent, false);

        MyWordAdapter.ListViewHolder vh = new MyWordAdapter.ListViewHolder(itemView);
        vh.ord= itemView.findViewById(R.id.Ord);


        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyWordAdapter.ListViewHolder holder, int position) {
        holder.ord.setText(worList.get(position));
    }


    @Override
    public int getItemCount() {
         return worList.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder  {
        public TextView ord;


        public ListViewHolder(View view) {
            super(view);
            ord = view.findViewById(R.id.Ord);

        }
    }
}








