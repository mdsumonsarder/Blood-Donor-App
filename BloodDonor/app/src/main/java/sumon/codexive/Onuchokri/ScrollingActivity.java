package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    ListView listView;

    List<User> userList;
    AllUserlist arrayAdapter;

    String blood="",city="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Please Call", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("users");
        listView = findViewById(R.id.listViewScrolling);
        userList = new ArrayList<>();


        Intent i = getIntent();
         blood = i.getStringExtra("searchBlood");
         city = i.getStringExtra("searchCity");


    }


    @Override
    protected void onStart() {
        super.onStart();






        //search from Database

        myRef.orderByChild("blood_city").equalTo(blood.trim()+","+city.trim()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                 userList.clear();

                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.d("User key", child.getKey());
                    //   Log.d("User ref", child.getRef().toString());
                    Log.d("User val", child.getValue().toString());
                    User user =  child.getValue(User.class);
                    String name = user.getName_phone_date();
                  //  Toast.makeText(ScrollingActivity.this, ""+name, Toast.LENGTH_SHORT).show();
                     userList.add(user);

                }

                arrayAdapter = new AllUserlist(ScrollingActivity.this,userList);
                arrayAdapter.notifyDataSetChanged();
                listView.setAdapter(arrayAdapter);
                // Log.d("firebase data", "onDataChange: "+dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
    private String BloodDate(){
        User user = new User();
        String string = user.getName_phone_date();
        String[] parts = string.split(",");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        String part3 = parts[2];
        return part3;
    }
}
