package com.mycompany.myfirstapp;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafa on 02/05/2015.
 */

public class ConexaoWS {
    private static String SOAP_ACTION = "http://tempuri.org/LoadMusics";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME = "LoadMusics";
    //private static String URL = "http://10.0.2.2/MusicOrganizerTeste/WSMusicSupplier.asmx";
    private static String URL = "http://192.168.1.103/MusicOrganizerTeste/WSMusicSupplier.asmx";

    public List<String> InvocarWS() throws Exception{
        List<String> musics = new ArrayList<String>();

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL);
        transporte.call(SOAP_ACTION, envelope);

        //SoapObject response = (SoapObject)envelope.getResponse();
        SoapObject response = (SoapObject) envelope.bodyIn;

        for (int i=0; i < response.getPropertyCount();i++){
            SoapObject music = (SoapObject)response.getProperty(i);

            for (int j = 0; j < music.getPropertyCount() ; j++) {
                musics.add(music.getProperty(j).toString());
            }

        }

        return musics;
    }
}
