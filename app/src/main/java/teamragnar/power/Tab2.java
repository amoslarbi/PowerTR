package teamragnar.power;

import android.*;
import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

/**
 * Created by kh$y on 5/5/2018.
 */

public class Tab2 extends Fragment {

    ListView list;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    ArrayList<String> smslist;
    Button tbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab2, container, false);

        list = (ListView) view.findViewById(R.id.list);
        tbtn = (Button) view.findViewById(R.id.tbtn);

        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //Method to start the service
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //Name of method for calling Message
            showContacts();

        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);

        }


            }
        });
        return view;

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int [] grantResults){
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission is granted
                showContacts();

            } else {
                Toast.makeText(getActivity(), "Until you grant the permission, we cannot display the names", Toast.LENGTH_LONG).show();

            }
        }
    }

    public void showContacts(){
        //Create Inbox box URI
        Uri inboxURI = Uri.parse("content://sms/inbox");
        smslist = new ArrayList<String>();
        //Get Content Resolver object, which will deal with content provider
        ContentResolver cr = getActivity().getContentResolver();

        //Fetch Inbox SMS Message from Built-in content provider
        Cursor c = cr.query(inboxURI, null, null, null, null);
        while (c.moveToNext()){
            String Number = c.getString(c.getColumnIndexOrThrow("address")).toString();
            String Body = c.getString(c.getColumnIndexOrThrow("body")).toString();

            smslist.add("Number: " + Number + "\n" + "Body: " + Body);

//            if (Number == "Number: +233246128810") {
//                smslist.add("Number: " + Number + "\n" + "Body: " + Body);
//            }else{
//                Toast.makeText(getActivity(), "kpa to kpa sekerenken", Toast.LENGTH_SHORT).show();
//
//            }


        }
        c.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, smslist);
        list.setAdapter(adapter);

    }



}
