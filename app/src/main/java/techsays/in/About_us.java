package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class About_us extends AppCompatActivity {
TextView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

web=findViewById(R.id.aboutewb);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log13 = new Intent(About_us.this, Webwiewtechsays.class);

                startActivity(log13);


            }
        });





    }
}
