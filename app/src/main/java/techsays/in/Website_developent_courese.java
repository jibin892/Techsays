package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Website_developent_courese extends AppCompatActivity {
    LinearLayout webbtn,web;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_developent_courese);




        web=findViewById(R.id.webweb);
        webbtn=findViewById(R.id.webbtn);
        back=findViewById(R.id.webback);




        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent web = new Intent(Website_developent_courese.this, Syllabus_webview.class);
                web.putExtra("syllabus","https://www.techsays.in/syllabus_web.html");
                startActivity(web);

            }



        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent b = new Intent(Website_developent_courese.this, Home.class);
                startActivity(b);

            }



        });

        webbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent log12 = new Intent(getApplicationContext(), Couresregistartion.class);
                log12.putExtra("ab","Website Developent Registration");
                startActivity(log12);


            }



        });



    }
}
