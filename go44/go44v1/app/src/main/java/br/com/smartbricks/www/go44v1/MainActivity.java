package br.com.smartbricks.www.go44v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import database.ContactDB;

//-----------------------------------ADD SQL-------------------------------------
import adapters.ContactListAdapter;
import entities.Contact;
import database.*;
import android.widget.*;
import android.content.*;
//-----------------------------------ADD SQL-------------------------------------

public class MainActivity extends AppCompatActivity {

     //-----------------------------------ADD SQL-------------------------------------
        private Button buttonAdd;
        private ListView listViewContacts;
     //-----------------------------------ADD SQL-------------------------------------

        // Used to load the 'native-lib' library on application startup.
        static {
            System.loadLibrary("native-lib");
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            this.buttonAdd = (Button) findViewById(R.id.buttonAdd);

            this.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent (MainActivity.this, AddContactActivity.class);//cria a Intent, ou ação atrelada a tela de add contato
                    startActivity(intent1);                                                   // e envia o usuário para a tela de add novo contato;
                }
            });

            final ContactDB contactDB = new ContactDB(this);
            this.listViewContacts = (ListView) findViewById(R.id.listViewContacts);
            this.listViewContacts.setAdapter(new ContactListAdapter(this, contactDB.findAll()));
            //toda vez que um novo contato for criado, ao iniciar essa tela, ele procura todos os contatos
            //para serem mostrados na tela, no listViewContacts;
            this.listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    Contact contact = contactDB.findAll().get(i);
                    Intent intent1 = new Intent(MainActivity.this, ContactDetailActivity.class); //Ver Contact em entities
                    intent1.putExtra("contact", contact);
                    startActivity(intent1);
                }
            });
        }

        /**
         * A native method that is implemented by the 'native-lib' native library,
         * which is packaged with this application.
         */



    public native String stringFromJNI();
}
