package com.example.smd.recyclerviewtest.BL;

import com.example.smd.recyclerviewtest.Models.*;

import java.util.ArrayList;

public interface BL_Interface {
    ArrayList<DonorsData> getDonors();
    boolean addDonor(DonorsData donor);
}
