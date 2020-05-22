package techsays.in;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import static android.content.Context.MODE_PRIVATE;
public class CourseFirstFragment extends Fragment  {
    View root;
    TextView androidbtn;
    ImageView profileimghome,nav,msg;
    SharedPreferences sh1, sh;
    ImageView profileimglogout;
    private BottomSheetDialog bottomSheetDialog;
FloatingActionButton contact;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sh1 = requireActivity().getSharedPreferences("LOGINDATA", MODE_PRIVATE);
        sh = getActivity().getSharedPreferences("log", MODE_PRIVATE);

androidbtn=root.findViewById(R.id.androidcouresebtn);






final String a="hfgdghf";

androidbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


         Intent log = new Intent(getActivity(), AndroidCourese.class);
        log.putExtra("aa",a);
        startActivity(log);


    }



});

        return root;
    }





}

