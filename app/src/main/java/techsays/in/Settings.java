package techsays.in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Settings extends AppCompatActivity {
    TextView cl,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        cl = findViewById(R.id.cl);
        delete=findViewById(R.id.deleteaccount);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



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

}