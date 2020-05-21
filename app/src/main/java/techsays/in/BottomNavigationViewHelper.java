package techsays.in;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationView bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");




//        bottomNavigationViewEx.enableAnimation(false);
//        bottomNavigationViewEx.enableItemShiftingMode(false);
//        bottomNavigationViewEx.enableShiftingMode(false);
//        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, final Activity callingActivity, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.navigation_home:
                        if(callingActivity.getClass()!=Home.class) {
                            Intent intent1 = new Intent(context, Home.class);//ACTIVITY_NUM = 0
                            //intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent1);
                            callingActivity.overridePendingTransition(0, 0);
                        }
                        break;
//
                    case R.id.navigation_dashboard:
                        if(callingActivity.getClass()!=Add_videos.class) {
                            Intent intent2 = new Intent(context, Add_videos.class);//ACTIVITY_NUM = 1
                            //intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent2);
                            callingActivity.overridePendingTransition(0, 0);
                        }
                        break;

                    case R.id.navigation_notifications:
                        if(callingActivity.getClass()!=Notification.class) {
                            Intent intent3 = new Intent(context, Notification.class);//ACTIVITY_NUM = 2
                            context.startActivity(intent3);
                            callingActivity.overridePendingTransition(0, 0);
                        }
                        break;
//
                    case R.id.navigation_youtube:
                        if(callingActivity.getClass()!= Youtube.class) {
                            Intent intent4 = new Intent(context, Youtube.class);//ACTIVITY_NUM = 3
                            //intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent4);
                            callingActivity.overridePendingTransition(0, 0);
                        }
                        break;
//
                    case R.id.navigation_profile:
                        if(callingActivity.getClass()!=Profile.class) {
                            Intent intent5 = new Intent(context, Profile.class);//ACTIVITY_NUM = 4
                            //intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent5);
                            callingActivity.overridePendingTransition(0, 0);
                        }
                        break;
                }


                return false;
            }
        });
    }
}
