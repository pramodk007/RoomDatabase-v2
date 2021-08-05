package com.androiddev.roomdatabase_v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.androiddev.roomdatabase_v2.Constants.Constants;
import com.androiddev.roomdatabase_v2.database.AppDatabase;
import com.androiddev.roomdatabase_v2.database.AppExecutors;
import com.androiddev.roomdatabase_v2.model.Person;

public class EditActivity extends AppCompatActivity {
    EditText name,email,pinCode,city,phoneNumber;
    Button button;
    int mPersonId;
    Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        initViews();
        mDb = AppDatabase.getInstance(getApplicationContext());
        intent = getIntent();
        if(intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)){
            button.setText("update");
        }
        mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id,-1);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Person person = mDb.personDao().loadPersonById(mPersonId);
                populateUI(person);
            }
        });


    }

    private void populateUI(Person person) {
        if(person == null){
            return;
        }
        name.setText(person.getName());
        email.setText(person.getEmail());
        pinCode.setText(person.getPinCode());
        city.setText(person.getCity());
        phoneNumber.setText(person.getNumber());
    }

    private void initViews() {
        name = findViewById(R.id.edit_name);
        email = findViewById(R.id.edit_email);
        pinCode = findViewById(R.id.edit_pincode);
        city = findViewById(R.id.edit_city);
        phoneNumber = findViewById(R.id.edit_number);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onSaveButtonClicked();
            }
        });


    }

    private void onSaveButtonClicked() {

        final Person person = new Person(
                name.getText().toString(),
                email.getText().toString(),
                phoneNumber.getText().toString(),
                pinCode.getText().toString(),
                city.getText().toString()
                );
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(!intent.hasExtra(Constants.UPDATE_Person_Id)){
                    mDb.personDao().insertPerson(person);
                }else{
                    person.setId(mPersonId);
                    mDb.personDao().updatePerson(person);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}