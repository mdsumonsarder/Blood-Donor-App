package sumon.codexive.Onuchokri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedback extends AppCompatActivity implements View.OnClickListener {
    private Button sendButton, clearButton;
    private EditText name, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        sendButton = findViewById(R.id.send);
        clearButton = findViewById(R.id.clear);
        name = findViewById(R.id.name);
        message = findViewById(R.id.ms);

        sendButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        //Add actionbar backbuttorn
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            //Ends the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String Name = name.getText().toString();
        String Message = message.getText().toString();

        try {
            if (v.getId() == R.id.send) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"sumonsorder953@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");
                intent.putExtra(Intent.EXTRA_TEXT, "Name : " + Name + "Message : " + Message);
                startActivity(Intent.createChooser(intent, "Feedback with"));


            } else if (v.getId() == R.id.clear) {
                message.setText("");
                name.setText("");

            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Exception : " + e, Toast.LENGTH_SHORT).show();


        }
    }
}
