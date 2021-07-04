package com.example.kltn.hospitalappointy.Fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kltn.hospitalappointy.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class Fragment_Date extends Fragment {

    private TextView mSelectDate, mSelectedDate, mAvailableDate;
    private RecyclerView recyclerView;

    private int count = 0;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    private String currentUserID = "";

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public Fragment_Date(){
        //Required Empty public constructor otherwise app will crash
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_date,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.date_doctorList_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));


        mSelectDate = (TextView) rootView.findViewById(R.id.date_select_date);
        mSelectedDate = (TextView) rootView.findViewById(R.id.date_selected_date);
        mAvailableDate = (TextView) rootView.findViewById(R.id.date_avilableDate);
        mSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(rootView.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        String date = dayOfMonth +"-"+ (month+1) +"-"+ year;
                        Toast.makeText(rootView.getContext(), date , Toast.LENGTH_SHORT).show();
                        mSelectedDate.setVisibility(View.VISIBLE);
                        mSelectDate.setText(date);
                        mAvailableDate.setVisibility(View.VISIBLE);

                        //showDoctorList(date);

                    }
                },day,month,year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });

        return rootView;
    }
    
    

    public class DoctorLisetVH extends RecyclerView.ViewHolder {

        View mView;
       public DoctorLisetVH(View itemView) {
           super(itemView);

           mView = itemView;
       }

       public void setDoctorName(String doctorName) {
           TextView userName = (TextView) mView.findViewById(R.id.name_id_single_user);
           userName.setText(doctorName);
       }

        public void setSpecialization(String specialization) {
            TextView userName = (TextView) mView.findViewById(R.id.special_id_single_user);
            userName.setText(specialization);
        }
    }

}
