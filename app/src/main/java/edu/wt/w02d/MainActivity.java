package edu.wt.w02d;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    public final String FILENAME = "settings.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateDisplay();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickDbAdd(View v){
        TextView tvname = (TextView) findViewById(R.id.name);
        TextView tvemail = (TextView) findViewById(R.id.email);
        String name = tvname.getText().toString();
        String email = tvemail.getText().toString();
        if(name.length() > 0 && email.length() > 0) {
            tvname.setText("");
            tvemail.setText("");
            DatabaseAdapter dba = new DatabaseAdapter(this);
            dba.open();
            dba.insertContact(name, email);
            dba.close();
        }
    }

    public void onClickDbUpdate(View v){
        updateDisplay();
    }

    public void updateDisplay() {
        DatabaseAdapter dba = new DatabaseAdapter(this);
        dba.open();
        String allContacts = "Kontakty:\n";
        Cursor c = dba.getAllContacts();
        if (c.moveToFirst())
        {
            do {
                allContacts += c.getString(1)+ " <" + c.getString(2) + ">\n";
            } while (c.moveToNext());
        }
        dba.close();
        TextView tv= (TextView) findViewById(R.id.dbData);
        tv.setText(allContacts);
    }

}
