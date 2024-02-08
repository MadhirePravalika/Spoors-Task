package com.codeinfinity.spoorstask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addBtn;

    private RecyclerView mRecyclerView;
    private EmployeeAdapter mEmployeeAdapter;
    private List<Employee> mEmployeeList;

    private EmployeeDatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.addEmployeeBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddEmployeeActivity.class));
            }
        });

        mRecyclerView = findViewById(R.id.recyclerViewDetails);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEmployeeList = new ArrayList<>();
        mDatabaseHelper = new EmployeeDatabaseHelper(this);

        // Fetch data from database
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        List<Employee> employeesList = mDatabaseHelper.getAllEmployees();
        mEmployeeList.addAll(employeesList);

        // Initialize and set adapter
        mEmployeeAdapter = new EmployeeAdapter(this, mEmployeeList);
        mRecyclerView.setAdapter(mEmployeeAdapter);
    }
}