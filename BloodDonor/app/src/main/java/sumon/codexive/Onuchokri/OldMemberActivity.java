package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OldMemberActivity extends AppCompatActivity {

    private Button bloodDate;
    private EditText oldName, oldPhone;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String OldDonorPhone="",OldDonorName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_member);
        oldName = findViewById(R.id.editTextOldName);
        oldPhone = findViewById(R.id.editTextOldPhone);
        bloodDate = findViewById(R.id.buttonManageBloodDate);

        database = FirebaseDatabase.getInstance();
        myRef =database.getReference("users");

        //Add backbutton
        getSupportActionBar().setTitle("Update Donation Date");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        bloodDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OldDonorName = oldName.getText().toString();
                 OldDonorPhone = oldPhone.getText().toString();

             if (!TextUtils.isEmpty(OldDonorName) && !TextUtils.isEmpty(OldDonorPhone)) {

                 myRef.orderByChild("name_phone").equalTo(OldDonorName.trim() + "," + OldDonorPhone.trim()).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {

                         String na = "";
                         String Key = "";


                         for (DataSnapshot child : dataSnapshot.getChildren()) {
                             Key = child.getKey();
                             Log.d("User key", child.getKey());
                           //  Toast.makeText(OldMemberActivity.this, child.getKey() + "", Toast.LENGTH_SHORT).show();
                             //   Log.d("User ref", child.getRef().toString());
                             Log.d("User val", child.getValue().toString());
                           //  Toast.makeText(OldMemberActivity.this, child.getValue() + "", Toast.LENGTH_SHORT).show();
                             User user = child.getValue(User.class);
                             //  String n =  user.getBlood_city_date();
                             na = user.getName_phone_date();
                            // Toast.makeText(OldMemberActivity.this, " " + na, Toast.LENGTH_SHORT).show();

                         }

                         startActivity(new Intent(OldMemberActivity.this, BloodDateActivity.class).putExtra("phoneOldDonor", na).putExtra("Key", Key));
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {
                         Toast.makeText(OldMemberActivity.this, "Invalid User", Toast.LENGTH_SHORT).show();
                     }

                 });


             }

            }
        });



    }

    public void UpdateFireBaseData(String Id,String Date){


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
