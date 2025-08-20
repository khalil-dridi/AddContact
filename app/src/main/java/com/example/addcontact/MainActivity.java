package com.example.addcontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View; // Added import
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {
    EditText editText;
    DatabaseReference myRef;
    FloatingActionButton saveButton ;
    FloatingActionButton listeButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.textInputEditText1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference("contacts");
        listeButton= findViewById(R.id.list);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContactName();
            }
        });
        listeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(),ContactListActivity.class);
                startActivity(intent);

            }
        });
    }

    private void saveContactName() {
        String contactName = editText.getText().toString();

        if (!contactName.isEmpty()) {
            String contactKey = myRef.push().getKey();

            if (contactKey != null) {
                myRef.child(contactKey).setValue(contactName);
                editText.setText("");


                Toast.makeText(this, "Contact saved... " , Toast.LENGTH_SHORT).show();
            }
        } else {

            Toast.makeText(this, "Please enter a contact name", Toast.LENGTH_SHORT).show();
        }



    }

    public void onDataChange(DataSnapshot dataSnapshot) {


    }

    public void onCancelled(DatabaseError error) {

    }

}
