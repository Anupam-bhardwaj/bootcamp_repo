package com.example.AndroidAssignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Question2 extends AppCompatActivity {

    private RecyclerView newsRecycler;
    private RecyclerView.LayoutManager newsLayoutManager;
    private RecyclerView.Adapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);

        ArrayList<Model> list;
        list = addData();

        newsRecycler = (RecyclerView)findViewById(R.id.recyclerNewsView);

        newsLayoutManager = new LinearLayoutManager(this);
        newsRecycler.setLayoutManager(newsLayoutManager);

        mNewsAdapter = new newsAdapter(list,this);
        newsRecycler.setAdapter(mNewsAdapter);
    }

    //Adding data to Model Class Constructor.
    ArrayList<Model> addData(){
        ArrayList<Model> data = new ArrayList<>();
        {
            data.add(new Model(Model.BANNER_TYPE, R.drawable.image1, "The Times of India","World prepares for coronavirus pandemic; global recession feared"));
            data.add(new Model(Model.SMALL_BANNER_TYPE, R.drawable.image2, "Hindustan Times","India vs New Zealand prediction: India predicted XI for 2nd Test - Virat Kohli could opt for two big..."));
            data.add(new Model(Model.SMALL_BANNER_TYPE, R.drawable.image3, "Engadget","Facebook is suing a shady SDK developer."));
            data.add(new Model(Model.BANNER_TYPE, R.drawable.image1, "The Times of India","World prepares for coronavirus pandemic; global recession feared"));
            data.add(new Model(Model.SMALL_BANNER_TYPE, R.drawable.image2, "Hindustan Times","India vs New Zealand prediction: India predicted XI for 2nd Test - Virat Kohli could opt for two big..."));
            data.add(new Model(Model.SMALL_BANNER_TYPE, R.drawable.image3, "Engadget","Facebook is suing a shady SDK developer."));
        }
        return data;
    }
}