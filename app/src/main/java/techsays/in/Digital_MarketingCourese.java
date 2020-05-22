package techsays.in;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Digital_MarketingCourese extends AppCompatActivity {
   LinearLayout dibtn,diweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.digitalmarketing_courese);

        diweb=findViewById(R.id.diweb);
        dibtn=findViewById(R.id.dibtn);




        diweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent web = new Intent(Digital_MarketingCourese.this, Syllabus_webview.class);
                web.putExtra("syllabus","https://www.techsays.in/syllabus_digitalmarkting.html");
                startActivity(web);

            }



        });



        dibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Intent log12 = new Intent(getApplicationContext(), Couresregistartion.class);

                startActivity(log12);


            }



        });



    }
}
