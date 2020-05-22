package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class AndroidCourese extends AppCompatActivity {
   LinearLayout androidregisterbtn,androidweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_courese);

        androidregisterbtn=findViewById(R.id.androidregisterbtn);
        androidweb=findViewById(R.id.androidweb);




        androidregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log12 = new Intent(getApplicationContext(), Couresregistartion.class);

                startActivity(log12);


            }



        });



        androidweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent web = new Intent(getApplicationContext(), Syllabus_webview.class);
web.putExtra("syllabus","https://www.techsays.in/syllabus_android.html");
                startActivity(web);


            }



        });



    }
}
