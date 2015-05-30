package com.mycompany.myfirstapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ConfirmInformationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_information);

        loadInformations();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirm_information, menu);
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

    private void loadInformations(){
        String vemNumber = getIntent().getStringExtra(RegisterActivity.EXTRA_VEM_NUMBER);

        TextView lblName = (TextView)findViewById(R.id.lblUserName);
        TextView lblMother = (TextView)findViewById(R.id.lblMotherName);

        if (vemNumber.equals("123")){
            lblName.setText("Lucas Lima");
            lblMother.setText("Helena Lima");
        }
        else if (vemNumber.equals("321")){
            lblName.setText("Diego Souza");
            lblMother.setText("Julia Souza");
        }
    }

    public void cancelar(View view) {
        onBackPressed();
    }
}
