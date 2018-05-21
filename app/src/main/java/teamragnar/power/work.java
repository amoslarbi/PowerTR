package teamragnar.power;

import android.*;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class work extends AppCompatActivity {

    Chronometer tx;
    ToggleButton tbtn;
    TextView topic, num, msg, msgoff, tx3, yhy, bnum, tarr;

    int count = 0;
    double sum;
    double tarriffs;
    Thread t;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;
    ListView list;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    ArrayList<String> smslist;
    public static final String helloerror = "hellol";
    public static final String hello = "hellol";
    String timer;
    private static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 6),
                new DataPoint(4, 8),
                new DataPoint(5, 11),
                new DataPoint(6, 13)
        });
        graph.addSeries(series);




        topic = (TextView) findViewById(R.id.textView2);
        tbtn = (ToggleButton) findViewById(R.id.tbtn);
        num = (TextView) findViewById(R.id.num);
        msg = (TextView) findViewById(R.id.msg);
        msgoff = (TextView) findViewById(R.id.msgoff);
        tx3 = (TextView) findViewById(R.id.tx3);
        yhy = (TextView) findViewById(R.id.yhy);
        bnum = (TextView) findViewById(R.id.bnum);
        tarr = (TextView) findViewById(R.id.textVw117);

        tx = (Chronometer) findViewById(R.id.textView117);
        tx.setBase(SystemClock.elapsedRealtime());

        topic.setText(getIntent().getStringExtra("food"));
        num.setText(getIntent().getStringExtra("watts"));
        bnum.setText(getIntent().getStringExtra("numoo"));

        //Toast.makeText(getApplicationContext(), bnum.getText().toString(), Toast.LENGTH_SHORT).show();

        list = (ListView) findViewById(R.id.list);
        
        
        //////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////  Timer   /////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////


        t = new Thread(){
            public void run(){
                while(!isInterrupted()){

                    try {
                        Thread.sleep(1000);
                        work.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                count++;
                                tx3.setText(String.valueOf(count));
                                //String hh = tx3.getText().toString();

                                //double hello = Double.parseDouble(tx3.getText().toString());
                                double hello = Double.parseDouble(tx3.getText().toString());
                                double hellolol = Double.parseDouble(num.getText().toString());

                                //sum = hello/60 * 40;
                                //sum = hello * hellolol/1000;
                                sum = hello/3600 * hellolol;
                                //tarriffs = hellolol/1000 * 0.5;

                                yhy.setText(Double.toString(sum));
                                //tarr.setText(Double.toString(tarriffs));

                                //Toast.makeText(getApplicationContext(), "value" +name, Toast.LENGTH_SHORT).show();


                            }
                        });




                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }

        };



        //Method to start the service
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //Name of method for calling Message
            showContacts();

        } else {
            ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{android.Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);

        }



        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.soho);

        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tbtn.isChecked()) {
                    //edit.putBoolean("tbtn.isChecked()",true);
                    mp.start();

                    //bundle.putBoolean("state", tbtn.isChecked());
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.SEND_SMS);

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        MyMessage();

                    } else {
                        ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{android.Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);

                    }

                }
                else {

                    mp.start();
                    int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.SEND_SMS);

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        MyMessageoff();
                    } else {
                        ActivityCompat.requestPermissions((Activity) getApplicationContext(), new String[]{android.Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);

                    }

                }


            }
        });


    }


    public void MyMessage(){
        String myNumber = bnum.getText().toString().trim();
        String myMsg = msg.getText().toString().trim();

        //Begin check for number
        if (myNumber == null || myNumber.equals("") || myMsg == null || myMsg.equals("")){
            Toast.makeText(getApplicationContext(), "Field cant be empty", Toast.LENGTH_LONG).show();

        }else{
            if (TextUtils.isDigitsOnly(myNumber)){
                SmsManager sg = SmsManager.getDefault();
                sg.sendTextMessage(myNumber, null, myMsg, null, null);
                //Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
                tx.start();
                t.start();
                Toast.makeText(getApplicationContext(), "Appliance On", Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(getApplicationContext(), "Please Enter Interger Only", Toast.LENGTH_LONG).show();

            }

        }
    }

    public void MyMessageoff(){
        String myNumber = bnum.getText().toString().trim();
        String myMsg = msgoff.getText().toString().trim();

        //Begin check for number
        if (myNumber == null || myNumber.equals("") || myMsg == null || myMsg.equals("")){
            Toast.makeText(getApplicationContext(), "Field cant be empty", Toast.LENGTH_LONG).show();

        }else{
            if (TextUtils.isDigitsOnly(myNumber)){
                SmsManager sg = SmsManager.getDefault();
                sg.sendTextMessage(myNumber, null, myMsg, null, null);
                //Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
                tx.stop();
                t.stop();
                Toast.makeText(getApplicationContext(), "Appliance off", Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(getApplicationContext(), "Please Enter Interger Only", Toast.LENGTH_LONG).show();

            }

        }
    }

//End Check for phone Number

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permission, int [] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS:
            {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Name of method for calling Message
                    MyMessage();

                } else {
                    Toast.makeText(getApplicationContext(), "You dont have required permission to make the action", Toast.LENGTH_LONG).show();
                }
            }
        }

        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission is granted
                showContacts();

            } else {
                Toast.makeText(getApplicationContext(), "Until you grant the permission, we cannot display the names", Toast.LENGTH_LONG).show();

            }
        }

    }



    public void showContacts(){
        //Create Inbox box URI
        ArrayList<String> wattsArray;
        Uri inboxURI = Uri.parse("content://sms/inbox");
        smslist = new ArrayList<String>();
        //Get Content Resolver object, which will deal with content provider
        ContentResolver cr = getApplicationContext().getContentResolver();

        //Fetch Inbox SMS Message from Built-in content provider
        Cursor c = cr.query(inboxURI, null, null, null, null);
        while (c.moveToNext()){
            String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
            String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();

            //String Body2 = topic.getText().toString();
            smslist.add("Number: " +Number + "\n" + "Body: " + Body);
//            tx.start();

            //Toast.makeText(getApplicationContext(), Number, Toast.LENGTH_SHORT).show();

            if (smslist.contains(bnum.getText().toString())) {
                //smslist.add("Number: " + Number + "\n" + "Body: " + Body);
                //tx.start();
                //Toast.makeText(getApplicationContext(), "Bambi", Toast.LENGTH_SHORT).show();
                SweetAlertDialog su = new SweetAlertDialog(work.this, SweetAlertDialog.SUCCESS_TYPE);
                    su.setTitleText("Board Number Activated Successfully");
                    su.show();

            }

//           else {
//                if (Number.contains(bnum.getText().toString()) || Body.contains("Sistym Acildi")) {
//                    smslist.add("Number: " + Number + "\n" + "Body: " + Body);
//
//                    SweetAlertDialog su = new SweetAlertDialog(work.this, SweetAlertDialog.SUCCESS_TYPE);
//                    su.setTitleText("Board Number Activated Successfully");
//                    su.show();
//
//                }

//                else {
//
//                }
         //   }
//            else if (Number.contains(bnum.getText().toString()) || Body.contains("ISIK acildi")){
//            //else if (Number.contains("+233579631609") || Body.contains("ISIK acildi")){
//                smslist.add("Number: " + Number + "\n" + "Body: " + Body);
//                //tx.stop();
//
//            }


        }
        c.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, smslist);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


}
//was doing two things at the same time with the tx...have to change it....chronometer and thread