package com.example.smd.recyclerviewtest.UI;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.MenuInflater;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smd.recyclerviewtest.Models.*;
import com.example.smd.recyclerviewtest.BL.*;

import com.example.smd.recyclerviewtest.Models.CloudObserver;
import com.example.smd.recyclerviewtest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Popup.PopupListener {

    private ArrayList<DonorsData> data = new ArrayList<>();
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private RecycleViewAdapter mAdapter;
    private static BL_Interface bl_donor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //data = new DatabaseHelper(this).getDonors();
        //bl_donor = Instance.getInstance(this);
        bl_donor = Instance.getInstance(new CloudObserver() {
            @Override
            public void updateData(ArrayList<DonorsData> donorsData) {
                mAdapter.dataRefresher(donorsData);
                Log.w("Main Act","Updated "+donorsData.size());
            }
        });
        data = bl_donor.getDonors();
        recyclerView = findViewById(R.id.my_recycler_view);
        fab = findViewById(R.id.my_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildFAB();
            }
        });
        buildRecycleView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private void buildRecycleView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecycleViewAdapter(this, data);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    private void buildFAB() {

        Popup p = new Popup(this);
        p.show(getSupportFragmentManager(), null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            mAdapter.wew(data.getStringExtra("message"));

            if(data.getStringExtra("message").equals("YES"))
            {
                mAdapter.dataRefresher(bl_donor.getDonors());
            }
        }
    }

    @Override
    public void applyTexts(String str) {
        if(str.equals("YES")) {
            mAdapter.dataRefresher(bl_donor.getDonors());
        }
    }
}