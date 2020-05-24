package techsays.in;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView webview,profile,backarrow;
    TextView name,email;
     FirebaseUser user;
    private static final int ACTIVITY_NUM = 4;
    private AppBarConfiguration mAppBarConfiguration;
    private static final String TAG = "SearchActivity";
    private Context mContext = Profile.this;
SharedPreferences sh;
RelativeLayout relativeLayout;
    SharedPreferences phonepref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drwer);
        setupBottomNavigationView();
       phonepref=getSharedPreferences("data",MODE_PRIVATE);
relativeLayout=findViewById(R.id.proview);
        sh = getSharedPreferences("log", MODE_PRIVATE);


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ImageView menuIcon = (ImageView) findViewById(R.id.navbtn);
        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView navemail = headerView.findViewById(R.id.navemail);
        ImageView profileImageView = headerView.findViewById(R.id.navprofile);
        TextView navname = headerView.findViewById(R.id.navname);

        user = FirebaseAuth.getInstance().getCurrentUser();
//        navprofile=findViewById(R.id.navprofile);
        backarrow=findViewById(R.id.backarrow);

        webview=findViewById(R.id.webviewtwchsays);
        name=findViewById(R.id.profilename);
        email=findViewById(R.id.profileemail);
        profile=findViewById(R.id.profileimg);
        Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profile);
        name.setText(user.getDisplayName());
        Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profileImageView);
        navname.setText(user.getDisplayName());
        navemail.setText(user.getEmail());

        email.setText(user.getEmail());
        webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent log1 = new Intent(Profile.this, Webwiewtechsays.class);

                startActivity(log1);


            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Intent logg = new Intent(Profile.this, Home.class);

                startActivity(logg);


            }
        });


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            final Intent log12 = new Intent(Profile.this, Home.class);

            startActivity(log12);        }

        else if (id == R.id.nav_msg) {


            final Intent log12 = new Intent(Profile.this, Message.class);

            startActivity(log12);


        }
        else if (id == R.id.nav_contact) {


            final Intent log12 = new Intent(Profile.this, Contacts.class);

            startActivity(log12);


        }
        else if (id == R.id.nav_about) {

            final Intent log13 = new Intent(Profile.this, About_us.class);

            startActivity(log13);


        }


        else if (id == R.id.nav_settings) {

            final Intent log13 = new Intent(Profile.this, Settings.class);

            startActivity(log13);


        }

        else if (id == R.id.nav_payment) {


            final Intent pay = new Intent(Profile.this, Payment.class);

            startActivity(pay);


        }



        else if (id == R.id.nav_callbackk) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    Profile.this);
            builder.setTitle("Are you sure?");
            builder.setMessage("Call Back Now");
            builder.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
                        }
                    });
            builder.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            callback();
                        }
                    });
            builder.show();
        }
        else if (id == R.id.nav_share) {
            Bitmap imgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.reward);
            String imgBitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(),imgBitmap,"Techsays",null);
            Uri imgBitmapUri = Uri.parse(imgBitmapPath);

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM,imgBitmapUri);
            shareIntent.setType("*/*");
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Techsays's Official App From Play Store:https://play.google.com/store/apps/details?id="+getPackageName()+"\n"+" And get Recharged Your Phone with Our Watch and earn Task.For ,More detials contact us +91 9847423836, +91 8848968113");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Techsays");
            startActivity(Intent.createChooser(shareIntent, "Share this"));
        }
        else if (id == R.id.nav_logout) {



            final ProgressDialog progress = new ProgressDialog(Profile.this);
            progress.setTitle("Loading");
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progress.show();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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




        }




        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(Gravity.RIGHT);
        return true;
    }




    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView bottomNavigationViewEx = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext, this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


public void callback()
{
    StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://techsays.in/sms.php",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                   Snackbar.make(relativeLayout,response,BaseTransientBottomBar.LENGTH_LONG).show();
/* if (response.equals("success")) {

Toast.makeText(MainActivity.this, "success upload", Toast.LENGTH_SHORT).show();
//Creating a shared preference

} else {
Toast.makeText(MainActivity.this, "success failed", Toast.LENGTH_SHORT).show();
//If the server response is not success
//Displaying an error message on toast
// Toast.makeText(VerificationActivity.this,"success upload fail",Toast.LENGTH_SHORT).show();
}*/
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                }

            }) {

        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
//Adding parameters to request
            params.put("phone", phonepref.getString("phone",null));
            params.put("name",user.getDisplayName());
//returning parameter
            return params;
        }
    };
    RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);
    requestQueue.add(stringRequest);
}

}


