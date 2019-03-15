package com.elitewaresolutions.krte.Fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.elitewaresolutions.krte.R;
import com.elitewaresolutions.krte.database.DbQueries;
import com.elitewaresolutions.krte.model.UserAccount;

import java.util.Locale;


/**
 * Created by Ravi on 29/07/15.
 */
public class FormsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

   private Calendar myCalendar;
   private EditText fname;
   private EditText lname;
   private EditText email;
   private Spinner civ;
   private String item;
    private static final String[]paths = {"Mr.", "Miss", "Mrs"};
    EditText birth;
    EditText ph;
    CheckBox c1;
    CheckBox c2;
    CheckBox c3;
    String check="";
    private RadioGroup radioGroup;
    private RadioButton coun;
    Button button3;
    Button submit;

    public FormsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_forms, container, false);

        myCalendar = Calendar.getInstance();

        fname= (EditText)rootView.findViewById(R.id.fname1);
        lname= (EditText)rootView.findViewById(R.id.lname1);
        email= (EditText)rootView.findViewById(R.id.email1);

        civ= (Spinner) rootView.findViewById(R.id.Spinner);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        civ.setAdapter(adapter);
        civ.setOnItemSelectedListener(this);

        birth= (EditText)rootView.findViewById(R.id.set);
        ph= (EditText)rootView.findViewById(R.id.ph);
        submit= (Button)rootView.findViewById(R.id.submit);
        addListenerOnButton(rootView);

        c1= (CheckBox)rootView.findViewById(R.id.peinture);
        c2= (CheckBox)rootView.findViewById(R.id.litterature);
        c3= (CheckBox)rootView.findViewById(R.id.musique);

        addListenerOnButton1(rootView);

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

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

         item = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    public void setItemOnCheckBox(){

        if(c1.isChecked()){

            check = c1.getText().toString();
        }
        if(c2.isChecked()){
            check = check + "," + c2.getText().toString();
        }
        if(c3.isChecked()){
            check = check + "," +  c3.getText().toString();
        }
    }



    public void addListenerOnButton(View rootView) {
        button3=(Button)rootView.findViewById(R.id.setd);

        button3.setOnClickListener(new View.OnClickListener() {
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

        birth.setText(sdf.format(myCalendar.getTime()));

    }
    private void addListenerOnButton1(final View rootView) {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                radioGroup= (RadioGroup)rootView.findViewById(R.id.radiog);
                int selectedId=radioGroup.getCheckedRadioButtonId();
                coun = (RadioButton)rootView.findViewById(selectedId);
                setItemOnCheckBox();
                insertInDb(item ,birth.getText().toString().trim(),ph.getText().toString().trim(),coun.getText().toString(),check);
                civ.clearDisappearingChildren();
                birth.setText("");
                ph.setText("");
            }

        });
    }
    private void insertInDb(String item, String birth, String ph, String coun, String check) {
        UserAccount userAccount=new UserAccount();
        userAccount.setCiv(item);
        userAccount.setBirth(birth);
        userAccount.setPh(ph);
        userAccount.setCoun(coun);
        userAccount.setHob(check);

        DbQueries dbqueries=new DbQueries(getActivity());
        dbqueries.addUserAccout1(userAccount);
        Snackbar.make(submit, "Entries are successfully added", Snackbar.LENGTH_LONG).show();
    }
}

