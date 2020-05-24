package techsays.in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Locale;


import cn.pedant.SweetAlert.SweetAlertDialog;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    TextView cl,darkmode;
    public  TextView delete;
    SharedPreferences sh;
    private BottomSheetDialog bottomSheetDialog;
    ImageView profileimgdelet;
     FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        cl = findViewById(R.id.cl);
        delete=findViewById(R.id.deleteaccount);
        darkmode=findViewById(R.id.darkmodechange);
          user = FirebaseAuth.getInstance().getCurrentUser();

        bottomSheetDialog = new BottomSheetDialog(Settings.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.deleteaccount, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);


        Button logout = bottomSheetDialogView.findViewById(R.id.logout);
        profileimgdelet = bottomSheetDialogView.findViewById(R.id.profileimgdelet);
        TextView logoutemail = bottomSheetDialogView.findViewById(R.id.deletemail);
        TextView logoutname = bottomSheetDialogView.findViewById(R.id.deletname);



       Button deletaccounts = bottomSheetDialogView.findViewById(R.id.deletaccounts);
       Button deletlogout = bottomSheetDialogView.findViewById(R.id.deletlogout);
//        RelativeLayout insta = bottomSheetDialogView.findViewById(R.id.instagramlogout);
        Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profileimgdelet);
        logoutname.setText(user.getDisplayName());
        logoutemail.setText(user.getEmail());





        profileimgdelet.setOnClickListener(this);

        delete.setOnClickListener(this);
        deletaccounts.setOnClickListener(this);
//
        deletlogout.setOnClickListener(this);
//
//        insta.setOnClickListener(this);

        sh = getSharedPreferences("log", MODE_PRIVATE);



        darkmode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  String[] languages ={"Dark Mode","Light Mode"};
                AlertDialog.Builder mbuilder=new AlertDialog.Builder(Settings.this);
                mbuilder.setTitle("Theams");
                mbuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which==0)
                        {


                        }
                        else  if(which==1)
                        {


                        }


                        dialog.dismiss();
                    }
                });
                AlertDialog m=mbuilder.create();
                m.show();

            }
        });





        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlanguages();
            }
        });
    }

    private void showlanguages() {
        final  String[] languages ={"English","हिन्दी","മലയാളം","தமிழ்","ಕನ್ನಡ","తెలుగు"};
        AlertDialog.Builder mbuilder=new AlertDialog.Builder(Settings.this);
        mbuilder.setTitle("Choose Language");
        mbuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0)
                {
                    setLocale("en");
                    recreate();

                }
                else  if(which==1)
                {
                    setLocale("hi");
                    recreate();

                }
                else  if(which==2)
                {
                    setLocale("ml");
                    recreate();
                }
                else  if(which==3)
                {
                    setLocale("ta");
                    recreate();
                }

                dialog.dismiss();
            }
        });
        AlertDialog m=mbuilder.create();
        m.show();
    }
    public void setLocale(String hi) {
        Locale locale=new Locale(hi);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
       getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        SharedPreferences sh=getSharedPreferences("Settings",MODE_PRIVATE);
        SharedPreferences.Editor editor=sh.edit();
        editor.putString("my",hi);
        editor.apply();
    }
    public void loadLocale()
    {
       SharedPreferences sharedPreferences=getSharedPreferences("Settings",MODE_PRIVATE);
       String language=sharedPreferences.getString("my","");
       setLocale(language);

    }

    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.deleteaccount:
                bottomSheetDialog.show();
                break;
            case R.id.deletaccounts:

            {

                AlertDialog.Builder builder = new AlertDialog.Builder(
                        Settings.this);
                builder.setTitle("DELETE ACCOUNT");
                builder.setMessage("Are you sure?");
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                deleteaccount();
                            }

                            private void deleteaccount() {

                                final ProgressDialog progress = new ProgressDialog(Settings.this);
                                progress.setMessage(" Please wait... ");
                                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                //  progress.setIndeterminate(true);
                                progress.show();
                                if(user.getUid()!=null) {


                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                    Query applesQuery = ref.child("REGISTRATION").orderByChild("id").equalTo(user.getUid());
                                    applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                                                appleSnapshot.getRef().removeValue();


                                                AuthUI.getInstance()
                                                        .delete(Settings.this)

                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                // user is now signed out
                                                                SharedPreferences.Editor e = sh.edit();
                                                                e.clear();
                                                                e.apply();
                                                                startActivity(new Intent(getApplicationContext(), Login.class));
                                                                // finish();
                                                                progress.dismiss();
                                                            }
                                                        });


                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            // Log.e(TAG, "onCancelled", databaseError.toException());
                                        }
                                    });


                                }




                            }
                        });
                builder.show();

// Create the AlertDialog
                AlertDialog dialog = builder.create();









            }



                break;

            case R.id.deletlogout:


                final ProgressDialog progress = new ProgressDialog(Settings.this);
                progress.setTitle("Loading");
                progress.setMessage("Wait while loading...");
                progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
                progress.show();
                user = FirebaseAuth.getInstance().getCurrentUser();
                if (user.getUid() != null) {

                    final SharedPreferences sh1 = getSharedPreferences("userdatastemp", MODE_PRIVATE);

                    SharedPreferences.Editor ee = sh1.edit();

                    ee.putString("id", user.getUid());
                    ee.putString("name", user.getDisplayName());
                    ee.putString("email", user.getEmail());
                    ee.putString("pid", user.getProviderId());
                    ee.putString("image", String.valueOf(user.getPhotoUrl()));

                    ee.apply();


                    // user is now signed out
                    SharedPreferences.Editor e = sh.edit();
                    e.clear();
                    e.apply();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    // finish();

                    progress.dismiss();


                } else {
                    Toast.makeText(getApplicationContext(), "somthing Wrong", Toast.LENGTH_LONG).show();


                }


                break;


        }

    }




}