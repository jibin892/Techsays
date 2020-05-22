package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AndroidCourese extends AppCompatActivity {
   LinearLayout androidregisterbtn,androidweb;
   ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_courese);

        androidregisterbtn=findViewById(R.id.androidregisterbtn);
        androidweb=findViewById(R.id.androidweb);
        back=findViewById(R.id.androidback);




        androidregisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log12 = new Intent(getApplicationContext(), Couresregistartion.class);
                log12.putExtra("ab","Android Registration");
                startActivity(log12);


            }



        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log1 = new Intent(getApplicationContext(), Home.class);
                startActivity(log1);


            }



        });

        androidweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent web1 = new Intent(getApplicationContext(), Syllabus_webview.class);
               web1.putExtra("syllabus","https://www.techsays.in/syllabus_android.html");
                startActivity(web1);


            }



        });



    }
}
