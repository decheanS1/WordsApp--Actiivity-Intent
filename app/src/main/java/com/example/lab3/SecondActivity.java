package com.example.lab3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements RVInterface{
    TextView textView1;

    // store the model within an arraylist
    ArrayList<WordModel> filteredWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String name = intent.getStringExtra("mywords");
        String lowerWords = name.toLowerCase();

        RecyclerView recyclerView = findViewById(R.id.second_recyclerview);

        setUpWords(lowerWords);

        // create an adapter after you set up your models
        DetailAdapter adapter = new DetailAdapter( this, filteredWords, this);

        // attach the adapter to our recyclerview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setTitle("Words That Start With "+ name);
    }


    private void setUpWords(String words){
        // grab all the values from the strings.xml file array
        String[] detailedWords = getResources().getStringArray(R.array.words);
        // iterate through each array
        for (String word: detailedWords){
            if (word.toLowerCase().startsWith(words)){
                filteredWords.add(new WordModel(word));
            }
        }


    }

    // rvInterface method overriding
    @Override
    public void onItemClick(int position) {
        // get the word clicked on and store it to search for that keyword
        String searchWord = filteredWords.get(position).getSingleCharacter();
        // implicit intent
        Uri uri = Uri.parse("https://www.google.com/search?q="+ searchWord);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);




    }


}