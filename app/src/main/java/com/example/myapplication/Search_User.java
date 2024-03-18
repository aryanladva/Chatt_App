package com.example.myapplication;

import android.os.Bundle;

import com.example.myapplication.Models.User_Model;
import com.example.myapplication.Utilis.FirebaseUtil;
import com.example.myapplication.adapter.Search_User_Adapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.ImageButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.Query;

public class Search_User extends AppCompatActivity {

    EditText searchInput;
    ImageButton searchButton;
    ImageButton backButton;
    RecyclerView recyclerView;

    Search_User_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        searchInput = findViewById(R.id.seach_username_input);
        searchButton = findViewById(R.id.search_user_btn);
        backButton = findViewById(R.id.back_btn);
        recyclerView = findViewById(R.id.search_user_recycler_view);

        searchInput.requestFocus();

        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        searchButton.setOnClickListener(v -> {
            String searchTera = searchInput.getText().toString();
            if (searchTera.isEmpty() || searchTera.length() < 3) {
                searchInput.setError("Invalid Username");
            }
            setupSearchRecyclerView(searchTera);
        });

    }

    void setupSearchRecyclerView(String searchTera) {

        Query query = FirebaseUtil.allUserCollectionReference()
                .whereGreaterThanOrEqualTo("username",searchTera)
                .whereLessThanOrEqualTo("username",searchTera+'\uf8ff');

        FirestoreRecyclerOptions<User_Model> options = new FirestoreRecyclerOptions.Builder<User_Model>()
                .setQuery(query,User_Model.class).build();

        adapter = new Search_User_Adapter(options,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.startListening();
    }
}