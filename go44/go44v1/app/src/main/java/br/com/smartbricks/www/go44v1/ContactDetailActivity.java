package br.com.smartbricks.www.go44v1;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import entities.Contact;
import database.*;
import android.view.View;
import android.widget.*;
import android.content.*;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewPhone;
    private Button buttonBack;
    private Button buttonEdit;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        Intent intent = getIntent();
        final Contact contact = (Contact) intent.getSerializableExtra("contact");
        this.textViewName = (TextView) findViewById(R.id.textViewName);
        this.textViewName.setText(contact.getName());
        this.textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        this.textViewPhone.setText(contact.getPhone());

        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonDelete = (Button) findViewById(R.id.buttonDelete);
        this.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(false);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ContactDB contactDB = new ContactDB(getBaseContext());
                                if(contactDB.delete(contact.getId())){
                                    Intent intent1 = new Intent(ContactDetailActivity.this, MainActivity.class);
                                    startActivity(intent1);
                                } else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                                    builder1.setCancelable(false);
                                    builder1.setMessage("Fail");
                                    builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                                    builder1.create().show();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        builder.create().show();
            }
        });

        this.buttonEdit = (Button) findViewById(R.id.buttonEdit);
        this.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ContactDetailActivity.this,EditContactActivity.class);
                intent1.putExtra("contact", contact);
                startActivity(intent1);
            }
        });
    }
}
