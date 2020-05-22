package techsays.in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Digital_MarketingCourese extends AppCompatActivity {
   LinearLayout digitalmarketingbtn,digitalmarketingweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digitalmarketing_courese);

        digitalmarketingbtn=findViewById(R.id.digitaldarketingwebsite);
        digitalmarketingweb=findViewById(R.id.digitalmarketingregisterbtn);




        digitalmarketingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log12 = new Intent(getApplicationContext(), Couresregistartion.class);

                startActivity(log12);


            }



        });



        digitalmarketingweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent web = new Intent(getApplicationContext(), Syllabus_webview.class);
web.putExtra("syllabus","https://www.techsays.in/syllabus_digitalmarkting.html");
                startActivity(web);


            }



        });



    }
}
