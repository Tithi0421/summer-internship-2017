package com.elitewaresolutions.krte.Fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.activity.ConnectionDetector;
import com.elitewaresolutions.krte.activity.NonSwipeableViewPager;
import com.elitewaresolutions.krte.activity.ViewPagerActivity;
import com.elitewaresolutions.krte.database.DbQueries;
import com.elitewaresolutions.krte.model.UserForms;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

/**
 * Created by Tithi on 17-06-2017.
 */
public class UserForms3Fragment extends Fragment {


    Button submit;
    Button pre;
    Switch lamination;
    Switch crack;
    Switch hydro;
    Switch range;
    Switch valve;
    String lamicheck;
    String crackcheck;
    String hydrocheck;
    String rangecheck;
    String valvecheck;

    private long id;
    private  Firebase mRef;
    private DatabaseReference mDatabase;
   // private BroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;
    private UserForms userForms;
    private DbQueries dbqueries;
    private SharedPreferenceUtils sharedPreferenceUtils;

    private String title;
    private int page;
    static NonSwipeableViewPager vpPager = ViewPagerActivity.vpPager;
    // newInstance constructor for creating fragment with arguments
    public static UserForms3Fragment newInstance(int page, String title) {
        UserForms3Fragment userforms3fragment = new UserForms3Fragment();
        Bundle args = new Bundle();
        args.putInt("3", page);
        args.putString("Title3", title);
        userforms3fragment.setArguments(args);
        return userforms3fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("3", 0);
        title = getArguments().getString("Title3");
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_userforms3, container, false);

        submit = (Button) rootView.findViewById(R.id.Submit);
        pre = (Button)rootView.findViewById(R.id.pre2);
        lamination = (Switch)rootView.findViewById(R.id.s6);
        crack = (Switch)rootView.findViewById(R.id.s7);
        hydro = (Switch)rootView.findViewById(R.id.s8);
        range = (Switch)rootView.findViewById(R.id.s9);
        valve = (Switch)rootView.findViewById(R.id.s10);
        Firebase.setAndroidContext(getActivity());
        mRef = new Firebase("https://krte-8d43f.firebaseio.com/");
        mDatabase = FirebaseDatabase.getInstance().getReference();

        addListenerOnButton1(rootView);
        addListenerOnButton(rootView);

        // Inflate the layout for this fragment
        return rootView;

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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

        if(lamination.isChecked()){
            lamicheck = "Yes";
        } else { lamicheck = "No";   }
        if(crack.isChecked()){
            crackcheck = "Yes";
        } else { crackcheck = "No";   }
        if(hydro.isChecked()){
            hydrocheck = "Yes";
        } else { hydrocheck = "No";   }
        if(range.isChecked()){
            rangecheck = "Yes";
        } else { rangecheck = "No";   }
        if(valve.isChecked()){
            valvecheck = "Yes";
        } else { valvecheck = "No";   }

    }



    private void addListenerOnButton(final View rootView) {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setItemOnSwitch();
                insertInDb(lamicheck,crackcheck,hydrocheck,rangecheck,valvecheck);

            }


        });

    }
    private void insertInDb( String v6, String v7, String v8, String v9, String v10) {
     /*   UserForms userForms=new UserForms();
        userForms.setLam(v6);
        userForms.setCrack(v7);
        userForms.setHydro(v8);
        userForms.setRange(v9);
        userForms.setValve(v10);
*/
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
        String value = SharedPreferenceUtils.getInstance().getString(getActivity(),"key1");
        Gson gson= new Gson();
        userForms = gson.fromJson(value, UserForms.class);
        userForms.setLam(v6);
        userForms.setCrack(v7);
        userForms.setHydro(v8);
        userForms.setRange(v9);
        userForms.setValve(v10);

        dbqueries = new DbQueries(getActivity());
         id = dbqueries.addUserForms(userForms);
        Snackbar.make(submit, "Entries are successfully added into Database", Snackbar.LENGTH_LONG).show();


       // IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");



       ConnectionDetector connectionDetector = new ConnectionDetector(getActivity());
        if(connectionDetector.isConnected()){

          //   Firebase mRefChild = mRef.child("name");
           // mRefChild.setValue("value");
           // SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance();

            String uid = sharedPreferenceUtils.getString(getActivity(),"key");
            String key = mDatabase.push().getKey();
            mDatabase.child("users").child(uid).child(key).setValue(userForms);
            Snackbar.make(submit, "Entries are successfully added", Snackbar.LENGTH_LONG).show();

            dbqueries.deleteUserForms(id);

        }
       /* else {

        } */
    }


  /*  public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        int[] type = {ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};

        @Override
        public void onReceive(Context context,Intent intent ) {
            if (NetworkMonitor.isNetworkAvailable(context, type)) {
                String uid = SharedPreferenceUtils.getInstance().getString(getActivity(), "key");
                String key = mDatabase.push().getKey();
                mDatabase.child("users").child(uid).child(key).setValue(userForms);
                Snackbar.make(submit, "Entries are successfully added", Snackbar.LENGTH_LONG).show();
                dbqueries = new DbQueries(getActivity());
                dbqueries.deleteUserForms(id);
            }
        }
    }; */

  /*  @Override
   public void onStart() {
        super.onStart();
        getActivity().registerReceiver(broadcastReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE") );
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().unregisterReceiver(broadcastReceiver);
    } */
}
