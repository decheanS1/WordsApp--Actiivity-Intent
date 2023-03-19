package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    private final RVInterface rvInterface;
    Context context;
    ArrayList<WordModel> wordModels;

    public RVAdapter(Context context, ArrayList<WordModel> wordModels, RVInterface rvInterface) {
        this.context = context;
        this.wordModels = wordModels;
        this.rvInterface = rvInterface;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // this is where you inflate the layout - giving a look to our view rows
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recycler_view_row, parent, false);


        return new MyViewHolder(view, rvInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // assigning values to the views we created in the recycler_view_row layout file
        // based on the position of the recycler view

        // update the data on each of our rows, we need to change the value within the holder that's passed in
        holder.btnWord.setText(wordModels.get(position).getSingleCharacter());

    }

    @Override
    public int getItemCount() {
        // no. of items you want to display on the screen
        return wordModels.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // grabbing the views from our recycler_view_row layout file
        // similar to the onCreate method

        // variables that hold the data from the layout file
        Button btnWord;

        public MyViewHolder(@NonNull View itemView, RVInterface rvInterface) {
            super(itemView);

            btnWord = itemView.findViewById(R.id.button2);

            // attach onClickListener to our itemview
            btnWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // perform view checks
                    if (rvInterface != null){
                        // grab the position from our adapter for the RV-interface
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
