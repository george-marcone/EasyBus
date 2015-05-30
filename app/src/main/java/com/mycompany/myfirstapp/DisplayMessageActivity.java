package com.mycompany.myfirstapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class DisplayMessageActivity extends ActionBarActivity {

    //private static String SOAP_ACTION = "http://tempuri.org/LoadMusics";
    //private static String NAMESPACE = "http://tempuri.org/";
    //private static String METHOD_NAME = "LoadMusics";
    //private static String URL = "http://10.0.2.2/MusicOrganizerTeste/WSMusicSupplier.asmx";
    //private static String URL = "http://192.168.1.103/MusicOrganizerTeste/WSMusicSupplier.asmx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        new MyAsyncTask().execute();
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

    private class MyAsyncTask extends AsyncTask<Void,Void,List<String>>{

        boolean erro = false;
        String msgErro = "";

        private final ProgressDialog dialog = new ProgressDialog(DisplayMessageActivity.this);

        @Override
        protected void onProgressUpdate(Void... values) {

            this.dialog.setMessage("Carregando...");
            this.dialog.show();

        }

        @Override
        protected List<String> doInBackground(Void... params) {
            List<String> musics = null;

            try {
                ConexaoWS ws = new ConexaoWS();
                musics = ws.InvocarWS();

            } catch (Exception e) {
                erro = true;
                msgErro = e.getMessage();
                e.printStackTrace();
            }

            return musics;
        }

        @Override
        protected void onPostExecute(List<String> result) {

            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (erro == false) {

                List<String> musics = result;
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        DisplayMessageActivity.this, android.R.layout.simple_list_item_1,
                        musics);

                ListView list=(ListView)findViewById(R.id.lvMusics);
                list.setAdapter(adapter);

            } else {

                Toast.makeText(DisplayMessageActivity.this,
                        "Ocorreu um erro ao acessar o Web Service." + msgErro,
                        Toast.LENGTH_LONG).show();

            }

        }

    }
}
