package com.example.addcontact;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("ListDebug", "ContactListActivity onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        listView = findViewById(R.id.listc); // Make sure your ListView ID matches in your XML
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        // Reference to Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("contacts");

        // Listen for changes in the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear(); // Clear the adapter before populating it again
                for (DataSnapshot contactSnapshot : dataSnapshot.getChildren()) {
                    String contactName = contactSnapshot.getValue(String.class);
                    if (contactName != null) {
                        adapter.add(contactName);
                    }
                }
                adapter.notifyDataSetChanged(); // Update the ListView
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ListDebug", "Erreur Firebase : " + databaseError.getMessage());
            }
                // Handle errors here

        });
        FloatingActionButton floatingActionButton2 = findViewById(R.id.floatingActionButton2);

        // Ajoutez un écouteur d'événements OnClickListener au bouton
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créez un Intent pour lancer l'activité "addcontact"
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
