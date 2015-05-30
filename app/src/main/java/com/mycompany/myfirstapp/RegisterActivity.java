package com.mycompany.myfirstapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class RegisterActivity extends ActionBarActivity {

    public static final String EXTRA_VEM_NUMBER = "com.mycompany.myfirstapp.VEM_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void confirm(View view) {
        EditText txt = (EditText)findViewById(R.id.txtVemNumber);

        if (txt.getText().toString().trim().equals("")){
            txt.setError("Informe o número do VEM.");
        }
        else{
            Intent intent = new Intent(this, ConfirmInformationActivity.class);
            String vem = txt.getText().toString();
            intent.putExtra(EXTRA_VEM_NUMBER, vem);
            startActivity(intent);
        }
    }
}
