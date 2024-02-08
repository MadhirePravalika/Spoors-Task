package com.codeinfinity.spoorstask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AddEmployeeActivity extends AppCompatActivity {

    EditText name, address;
    RadioButton male, female;

    RadioGroup radioGroup;

    Switch isFresher;

    Spinner department;

    Button save, discard;

    private String[] departments = {"Select department", "Software Development", "Quality Assurance", "Project Management", "Technical Support", "DevOps", "Sales"};

    private EmployeeDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);


        name = findViewById(R.id.nameEdittext);
        address = findViewById(R.id.addressEdittext);
        department = findViewById(R.id.departmentSpinner);
        radioGroup = findViewById(R.id.radioGroupGender);
        male = findViewById(R.id.maleRadioBtn);
        female = findViewById(R.id.femaleRadioBtn);
        isFresher = findViewById(R.id.isFresherSwitch);
        save = findViewById(R.id.saveDetailsBtn);
        discard = findViewById(R.id.discardDetailsBtn);

        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiscardDialog();
            }
        });

        databaseHelper = new EmployeeDatabaseHelper(this);

        ArrayAdapter<String> departmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        department.setAdapter(departmentAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEmployee();
            }
        });
    }

    private void saveEmployee() {
        String nameString = name.getText().toString().trim();
        String addressString = address.getText().toString().trim();
        String departmentString = department.getSelectedItem().toString();

        boolean isFresherValue = isFresher.isChecked();

        // Validation
        if (nameString.isEmpty()) {
            name.setError("Name cannot be empty!");
            return;
        } else if (addressString.isEmpty()) {
            address.setError("Address cannot be empty!");
            return;
        }else if (departmentString.equals("Select department")){
            Toast.makeText(this, "Select a department", Toast.LENGTH_SHORT).show();
            return;
        }
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            // No gender is selected, show an error message
            Toast.makeText(AddEmployeeActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return; // Exit the method without saving
        }

        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        String gender = selectedRadioButton.getText().toString();


        // Saving to database
        long result = databaseHelper.insertEmployee(nameString, addressString, departmentString, gender, isFresherValue);
        if (result != -1) {
            Toast.makeText(this, "Employee details saved", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddEmployeeActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Failed to save employee details", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDiscardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Discard details?");
        builder.setMessage("Are you sure you want to discard?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AddEmployeeActivity.this, "Details not saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddEmployeeActivity.this, MainActivity.class));
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}