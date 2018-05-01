package com.example.syrix.reminder;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> items;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        items = new ArrayList<String >();
        items.add("Patch griefs with proverbs.\n");
        items.add("Go to a movie tonight.  Darkness becomes you.\n");
        items.add("Lord, what fools these mortals be!\n");
        items.add("Don't read any sky-writing for the next two weeks.\n");
        items.add("Don't read any sky-writing for the next two weeks.\n");
        items.add("You will remember something that you should not have forgotten.\n");


        populateListView();

    }

    private void populateListView()
    {



        adapter = new ArrayAdapter<String>
        (
                this,
                R.layout.item,
                items
        );

        list = (ListView) findViewById(R.id.list_view);
        list.setAdapter(adapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_reminder)
        {

            final EditText taskEditText = new EditText(this);
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("Add new Reminder")
                    .setMessage("What do you want to add?")
                    .setView(taskEditText)
                    .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String reminder = String.valueOf(taskEditText.getText());

                            adapter.add(reminder);
                            adapter.notifyDataSetChanged();

                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .create();
            dialog.show();


            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    public void deleteItem(View view)
    {

        View parent = (View)view.getParent();
        TextView textView = (TextView)parent.findViewById(R.id.text_view);
        final String text = String.valueOf(textView.getText());

        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Options")
                .setMessage("Edit or remove")
                .setView(taskEditText)
                .setPositiveButton("Just Remove", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        int indx = items.indexOf(text);



                        if(indx != -1)
                        {
                            items.remove(indx);
                            populateListView();
                        }


                    }

                })
                .setNegativeButton("Edit", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {


                        String input_text = String.valueOf(taskEditText.getText());
                        int indx = items.indexOf(text);



                        if(indx != -1)
                        {
                            items.set(indx, input_text);
                            adapter.notifyDataSetChanged();
                        }


                    }

                })
                .create();
        dialog.show();





    }
}
