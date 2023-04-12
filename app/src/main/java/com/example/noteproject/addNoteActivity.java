package com.example.noteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;

public class addNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText titleInput = (EditText) findViewById(R.id.titleinput);
        EditText descriptionInput = (EditText) findViewById(R.id.descriptioninput);

        MaterialButton saveBtn = (MaterialButton) findViewById(R.id.savrbtn);

        Realm.init(getApplicationContext());

        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                long crearedTime = System.currentTimeMillis();

                realm.beginTransaction();

                Note note = realm.createObject(Note.class);

                note.setTitle(title);
                note.setDescription(description);
                note.setCreatedTime(crearedTime);

                realm.commitTransaction();

                Toast.makeText(getApplicationContext(), "Save note", Toast.LENGTH_LONG ).show();
                finish();
            }
        });


    }
}