package techsays.in;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static techsays.in.R.color.colorPrimary;


public class Home extends AppCompatActivity implements View.OnClickListener ,NavigationView.OnNavigationItemSelectedListener {



    TextView usernamedisplay;
    ImageView profileimghome,nav,msg;
    SharedPreferences sh1, sh;
    ImageView profileimglogout;
    private BottomSheetDialog bottomSheetDialog;
    FloatingActionButton contact;
    boolean doubleBackToExitPressedOnce = false;

    SharedPreferences she;
    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NO = 0;
    private Context mContext = Home.this;
        @Override
        protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drwer_home);
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        she=getSharedPreferences("log",MODE_PRIVATE);
        SharedPreferences.Editor e=she.edit();
        e.putBoolean("id",true);

        e.apply();


            sh1 = getSharedPreferences("LOGINDATA", MODE_PRIVATE);
            sh = getSharedPreferences("log", MODE_PRIVATE);

            bottomSheetDialog = new BottomSheetDialog(Home.this);
            View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.logout_and_info, null);
            bottomSheetDialog.setContentView(bottomSheetDialogView);
            profileimghome = findViewById(R.id.profileimghome);
            usernamedisplay = findViewById(R.id.usernamedisplay);

            contact = findViewById(R.id.location);


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
            msg = findViewById(R.id.msg);
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

                    Intent messg=new Intent(getApplicationContext(), Message.class);
                    startActivity(messg);


                }
            });







            //  firebaseMethods.signUp();

        //  initImageLoader();
        setupBottomNavigationView();
        setupViewPager();

    }



        private void setupViewPager() {
        HomePageAdapter adapter = new HomePageAdapter(Home.this.getSupportFragmentManager());
        adapter.addFragment(new CourseFirstFragment());
        adapter.addFragment(new OnlineclassFragment());
        adapter.addFragment(new CodeFragment());

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText("Course");
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText("Class");
        Objects.requireNonNull(tabLayout.getTabAt(2)).setText("Code");
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
        else if (id == R.id.nav_about) {

            final Intent log13 = new Intent(Home.this, About_us.class);

            startActivity(log13);


        }


        else if (id == R.id.nav_settings) {

            final Intent log13 = new Intent(Home.this, Settings.class);

            startActivity(log13);


        }
        else if (id == R.id.nav_callbackk) {

            new SweetAlertDialog(Home.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Are you sure?")
                    .setContentText("Call Back Now")
                    .setConfirmText("Delete!")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismissWithAnimation();
                        }
                    })
                    .show();
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



}

