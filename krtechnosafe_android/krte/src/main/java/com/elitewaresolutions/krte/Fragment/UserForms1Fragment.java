package com.elitewaresolutions.krte.Fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.activity.ViewPagerActivity;
import com.elitewaresolutions.krte.model.UserForms;
import com.elitewaresolutions.krte.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Tithi on 17-06-2017.
 */
public class UserForms1Fragment extends Fragment {

    private Calendar myCalendar;
    private EditText equip;
    private EditText equipsr;
    private EditText make;
    private EditText date1;
    private EditText cap;
    private EditText loc;
    private Button next;
    private String title;
    private int page;

    private SharedPreferenceUtils sharedPreferenceUtils;
    private UserForms userForms;
    String val;

    // newInstance constructor for creating fragment with arguments
    public static UserForms1Fragment newInstance(int page, String title) {
        UserForms1Fragment userforms1fragment = new UserForms1Fragment();
        Bundle args = new Bundle();
        args.putInt("1", page);
        args.putString("Title1", title);
        userforms1fragment.setArguments(args);
        return userforms1fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("1", 0);
        title = getArguments().getString("Title1");

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_userforms1, container, false);
        myCalendar = Calendar.getInstance();
        equip = (EditText) rootView.findViewById(R.id.equip);
        equipsr = (EditText) rootView.findViewById(R.id.equipsr);
        make = (EditText) rootView.findViewById(R.id.make);
        date1 = (EditText) rootView.findViewById(R.id.date);
        cap = (EditText) rootView.findViewById(R.id.cap);
        loc = (EditText) rootView.findViewById(R.id.loc);
        next = (Button) rootView.findViewById(R.id.next1);
        addListenerOnDate(rootView);
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


    private void addListenerOnDate(View rootView) {


        date1.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                new DatePickerDialog(getActivity(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @TargetApi(Build.VERSION_CODES.N)
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        date1.setText(sdf.format(myCalendar.getTime()));

    }

    private void addListenerOnButton(final View rootView) {

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equip.getText().toString().length() == 0) {
                    equip.setError("This field is required!");
                    equip.requestFocus();
                    return;
                }
                if (equipsr.getText().toString().length() == 0) {
                    equipsr.setError("This field is required!");
                    equipsr.requestFocus();
                    return;
                }
                if (make.getText().toString().length() == 0) {
                    make.setError("This field is required!");
                    make.requestFocus();
                    return;
                }

                if (date1.getText().toString().length() == 0) {
                    date1.setError("This field is required!");
                    date1.requestFocus();
                    return;
                }

                if (cap.getText().toString().length() == 0) {
                    cap.setError("This field is required!");
                    cap.requestFocus();
                    return;
                }

                if (loc.getText().toString().length() == 0) {
                    loc.setError("This field is required!");
                    loc.requestFocus();
                    return;
                } else {

                    insertInDb(equip.getText().toString().trim(), equipsr.getText().toString().trim(), make.getText().toString().trim(), date1.getText().toString().trim(), cap.getText().toString().trim(), loc.getText().toString().trim());

                }
                ((ViewPagerActivity)getActivity()).setCurrentItem(true);
            }

            private void insertInDb(String equip, String equipsr, String make, String date1, String cap, String loc) {
                userForms = new UserForms();
                userForms.setEquip(equip);
                userForms.setEquipsr(equipsr);
                userForms.setMake(make);
                userForms.setDateMfg(date1);
                userForms.setCap(cap);
                userForms.setLoc(loc);

                Gson gson = new Gson();
                val = gson.toJson(userForms);
                sharedPreferenceUtils = SharedPreferenceUtils.getInstance();
                sharedPreferenceUtils.setString(getActivity(), "key1", val);



               /* DbQueries dbqueries = new DbQueries(getActivity());
                id = dbqueries.addUserAccout(userAccount);

                SharedPreferenceUtils sharedPreferenceUtils=SharedPreferenceUtils.getInstance();
                sharedPreferenceUtils.setLong(this,"key",id);
                dbqueries.addUserForms(userForms);
                Snackbar.make(next, "Entries are successfully added", Snackbar.LENGTH_LONG).show();*/

            }

        });
    }
}






