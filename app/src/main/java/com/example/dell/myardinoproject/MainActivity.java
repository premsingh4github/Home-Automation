package com.example.dell.myardinoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;


public class MainActivity extends Activity {
    public Switch switch1, switch2,switch3,switch4,switch5,switch6 ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        switch4 = (Switch) findViewById(R.id.switch4);
        switch5 = (Switch) findViewById(R.id.switch5);
        switch6 = (Switch) findViewById(R.id.switch6);
        textView = (TextView) findViewById(R.id.switchStatus);



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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Show Dialog Box with Progress bar

    private static final int UDP_SERVER_PORT = 8888;
    private void runUdpClient()  {
        Log.v("start","1");
        //String udpMsg = "1:0#2:0#3:0#4:0#5:0#6:0#";
        String udpMsg = "1:1#2:1#3:1#4:1#5:1#6:1#";

        byte[] receiveData = new byte[1024];
        String modifiedSentence;
        Log.v("start","Udp");
        DatagramSocket ds = null;
        try {
            Log.v("start","Udp try");
            ds = new DatagramSocket();
            Log.v("start","Datagram");
            //InetAddress serverAddr = InetAddress.getByName("127.0.0.1");
            InetAddress serverAddr = InetAddress.getByName("192.168.0.177");
            Log.v("start","serverAddr");
            DatagramPacket dp;
            dp = new DatagramPacket(udpMsg.getBytes(), udpMsg.length(), serverAddr, UDP_SERVER_PORT);
            ds.send(dp);
            Log.v("start","dp");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            ds.receive(receivePacket);
            Log.v("start","receivePacket");
            modifiedSentence = new String(receivePacket.getData());
            Log.d("recived Data",modifiedSentence);
        } catch (SocketException e) {
            Log.v("start","Datagram Error" + e.toString());
            e.printStackTrace();
        }catch (UnknownHostException e) {
            Log.v("start","Datagram Error2" + e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.v("start","Datagram Error3" + e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.v("start","Datagram Error4" + e.toString());
            e.printStackTrace();
        } finally {

            if (ds != null) {
                ds.close();
            }
        }
    }

}
