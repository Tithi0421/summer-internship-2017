package com.elitewaresolutions.krte.Fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.activity.NonSwipeableViewPager;
import com.elitewaresolutions.krte.activity.ViewPagerActivity;
import com.elitewaresolutions.krte.model.UserForms;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

/**
 * Created by Tithi on 17-06-2017.
 */
public class UserForms2Fragment extends Fragment {


    private Button next;
    private Button pre;
    private Switch shell;
    private Switch smooth;
    private Switch drain;
    private Switch tight;
    private Switch thick;
    private String shellcheck;
    private String smoothcheck;
    private String draincheck;
    private String tightcheck;
    private String thickcheck;
    private String title;
    private int page;
    static NonSwipeableViewPager vpPager = ViewPagerActivity.vpPager;

    private SharedPreferenceUtils sharedPreferenceUtils;
    private UserForms userForms;
    private String value;

    // newInstance constructor for creating fragment with arguments
    public static UserForms2Fragment newInstance(int page, String title) {
        UserForms2Fragment userformsmoothfragment = new UserForms2Fragment();
        Bundle args = new Bundle();
        args.putInt("2", page);
        args.putString("Title2", title);
        userformsmoothfragment.setArguments(args);
        return userformsmoothfragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("2", 0);
        title = getArguments().getString("Title2");
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_userforms2, container, false);
        next= (Button)rootView.findViewById(R.id.next2);
        pre = (Button)rootView.findViewById(R.id.pre1);
        shell = (Switch)rootView.findViewById(R.id.s1);
        smooth = (Switch)rootView.findViewById(R.id.s2);
        drain = (Switch)rootView.findViewById(R.id.s3);
        tight = (Switch)rootView.findViewById(R.id.s4);
        thick = (Switch)rootView.findViewById(R.id.s5);
        addListenerOnButton(rootView);
        addListenerOnButton1(rootView);
        // Inflate the layout for this fragment
        return rootView;

    }

    private void addListenerOnButton1(View rootView) {
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ViewPagerActivity)getActivity()).setCurrentItem1(true);
            }

    });
}

    private void setItemOnSwitch() {

        if(shell.isChecked()){
            shellcheck = "Yes";
        } else { shellcheck = "No";   }
        if(smooth.isChecked()){
            smoothcheck = "Yes";
        } else { smoothcheck = "No";   }
        if(drain.isChecked()){
            draincheck = "Yes";
        } else { draincheck = "No";   }
        if(tight.isChecked()){
            tightcheck = "Yes";
        } else { tightcheck = "No";   }
        if(thick.isChecked()){
            thickcheck = "Yes";
        } else { thickcheck = "No";   }

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void addListenerOnButton(final View rootView) {

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setItemOnSwitch();
                insertInDb(shellcheck,smoothcheck,draincheck,tightcheck,thickcheck);

                ((ViewPagerActivity)getActivity()).setCurrentItem(true);
            }

        });
    }
    private void insertInDb( String v1, String v2, String v3, String v4, String v5) {
        /*UserForms userForms = new UserForms();
        userForms.setShell(v1);
        userForms.setSmooth(v2);
        userForms.setDrain(v3);
        userForms.setTight(v4);
        userForms.setThick(v5);
*/
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
        value = sharedPreferenceUtils.getString(getActivity(),"key1");
        Gson gson= new Gson();
        userForms = gson.fromJson(value, UserForms.class);
        userForms.setShell(v1);
        userForms.setSmooth(v2);
        userForms.setDrain(v3);
        userForms.setTight(v4);
        userForms.setThick(v5);
        String val = gson.toJson(userForms);
        sharedPreferenceUtils.setString(getActivity(),"key1",val);

    }
}
