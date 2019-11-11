package com.example.galgeleg.Model.Spillogik.HighScore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.galgeleg.R;

import java.util.List;


public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {

    List<UserHighScoreDTO> userList;

    public ListViewAdapter(List<UserHighScoreDTO> userList){
        this.userList=userList;
    }


    @NonNull
    @Override
    public ListViewAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.highscore_listitem, parent, false);

            ListViewAdapter.ListViewHolder vh = new ListViewAdapter.ListViewHolder(itemView);
            vh.user = itemView.findViewById(R.id.username);
            vh.highscore = itemView.findViewById(R.id.score);

            return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.ListViewHolder holder, int position) {
        holder.user.setText(userList.get(position).getName());
        System.out.println(userList.get(position).getName());
        holder.highscore.setText(userList.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder  {
        public TextView user;
        public TextView highscore;

        public ListViewHolder(View view) {
        super(view);
        user = view.findViewById(R.id.username);
        highscore = view.findViewById(R.id.score);
        }
    }
}
