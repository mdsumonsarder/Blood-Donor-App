package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class BloodDateActivity extends AppCompatActivity {

    DatePicker datePicker;
    private Button btnUpdateInfo;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String phone="",token;
    String Date="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_date);

        //Add backbutton
        getSupportActionBar().setTitle("Blood Donation Date");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datePicker = findViewById(R.id.datePicker);
        btnUpdateInfo = findViewById(R.id.buttonUpdateInformation);

        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("users");

        Intent i = getIntent();
        phone = i.getStringExtra("phoneOldDonor");
        token = i.getStringExtra("Key");

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date = getCurrentDate();
                myRef.child(token).child("name_phone_date").setValue(null);
              //  myRef.child(token).child("name_phone_date").setValue(phone+Date);

                HashMap<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("name_phone_date" , phone+Date);
                myRef.child(token).updateChildren(childUpdates);
                  startActivity(new Intent(BloodDateActivity.this,MainActivity.class));
                Toast.makeText(BloodDateActivity.this, "You are a real hero! You save a life.", Toast.LENGTH_LONG).show();

            }
        });


    }

    public String getCurrentDate(){
        StringBuilder builder=new StringBuilder();
        builder.append("");
        builder.append((datePicker.getMonth() + 1)+"/");//month is 0 based
        builder.append(datePicker.getDayOfMonth()+"/");
        builder.append(datePicker.getYear());
        return builder.toString();
    }

    //Add backbutton
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            //Ends the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
