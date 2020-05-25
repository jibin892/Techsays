package techsays.in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class Home extends AppCompatActivity implements View.OnClickListener ,NavigationView.OnNavigationItemSelectedListener {
    private BroadcastReceiver MyReceiver = null;
final int RequestPermissionCode=1;
    SharedPreferences phonepref;
    TextView usernamedisplay;
    ImageView profileimghome,nav,msg;
    SharedPreferences sh1, sh;
    ImageView profileimglogout;
    private BottomSheetDialog bottomSheetDialog;
    FloatingActionButton contact;

    String ph;

    boolean doubleBackToExitPressedOnce = false;
ConstraintLayout constraintLayout;
    SharedPreferences she;
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NO = 0;
    FirebaseUser user;
    private Context mContext = Home.this;
        @Override
        protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drwer_home);
            EnableRuntimePermission();
            MyReceiver = new MyReceiver();
            broadcastIntent();
       user = FirebaseAuth.getInstance().getCurrentUser();
constraintLayout=findViewById(R.id.homeview);
        she=getSharedPreferences("log",MODE_PRIVATE);
        SharedPreferences.Editor e=she.edit();
        e.putBoolean("id",true);
        e.apply();


            loadLocale();
            userdataload();






            phonepref=getSharedPreferences("databasedata",MODE_PRIVATE);
            sh1 = getSharedPreferences("LOGINDATA", MODE_PRIVATE);
            sh = getSharedPreferences("log", MODE_PRIVATE);
            bottomSheetDialog = new BottomSheetDialog(Home.this);
            View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.logout_and_info, null);
            bottomSheetDialog.setContentView(bottomSheetDialogView);

            msg = findViewById(R.id.msghome);

            profileimghome = findViewById(R.id.profileimghome);
            usernamedisplay = findViewById(R.id.usernamedisplay);
            final DrawerLayout drawer = findViewById(R.id.drawer_layout1);
            ImageView menuIcon = (ImageView) findViewById(R.id.navhombtn);
            menuIcon.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    drawer.openDrawer(Gravity.LEFT);
                }
            });
            NavigationView navigationView = findViewById(R.id.nav_view1);
            navigationView.setItemIconTintList(null);
            navigationView.setNavigationItemSelectedListener(this);

            View headerView = navigationView.getHeaderView(0);
            ImageView profileImageView = headerView.findViewById(R.id.navprofile);
            TextView navname = headerView.findViewById(R.id.navname);
            TextView navemail = headerView.findViewById(R.id.navemail);
            navemail.setText(user.getEmail());
            Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profileImageView);
            navname.setText(user.getDisplayName());





            nav = findViewById(R.id.nav);
            Button logout = bottomSheetDialogView.findViewById(R.id.logout);
            profileimglogout = bottomSheetDialogView.findViewById(R.id.profileimglogout);
            TextView logoutemail = bottomSheetDialogView.findViewById(R.id.logoutemail);


            TextView logoutname = bottomSheetDialogView.findViewById(R.id.logoutname);
            RelativeLayout facebook = bottomSheetDialogView.findViewById(R.id.faceBookBtnlogout);
            RelativeLayout googel = bottomSheetDialogView.findViewById(R.id.sign_in_buttonlogout);
            RelativeLayout insta = bottomSheetDialogView.findViewById(R.id.instagramlogout);
            Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profileimglogout);
            logoutname.setText(user.getDisplayName());
            usernamedisplay.setText(user.getDisplayName());
            logoutemail.setText(user.getEmail());







            logout.setOnClickListener(this);

            profileimglogout.setOnClickListener(this);

            profileimghome.setOnClickListener(this);
            facebook.setOnClickListener(this);

            googel.setOnClickListener(this);

            insta.setOnClickListener(this);


            Picasso.get().load(String.valueOf(user.getPhotoUrl())).into(profileimghome);




msg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        final Intent msgss = new Intent(Home.this, Message.class);

        startActivity(msgss);



    }
});




            //  firebaseMethods.signUp();

        //  initImageLoader();
        setupBottomNavigationView();
        setupViewPager();

    }

    private void userdataload() {
        final FirebaseUser userss = FirebaseAuth.getInstance().getCurrentUser();
       DatabaseReference mUserDatabase = FirebaseDatabase.getInstance().getReference().child("REGISTRATION").child(userss.getDisplayName());
        mUserDatabase.keepSynced(true);

        mUserDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String id = dataSnapshot.child("id").getValue().toString();
                 ph = dataSnapshot.child("phone").getValue().toString();
                String name = dataSnapshot.child("personName").getValue().toString();
                String photo = dataSnapshot.child("personPhoto").getValue().toString();
                String email = dataSnapshot.child("personEmail").getValue().toString();



                final SharedPreferences sh=getSharedPreferences("databasedata",MODE_PRIVATE);

                SharedPreferences.Editor ee=sh.edit();

                ee.putString("id",id);
                ee.putString("name",name);
                ee.putString("email",email);
                ee.putString("image",photo);
                ee.putString("phone",ph);
                ee.apply();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

            }




    private void setupViewPager() {
        HomePageAdapter adapter = new HomePageAdapter(Home.this.getSupportFragmentManager());
        adapter.addFragment(new CourseFirstFragment());
        adapter.addFragment(new OnlineclassFragment());
        adapter.addFragment(new WalletFragment());

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(getString(R.string.course));
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(getString(R.string.service));
        Objects.requireNonNull(tabLayout.getTabAt(2)).setText(getString(R.string.wallet));
       // viewPager.setCurrentItem(1,false);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

    }



        /**
         * BottomNavigationView setup
         */
        private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationView bottomNavigationViewEx = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NO);
        menuItem.setChecked(true);

    }





    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {

            case R.id.profileimghome:
                bottomSheetDialog.show();
                break;
            case R.id.logout:
                logoutt();

                break;

            case R.id.faceBookBtnlogout:
                fbook();

                break;


            case R.id.sign_in_buttonlogout:
                gmail();

                break;

            case R.id.instagramlogout:
                instagram();

                break;


        }

    }

    private void gmail() {


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/pscTrolls"));
        startActivity(intent);




    }

    private void instagram() {



        Uri uri = Uri.parse("https://www.instagram.com/psctrolls/?hl=en");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/psctrolls/?hl=en")));
        }

    }

    private void fbook() {


        String facebookUrl = "https://www.facebook.com/techsayssoftwaresolutions/?modal=admin_todo_tour";
        String facebookID = "111754506931637";

        try {
            int versionCode = getPackageManager().getPackageInfo("com.facebook.katana", 0).versionCode;

            if (!facebookID.isEmpty()) {
                // open the Facebook app using facebookID (fb://profile/facebookID or fb://page/facebookID)
                Uri uri = Uri.parse("fb://page/" + facebookID);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else if (versionCode >= 3002850 && !facebookUrl.isEmpty()) {
                // open Facebook app using facebook url
                Uri uri = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            } else {
                // Facebook is not installed. Open the browser
                Uri uri = Uri.parse(facebookUrl);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        } catch (PackageManager.NameNotFoundException e) {
            // Facebook is not installed. Open the browser
            Uri uri = Uri.parse(facebookUrl);
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }




    }

    private void logoutt() {
        final ProgressDialog progress = new ProgressDialog(Home.this);
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

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(Gravity.LEFT)) {
            drawer.closeDrawer(Gravity.LEFT);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            final Intent log12 = new Intent(Home.this, Home.class);

            startActivity(log12);        }


        else if (id == R.id.nav_contact) {


            final Intent log12 = new Intent(Home.this, Contacts.class);

            startActivity(log12);


        }

        else if (id == R.id.nav_payment) {


            final Intent pay = new Intent(Home.this, Payment.class);

            startActivity(pay);


        }

        else if (id == R.id.nav_msg) {


            final Intent log12 = new Intent(Home.this, Message.class);

            startActivity(log12);


        }



        else if (id == R.id.nav_about) {

            final Intent log13 = new Intent(Home.this, About_us.class);

            startActivity(log13);


        }


        else if (id == R.id.nav_settings) {

            final Intent log13 = new Intent(Home.this, Settings.class);

            startActivity(log13);


        }
        else if (id == R.id.nav_callbackk) {


            AlertDialog.Builder builder = new AlertDialog.Builder(
                    Home.this);
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
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Download Techsays's Official App From Play Store:https://play.google.com/store/apps/details?id="+getPackageName()+"\n"+" And get Recharged Your Phone with Our Watch and earn Task.For ,More details contact us +91 9847423836, +91 8848968113");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Techsays");
            startActivity(Intent.createChooser(shareIntent, "Share this"));

        }
        else if (id == R.id.nav_logout) {



            final ProgressDialog progress = new ProgressDialog(Home.this);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(Gravity.LEFT);
        return true;
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
    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Home.this,
                Manifest.permission.CALL_PHONE)) {

// Toast.makeText(Cpature_image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Home.this, new String[]{
                    Manifest.permission.CALL_PHONE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.CHANGE_NETWORK_STATE,Manifest.permission.CHANGE_WIFI_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);


        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

// Toast.makeText(Cpature_image.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(this, "Permission Cancelled,Please Grant Permission", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
    public void callback()
    {

     //   Toast.makeText(Home.this, phonepref.getString("phone",null)+user.getDisplayName(),Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://techsays.in/sms.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Snackbar.make(constraintLayout,response,BaseTransientBottomBar.LENGTH_LONG).show();



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
        RequestQueue requestQueue = Volley.newRequestQueue(Home.this);
        requestQueue.add(stringRequest);
    }
    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }



}

