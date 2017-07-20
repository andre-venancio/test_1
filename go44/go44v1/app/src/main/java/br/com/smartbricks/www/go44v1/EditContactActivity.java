package br.com.smartbricks.www.go44v1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import entities.Contact;
import database.*;
import android.view.View;
import android.widget.*;
import android.content.*;

public class EditContactActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonBack;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        final Contact contact = (Contact) intent.getSerializableExtra("contact");

        this.editTextName = (EditText) findViewById(R.id.editTextName);
        this.editTextName.setText(contact.getName());

        this.editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        this.editTextPhone.setText(contact.getPhone());

        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditContactActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonSave = (Button) findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactDB contactDB = new ContactDB(getBaseContext());
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                if(contactDB.update(contact)){
                    Intent intent1 = new Intent(EditContactActivity.this, MainActivity.class);
                    startActivity(intent1);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Fail");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });

    }
}
