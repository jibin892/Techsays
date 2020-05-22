package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contacts extends AppCompatActivity {
TextView phone,email,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        phone=findViewById(R.id.contactph);

        email=findViewById(R.id.contactemail);

        address=findViewById(R.id.contactaddress);




        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+919744613979"));
                startActivity(intent);



            }
        });




        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });


        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





            }
        });



    }
}
