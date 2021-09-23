package com.example.hi.gamedapchuot;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter_Score extends RecyclerView.Adapter<Adapter_Score.adapterHoler> {
    private List<ScoreOBJS> list;

    public Adapter_Score(List<ScoreOBJS> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public adapterHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_higth_score, parent, false);
        return new adapterHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHoler holder, int position) {
        if (position <= list.size() - 1) {
            holder.txtName.setText(list.get(position).getName());
            holder.txtScore.setText(list.get(position).getScore() + "");
        } else {
            holder.txtName.setText("No Name");
            holder.txtScore.setText("0000");
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class adapterHoler extends RecyclerView.ViewHolder {
        TextView txtName, txtScore;

        public adapterHoler(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtScore = itemView.findViewById(R.id.txt_score);
        }
    }
}
