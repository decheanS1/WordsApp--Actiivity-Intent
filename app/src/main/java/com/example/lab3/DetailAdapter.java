package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    private final RVInterface rvInterface;
    Context context;
    ArrayList<WordModel> detailModels;

    public DetailAdapter(Context context, ArrayList<WordModel> detailModels, RVInterface rvInterface) {
        this.context = context;
        this.detailModels = detailModels;
        this.rvInterface = rvInterface;

    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // giving a look to view rows
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.second_recycler_view_row, parent, false);

        return new DetailViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        // update data to the view rows
        holder.btnWord.setText(detailModels.get(position).getSingleCharacter());
    }

    @Override
    public int getItemCount() {
        return detailModels.size();
    }



    // inner class
    public static class DetailViewHolder extends RecyclerView.ViewHolder{

        // variable that holds value from the layout file
        Button btnWord;

        public DetailViewHolder(@NonNull View itemView, RVInterface rvInterface) {
            super(itemView);

            btnWord = itemView.findViewById(R.id.button3);
            // attach onClickListener to button
            btnWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // perform view checks
                    if (rvInterface != null){
                        // grab the position from our adapter
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION){
                            rvInterface.onItemClick(pos);
                        }


                    }

                }
            });


        }
    }




}
