package techsays.in;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.graphics.Color.GREEN;
import static com.facebook.FacebookSdk.getApplicationContext;

public class Login extends AppCompatActivity {
    ConstraintLayout vie;
    int RC_SIGN_IN = 0;
    RelativeLayout signInButton;
    RelativeLayout facebooknButton;
    RelativeLayout  emailbtn;
    EditText username,pssword;
    Button sign,createaccount;
    Snackbar s;
    private boolean loggedIn = false;
    SharedPreferences sharedPreferences,sh,sh1,sh2;
    RelativeLayout pass,pass1;
    TextView name;
    ProgressBar progressBar;
    ImageView profileimg;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        vie=findViewById(R.id.vie);

        profileimg=findViewById(R.id.profileimg);
        name=findViewById(R.id.names);


        sh2 = getSharedPreferences("userdatastemp", MODE_PRIVATE);
        String name1=sh2.getString("name", null);
        String img=String.valueOf(sh2.getString("image", null));


        if (name1 != null && img != null )
        {
            Picasso.get().load(sh2.getString("image", null)).into(profileimg);
            name.setText(sh2.getString("name", null));

        }


        else{

            profileimg.setImageResource(R.drawable.user_image);
            name.setText("Hi");
        }







        sh1= Objects.requireNonNull(getApplicationContext()).getSharedPreferences("LOGINDATA",MODE_PRIVATE);

        //  Picasso.get().load(sh1.getString("image",null)).into(profileimg);


        FirebaseApp.initializeApp(this);
        sh=getSharedPreferences("log",MODE_PRIVATE);
        loggedIn=sh.getBoolean("id",false);
        sharedPreferences=getSharedPreferences("phone",MODE_PRIVATE);
        if (loggedIn) {
            startActivity(new Intent(Login.this,Home.class));
            // Snackbar.make(v,"Enter emergency number",Snackbar.LENGTH_SHORT).show();

        }


        signInButton = findViewById(R.id.sign_in_button);
        facebooknButton = findViewById(R.id.faceBookBtn);
        emailbtn = findViewById(R.id.emailbtn);



        Animation top_curve_anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.edittext_anim);
        signInButton.startAnimation(top_curve_anim);
        facebooknButton.startAnimation(top_curve_anim);

        emailbtn.startAnimation(top_curve_anim);






        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        //  new AuthUI.IdpConfig.PhoneBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build()
                        //new AuthUI.IdpConfig.FacebookBuilder().build()
                );
// Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),

                        12);


            }
        });



        facebooknButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        new AuthUI.IdpConfig.FacebookBuilder().build()

                );
// Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),

                        12);



            }
        });

        emailbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                List<AuthUI.IdpConfig> providers = Arrays.asList(
                        //   new AuthUI.IdpConfig.TwitterBuilder().build()
                        new AuthUI.IdpConfig.EmailBuilder().build()
                        //  new AuthUI.IdpConfig.GoogleBuilder().build()
                        //  new AuthUI.IdpConfig.FacebookBuilder().build()
                );

// Create and launch sign-in intent
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),

                        12);


            }
        });




    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);

    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 12) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
// Successfully signed in
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){
                    DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("REGISTRATION");
                    userRef
                            .orderByChild("personPhoto")
                            .equalTo(String.valueOf(user.getPhotoUrl()))
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.getValue() != null) {

                                        final Intent log = new Intent(Login.this, Home.class);
                                        final SharedPreferences sh=getSharedPreferences("LOGINDATA",MODE_PRIVATE);

                                        SharedPreferences.Editor ee=sh.edit();
                                        ee.putString("id",user.getUid());
                                        ee.putString("name",user.getDisplayName());
                                        ee.putString("email",user.getEmail());
                                        ee.putString("pid",user.getProviderId());
                                        ee.putString("image",String.valueOf(user.getPhotoUrl()));

                                        ee.apply();

                                        startActivity(log);



                                    } else {

                                        final Intent log1 = new Intent(Login.this, Registration.class);

                                        startActivity(log1);

                                    }

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                }

            }
            else {
                s = Snackbar.make(vie, "SOMTHING WAS WRONG", Snackbar.LENGTH_SHORT);
                View snackBarView = s.getView();
                snackBarView.setBackgroundColor(GREEN);
                s.show();

                return;
            }
        }
    }
}