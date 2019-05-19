package com.topicos.proyecto;

import android.app.Dialog;
//import android.app.DialogFragment;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class PickersActivity2 extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        final Calendar calendar = Calendar.getInstance();


        //Calendar c = Calendar.getInstance();
        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);


        return new TimePickerDialog(getActivity(), this, hh, mm,
                DateFormat.is24HourFormat(getActivity()));

    }

    public void populateSetTime(int horas, int minutos) {
        TextView dob= (TextView)getActivity(). findViewById(R.id.RHora);
        dob.setText(horas+":"+minutos);
    }

    @Override
    public void onTimeSet(TimePicker view, int hh, int mm) {
        populateSetTime(hh,mm);
    }
}