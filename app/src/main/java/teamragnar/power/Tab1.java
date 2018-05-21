package teamragnar.power;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static teamragnar.power.R.id.container;

/**
 * Created by kh$y on 5/5/2018.
 */

public class Tab1 extends android.support.v4.app.Fragment {

    ToggleButton tbtn;
    TextView num, msg, msgoff;

    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab1, container, false);

        tbtn = (ToggleButton) view.findViewById(R.id.tbtn);
        num = (TextView) view.findViewById(R.id.num);
        msg = (TextView) view.findViewById(R.id.msg);
        msgoff = (TextView) view.findViewById(R.id.msgoff);

        final MediaPlayer mp = MediaPlayer.create(getActivity(), R.raw.soho);
        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbtn.isChecked()) {

                    mp.start();
                    int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.SEND_SMS);

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        MyMessage();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);

                    }

                }
                else {

                    mp.start();
                    int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.SEND_SMS);

                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        MyMessageoff();
                    } else {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);

                    }

                }


            }
        });


        return view;


    }


    public void MyMessage(){
        String myNumber = num.getText().toString().trim();
        String myMsg = msg.getText().toString().trim();

        //Begin check for number
        if (myNumber == null || myNumber.equals("") || myMsg == null || myMsg.equals("")){
            Toast.makeText(getActivity(), "Field cant be empty", Toast.LENGTH_LONG).show();

        }else{
            if (TextUtils.isDigitsOnly(myNumber)){
                SmsManager sg = SmsManager.getDefault();
                sg.sendTextMessage(myNumber, null, myMsg, null, null);
                //Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "Socket turned On", Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(getActivity(), "Please Enter Interger Only", Toast.LENGTH_LONG).show();

            }

        }
    }

    public void MyMessageoff(){
        String myNumber = num.getText().toString().trim();
        String myMsg = msgoff.getText().toString().trim();

        //Begin check for number
        if (myNumber == null || myNumber.equals("") || myMsg == null || myMsg.equals("")){
            Toast.makeText(getActivity(), "Field cant be empty", Toast.LENGTH_LONG).show();

        }else{
            if (TextUtils.isDigitsOnly(myNumber)){
                SmsManager sg = SmsManager.getDefault();
                sg.sendTextMessage(myNumber, null, myMsg, null, null);
                //Toast.makeText(getActivity(), "Message sent", Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "Socket turned off", Toast.LENGTH_LONG).show();


            }else{
                Toast.makeText(getActivity(), "Please Enter Interger Only", Toast.LENGTH_LONG).show();

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
                    Toast.makeText(getActivity(), "You dont have required permission to make the action", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    }

