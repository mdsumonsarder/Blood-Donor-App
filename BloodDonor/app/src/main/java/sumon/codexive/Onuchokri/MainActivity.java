package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton addMember, searchPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addMember = findViewById(R.id.imageButtonAddMember);
        searchPeople = findViewById(R.id.imageButtonSearchMember);

        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewDonorActivity.class));
            }
        });

        searchPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchDonorActivity.class));
            }
        });
    }

    //Menu Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.shareId) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");

            String subject = "Onuchokri Blood Search";
            String body = "This App is very helpfull";

            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);

            startActivity(Intent.createChooser(intent, "Share with"));
        }

        else if (item.getItemId()==R.id.feedbackId){

            Intent intent = new Intent(MainActivity.this,Feedback.class);
            startActivity(intent);
        }

        else if (item.getItemId()==R.id.aboutId){

            Intent intent = new Intent(MainActivity.this,About.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
