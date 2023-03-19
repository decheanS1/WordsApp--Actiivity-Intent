package com.example.lab3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RVInterface{

    // store the models within an arraylist
    ArrayList<WordModel> wordModels = new ArrayList<>();

    // for layoutmanager
    private boolean isLinearLayoutManager = true;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        recyclerView = findViewById(R.id.myRecyclerView);

        setUpWordModels();

        // create an adapter after you set up your models
        RVAdapter adapter = new RVAdapter(this, wordModels, this);

        // attach the adapter to our recycler view
        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // sets the linearLayoutManager of the recyclerview
        chooseLayout();



        setTitle("Words");



    }
    private void chooseLayout(){
        if (isLinearLayoutManager){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
    }


    private void setUpWordModels(){
        // grab all the values from the string.xml file and store it into an array
//        String[] letters = getResources().getStringArray(R.array.words);

        // storing characters 'a' to 'z'
        String[] letters = new String[26];
        for (int i = 0, j = 65; i < 26; i++, j++) {
            letters[i] = Character.toString((char) j);
        }
        
        // loops through each array
        for (int i = 0; i < letters.length ; i++) {
            // create model class for each of those items & store them within our wordModels arraylist
            wordModels.add(new WordModel(letters[i]));
        }
    }

    private void setIcon(MenuItem menuItem){
        if (menuItem == null){
            return;
        }

//        Drawable drawable = isLinearLayoutManager ? ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
//                : ContextCompat.getDrawable(this, R.drawable.ic_linear_layout);
//        menuItem.setIcon(drawable);

        Drawable drawable = isLinearLayoutManager ? ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
                : ContextCompat.getDrawable(this, R.drawable.ic_linear_layout);
        menuItem.setIcon(drawable);

    }

    // inflate the options menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);

        MenuItem layoutButton = menu.findItem(R.id.action_switch_layout);
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch_layout:
                RecyclerView recyclerView;
                isLinearLayoutManager = !isLinearLayoutManager;
                chooseLayout();
                setIcon(item);
            default:
                //  Otherwise, do nothing and use the core event handling
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onItemClick(int position) {
        // when an item is clicked swap over to new activity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        intent.putExtra("mywords", wordModels.get(position).getSingleCharacter());

        startActivity(intent);




    }
}