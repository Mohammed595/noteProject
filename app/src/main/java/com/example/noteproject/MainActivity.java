package com.example.noteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton addNewNoteBtn = (MaterialButton) findViewById (R.id.addnewnotebtn);

        addNewNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // add new note
                startActivity(new Intent(MainActivity.this, addNoteActivity.class));
            }
        });

        // Last step in the project
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        RealmResults <Note> noteList = realm.where(Note.class).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter myAdapter = new MyAdapter(getApplicationContext(), noteList);

        recyclerView.setAdapter(myAdapter);

        noteList.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Note>>() {
            @Override
            public void onChange(RealmResults<Note> notes, OrderedCollectionChangeSet changeSet) {
                myAdapter.notifyDataSetChanged();
            }
        });

    }
}