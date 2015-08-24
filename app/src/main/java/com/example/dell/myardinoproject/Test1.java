package com.example.dell.myardinoproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Test1 extends Activity {

    private TextView textView,textView1;
    private static final int UDP_SERVER_PORT = 8888;
    public Switch switch1, switch2,switch3,switch4,switch5,switch6,switch7 ;
    public RadioGroup radioGroup;
    public RadioButton radioButton ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
       // textView = (TextView) findViewById(R.id.TextView01);

        switch1 = (Switch) findViewById(R.id.switch1);
        switch2 = (Switch) findViewById(R.id.switch2);
        switch3 = (Switch) findViewById(R.id.switch3);
        switch4 = (Switch) findViewById(R.id.switch4);
        switch5 = (Switch) findViewById(R.id.switch5);
        switch6 = (Switch) findViewById(R.id.switch6);
        switch7 = (Switch) findViewById(R.id.switch7);
        textView1 = (TextView) findViewById(R.id.switchStatus);
        radioGroup = (RadioGroup) findViewById(R.id.radioButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton1){
                    radioButton = (RadioButton) findViewById(R.id.radioButton1);
                    if(radioButton.isChecked()){
                        if(switch7.isChecked()){
                            switch1.setChecked(true);
                            switch2.setChecked(true);
                            switch3.setChecked(true);
                            switch4.setChecked(true);
                            switch5.setChecked(true);
                            switch6.setChecked(true);
                           // textView.setText("1:1#2:1#3:1#4:1#5:1#6:1#");
                            SocketController task = new SocketController();
                            task.execute("1:1#2:1#3:1#4:1#5:1#6:1#");
                        }
                        else{
                            radioGroup.clearCheck();
                        }
                    }
                    else{

                    }
                }
                if(checkedId == R.id.radioButton2){
                    radioButton = (RadioButton) findViewById(R.id.radioButton2);
                    if(radioButton.isChecked()){
                        if(switch7.isChecked()){

                            switch1.setChecked(false);
                            switch2.setChecked(false);
                            switch3.setChecked(false);
                            switch4.setChecked(false);
                            switch5.setChecked(false);
                            switch6.setChecked(false);
                           // textView.setText("1:0#2:0#3:0#4:0#5:0#6:0#");
                            SocketController task = new SocketController();
                            task.execute("1:0#2:0#3:0#4:0#5:0#6:0#");

                        }
                        else{
                            radioGroup.clearCheck();
                        }
                    }
                    else{

                    }
                }
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //textView.setText(readSwitch());
                SocketController task = new SocketController();
                task.execute(readSwitch());
            }
        });
        switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(switch7.isChecked()){

               }
                else{
                   radioGroup.clearCheck();
               }
            }
        });
    }

    private class SocketController extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            String udpMsg =  urls[0] ; //"1:1#2:1#3:1#4:1#5:1#6:1#";

            byte[] receiveData = new byte[1024];
            String modifiedSentence;
            Log.v("start", "Udp");
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
                response += modifiedSentence;
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
            return response ;
        }

        @Override
        protected void onPostExecute(String result) {
           // textView.setText(result);
            Log.v("after finish","ok");
        }
    }
    public String readSwitch(){
        return "1:" + String.valueOf((switch1.isChecked())? 1 : 0) +"#2:" +String.valueOf((switch2.isChecked())? 1 : 0) +"#3:" + String.valueOf((switch3.isChecked())? 1 : 0)+ "#4:" + String.valueOf((switch4.isChecked()) ? 1 : 0)+ "#5:" + String.valueOf((switch5.isChecked())? 1 : 0) + "#6:" + String.valueOf((switch6.isChecked())? 1 : 0) + "#";
    }
    public void onClick(View view) {
        SocketController task = new SocketController();
        task.execute(new String[] { "http://www.vogella.com" });

    }
}
