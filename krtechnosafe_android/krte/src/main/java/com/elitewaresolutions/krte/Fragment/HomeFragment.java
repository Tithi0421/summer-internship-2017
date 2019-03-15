package com.elitewaresolutions.krte.Fragment;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.activity.ActivityNotifications;
import com.elitewaresolutions.krte.adapter.CustomPagerAdapter;


public class HomeFragment extends Fragment {
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private Button button;
    String v,v1,v2,v3;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         mCustomPagerAdapter = new CustomPagerAdapter(getActivity());

        mViewPager = (ViewPager)rootView.findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);


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
    public void addListenerOnButton(View rootView) {
        button=(Button)rootView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ActivityNotifications.class);
                startActivity(i);

            }
        });
    }

}

