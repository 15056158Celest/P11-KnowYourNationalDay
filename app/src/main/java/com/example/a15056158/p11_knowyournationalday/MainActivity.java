package com.example.a15056158.p11_knowyournationalday;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> al;
    ArrayAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv = (ListView) this.findViewById(R.id.lv);


        al = new ArrayList<String>();
        al.add("Singapore National Day on 9 August");
        al.add("Singapore is 52 years old");
        al.add("Theme is #OneNationTogether");






        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Tally against the respective action item clicked
        //  and implement the appropriate action
        if (item.getItemId() == R.id.sendToFriend) {
            String [] list = new String[] { "Email", "SMS" };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select the way to enrich your friend")
                    // Set the list of items easily by just supplying an
                    //  array of the items
                    .setItems(list, new DialogInterface.OnClickListener() {
                        // The parameter "which" is the item index
                        // clicked, starting from 0
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {
                                Intent email = new Intent(Intent.ACTION_SEND);
                                // Put essentials like email address, subject & body text
                                email.putExtra(Intent.EXTRA_EMAIL,
                                        new String[]{"jason_lim@rp.edu.sg"});
                                email.putExtra(Intent.EXTRA_SUBJECT,
                                        "Test Email from C347");
                                email.putExtra(Intent.EXTRA_TEXT,
                                        "Text");
                                // This MIME type indicates email
                                email.setType("message/rfc822");
                                // createChooser shows user a list of app that can handle
                                // this MIME type, which is, email
                                startActivity(Intent.createChooser(email,
                                        "Choose an Email client :"));
                                Toast.makeText(MainActivity.this, "You selected Email",
                                        Toast.LENGTH_LONG).show();
                            } else if (which == 1) {

                                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                                smsIntent.setType("vnd.android-dir/mms-sms");
                                smsIntent.putExtra("address","22222222");
                                smsIntent.putExtra("sms_body","your desired message");
                                startActivity(smsIntent);

                                Toast.makeText(MainActivity.this, "You selected SMS",
                                        Toast.LENGTH_LONG).show();

                            }
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        } else if (item.getItemId() == R.id.quiz) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Quiz?")
                    // Set text for the positive button and the corresponding
                    //  OnClickListener when it is clicked
                    .setPositiveButton("Not Really", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this, "You selected Not Really",
                                    Toast.LENGTH_LONG).show();
                        }
                    })
                    // Set text for the negative button and the corresponding
                    //  OnClickListener when it is clicked
                    .setNegativeButton("Quiz", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            Toast.makeText(MainActivity.this, "You selected Quit",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (item.getItemId() == R.id.login) {
            LayoutInflater inflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LinearLayout passPhrase =
                    (LinearLayout) inflater.inflate(R.layout.login, null);
            final EditText etPassphrase = (EditText) passPhrase
                    .findViewById(R.id.editTextPassPhrase);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Please Login")
                    .setView(passPhrase)
                    .setNegativeButton("NO ACCESS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "Login Fail" +
                                    etPassphrase.getText().toString(), Toast.LENGTH_LONG).show();
                        }
                    })

                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            if(etPassphrase.getText().toString().equals("738964")) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this, "Login Successfully" , Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity.this, "Login Invalid" , Toast.LENGTH_LONG).show();
                                onStart();;
                            }

                        }




                    });




            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }

        else if (item.getItemId() == R.id.testYourself) {
        } else if (item.getItemId() == R.id.quit) {
            finish();
        }



        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
