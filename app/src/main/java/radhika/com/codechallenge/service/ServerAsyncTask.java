package radhika.com.codechallenge.service;

import android.os.AsyncTask;
import android.util.Log;

import junit.framework.Test;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;

import radhika.com.codechallenge.dto.Comment;

/**
 * Created by User on 12/1/2016.
 */

public class ServerAsyncTask extends AsyncTask<Comment, Void, String> {
    //interface to return to the activity
    private WeakReference<ISocketActivity> weakReference =null;
    private String result="fail";

    String SOAP_ACTION = "";
    String METHOD_NAME = "sendTest";
    String NAMESPACE = "";
    String URL = "";

    public ServerAsyncTask(ISocketActivity iSocketActivity)
    {
        weakReference = new WeakReference<ISocketActivity>(iSocketActivity);
    }

    //Background task which serve for the client
    @Override
    protected String doInBackground(Comment... params) {
        //Get the accepted socket object
        try {

            Comment comment = params[0];

            PropertyInfo property = new PropertyInfo();
            property.setName("test");
            property.setType(Test.class);
            property.setValue(comment);


            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty(property);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            HttpTransportSE Transport = new HttpTransportSE(URL);
            Transport.call(SOAP_ACTION, envelope);
            result = "success";

        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ISocketActivity iSocketActivity = weakReference.get();
        if (iSocketActivity != null)
            iSocketActivity.onSocketMessage(s);
    }



}
