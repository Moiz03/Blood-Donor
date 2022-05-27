package com.example.smd.recyclerviewtest.UI;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.smd.recyclerviewtest.BL.*;
import com.example.smd.recyclerviewtest.Models.CloudObserver;
import com.example.smd.recyclerviewtest.Models.*;
import com.example.smd.recyclerviewtest.R;

import java.util.ArrayList;

public class Popup extends AppCompatDialogFragment  {

    private AlertDialog.Builder dialogBuilder;
    private Context context;
    private AlertDialog dialog;
    private EditText name,phone_no;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch availability;
    private Spinner city,blood_group,status;
    private final String checked = "Available";
    private final String unchecked = "Not Available";
    private String message;
    private PopupListener Listener;
    private static BL_Interface bl_donor;
    private DonorsData donorsData = new DonorsData();

    Popup()
    {

    }
    Popup(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.builder));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup,null);


        name = view.findViewById(R.id.p_name);
        city = view.findViewById(R.id.p_city);
        status = view.findViewById(R.id.p_status);
        availability = view.findViewById(R.id.p_availability);
        blood_group= view.findViewById(R.id.p_blood_group);
        phone_no = view.findViewById(R.id.p_phone_no);

        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        donorsData.setName(name.getText().toString());
                        donorsData.setPhone_no(phone_no.getText().toString());
                        donorsData.setCity(city.getSelectedItem().toString());
                        donorsData.setStatus(status.getSelectedItem().toString());
                        donorsData.setBlood_group(blood_group.getSelectedItem().toString());

                        if(availability.isChecked())
                        {
                            donorsData.setAvailability(checked);

                        }
                        else
                        {
                            donorsData.setAvailability(unchecked);
                        }
                        message = "NO";
                        if(!donorsData.getAvailability().equals("") && !donorsData.getBlood_group().equals("") && !donorsData.getName().equals("")
                        && !donorsData.getCity().equals("") && !donorsData.getStatus().equals("") && !donorsData.getPhone_no().equals("")) {
                            System.out.println(donorsData.getName() + " " + donorsData.getCity() + " " + donorsData.getStatus() + " "
                                    + donorsData.getAvailability() + " " + donorsData.getBlood_group() + " " + donorsData.getPhone_no());

                            bl_donor = Instance.getInstance(new CloudObserver() {
                                @Override
                                public void updateData(ArrayList<DonorsData> donorsData) {
                                }
                            });
                            bl_donor.addDonor(donorsData);
                            message = "YES";
                        }

                        Listener.applyTexts(message);
                    }
                });

        ArrayAdapter<CharSequence> adapter_city = ArrayAdapter.createFromResource(this.context,
                R.array.ar_city,R.layout.selected);
        adapter_city.setDropDownViewResource(R.layout.dropdraw);
        city.setOnItemSelectedListener(new CitySelectionListener());
        city.setAdapter(adapter_city);

        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(this.context,
                R.array.ar_blood,R.layout.selected);
        adapter_blood.setDropDownViewResource(R.layout.dropdraw);
        blood_group.setOnItemSelectedListener(new BloodSelectionListener());
        blood_group.setAdapter(adapter_blood);

        ArrayAdapter<CharSequence> adapter_status = ArrayAdapter.createFromResource(this.context,
                R.array.ar_status,R.layout.selected);
        adapter_blood.setDropDownViewResource(R.layout.dropdraw);
        status.setOnItemSelectedListener(new StatusSelectionListener());
        status.setAdapter(adapter_status);
        return builder.create();
    }

    class CitySelectionListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position > 0)
            {
                donorsData.setCity(parent.getItemAtPosition(position).toString());
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class BloodSelectionListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position > 0)
            {
                donorsData.setBlood_group(parent.getItemAtPosition(position).toString());
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    class StatusSelectionListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if(position > 0)
            {
                donorsData.setStatus(parent.getItemAtPosition(position).toString());
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            this.Listener = (PopupListener)  context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }
    public interface PopupListener {
        void applyTexts(String str);
    }
}
