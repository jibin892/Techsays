package techsays.in;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.IdpResponse;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class Couresregistartion extends AppCompatActivity {
EditText coursedetails,age,phone,address,satse,dis;
Intent a;
Button regbtn;
ImageView back;
TextView regname;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couresregistartion);

a=getIntent();

        coursedetails=findViewById(R.id.regqul);
        age=findViewById(R.id.regage);
        phone=findViewById(R.id.regph);
        address=findViewById(R.id.regaddre);
        satse=findViewById(R.id.regsat);
        dis=findViewById(R.id.regdis);
        regbtn=findViewById(R.id.regbtn);
        regname=findViewById(R.id.regname);
        back=findViewById(R.id.coureback);


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent log = new Intent(getApplicationContext(), AndroidCourese.class);
                startActivity(log);

            }
        });








regname.setText("REGISTARION");
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

          if(coursedetails.getText().toString().isEmpty()){

              coursedetails.setError("Field is Empty");
          }
                else if(phone.getText().toString().isEmpty()){

                    phone.setError("Field is Empty");
                }


          else if(phone.getText().toString().length()>10){

              phone.setError("Enter a Valid Phone Number");
          }


          else if(age.getText().toString().isEmpty()){

              age.setError("Field is Empty");
          }
          else if(address.getText().toString().isEmpty()){

              address.setError("Field is Empty");
          }

          else if(satse.getText().toString().isEmpty()){

              satse.setError("Field is Empty");
          }

          else if(dis.getText().toString().isEmpty()){

              dis.setError("Field is Empty");
          }


          else{

              final ProgressDialog progress = new ProgressDialog(Couresregistartion.this);
              progress.setMessage("Register........");
              progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //  progress.setIndeterminate(true);
              progress.show();
    if(user!=null){
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("courese_registration");
        userRef
                .orderByChild("personEmail")
                .equalTo(String.valueOf(user.getEmail()))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.getValue() != null) {
                            progress.dismiss();
                            Crouton.makeText(Couresregistartion.this, "Alrady", Style.ALERT).show();
                            phone.getText().clear();
                            coursedetails.getText().clear();
                            address.getText().clear();
                            satse.getText().clear();
                            dis.getText().clear();
                            age.getText().clear();

                        } else {




                            {

                                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                DatabaseReference object = FirebaseDatabase.getInstance().getReference();
                                DatabaseReference namesRef = object.child("courese_registration").push();
                                Map<String, Object> map = new HashMap<>();
                                map.put("personName", user.getDisplayName());
                                map.put("personEmail",user.getEmail());
                                map.put("personPhoto",String.valueOf(user.getPhotoUrl()));
                                map.put("id", user.getUid());
                                map.put("course_info", coursedetails.getText().toString());
                                map.put("age", age.getText().toString());
                                map.put("address", address.getText().toString());
                                map.put("state", satse.getText().toString());
                                map.put("dis", dis.getText().toString());
                                map.put("alternative_phone", phone.getText().toString());
                                String mGroupId = object.push().getKey();
                                map.put("personId", mGroupId);
                                String currentTime = new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
                                map.put("reg_time", currentTime);
                                namesRef.updateChildren(map);
                                object.child("courese_registration");



                                object.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        progress.dismiss();
                                        Crouton.makeText(Couresregistartion.this, "SUCCSEUS", Style.CONFIRM).show();
                                        phone.getText().clear();
                                        coursedetails.getText().clear();
                                        address.getText().clear();
                                        satse.getText().clear();
                                        dis.getText().clear();
                                        age.getText().clear();
//                                                            Intent log = new Intent(getApplicationContext(), AndroidCourese.class);
//                                                            startActivity(log);




                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });

                            }





                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

}




            }
        });


    }
}
