package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewDonorActivity extends AppCompatActivity{

    private Button addButton, oldMemberBtn;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private EditText donorName, donorPhone;

    String donorBloodGroup = "", donorCity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_donor);

        addButton = findViewById(R.id.button5);
        oldMemberBtn = findViewById(R.id.buttonOldMemberInfo);
        database = FirebaseDatabase.getInstance();


        donorName = findViewById(R.id.editTextDonorName);
        donorPhone = findViewById(R.id.editTextDonorPhone);

        //Add backbutton
        getSupportActionBar().setTitle("Donate Blood");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Spinner spinnerBlood = findViewById(R.id.spinner);

        Spinner spinnerCity = findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterCity = ArrayAdapter.createFromResource(this,
                R.array.blood_people_city, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerBlood.setAdapter(adapter);
        spinnerCity.setAdapter(adapterCity);

        spinnerBlood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               // Toast.makeText(NewDonorActivity.this, adapterView.getItemAtPosition(i)+"", Toast.LENGTH_SHORT).show();
                donorBloodGroup = adapterView.getItemAtPosition(i)+"";

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(NewDonorActivity.this, adapterView.getItemAtPosition(i)+"", Toast.LENGTH_SHORT).show();
                donorCity = adapterView.getItemAtPosition(i)+"";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Write a message to the database

                String phone = donorPhone.getText().toString().trim();
                String name = donorName.getText().toString().trim();

                if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(name)) {

                    myRef = database.getReference("users").push();

                    myRef.child("name").setValue(name);
                    myRef.child("blood_city").setValue(donorBloodGroup + "," + donorCity);
                    myRef.child("phone").setValue(phone);
                    myRef.child("blood_city_date").setValue(donorBloodGroup + "," + donorCity + "," + "");
                    myRef.child("name_phone").setValue(name + "," + phone);
                    myRef.child("name_phone_date").setValue(name + "," + phone + "," + " ");


                    Toast.makeText(NewDonorActivity.this, "Congratulation! You are now a Blood donor!", Toast.LENGTH_LONG).show();

                    startActivity(new Intent(NewDonorActivity.this, MainActivity.class));

                }
            }
        });

        oldMemberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewDonorActivity.this, OldMemberActivity.class));
            }
        });

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
