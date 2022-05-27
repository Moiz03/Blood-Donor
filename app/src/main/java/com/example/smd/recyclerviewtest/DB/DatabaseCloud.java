package com.example.smd.recyclerviewtest.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.smd.recyclerviewtest.Models.CloudObserver;
import com.example.smd.recyclerviewtest.Models.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseCloud implements DB_Interface{

    private CloudObserver cloudObserver;
    FirebaseDatabase firebaseDb = null;
    DatabaseReference dbReference = null;
    ArrayList<DonorsData> data = new ArrayList<>();
    Boolean success = false;

    public DatabaseCloud(CloudObserver cloudObserver)
    {
        if(this.cloudObserver == null)
            this.cloudObserver = cloudObserver;
        if(firebaseDb == null) {
            firebaseDb = FirebaseDatabase.getInstance();
            firebaseDb.setPersistenceEnabled(true);
            dbReference = firebaseDb.getReference().child("Donors");
        }
    }

    @Override
    public boolean addDonor(DonorsData donor) {
        if(donor.getPhone_no()!=null) {
            dbReference.child(donor.getPhone_no()).setValue(donor).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        success = true;
                    }
                }
            });
        }
        return success;
    }

    @Override
    public ArrayList<DonorsData> getDonors() {
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    data.clear();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        DonorsData donor = ds.getValue(DonorsData.class);
                        data.add(donor);
                    }
                    Log.w("fireBaseDb", "Updated values."+data.size());
                    ArrayList<DonorsData> donorsData = new ArrayList<>();
                    donorsData.addAll(data);
                    Log.w("fireBaseDb", "Data values."+donorsData.size());
                    cloudObserver.updateData(donorsData);

                }
                catch (Exception ex) {
                    Log.e("fireBaseDb", ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("fireBaseDb", "Failed to read value.", error.toException());
            }
        });
        return data;
    }
}
