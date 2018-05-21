package teamragnar.power;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.text.Layout;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    ListView listView;
    SearchView search;
    FloatingActionButton aboutback;
    EditText editText, editText1, watts;
    ArrayList<String> itemlist;
    ArrayList<String> itemlist1;
    ArrayList<String> itemlist2;
    MyAdapter adapter;

    String lolo,lolos,loloss;

    private TextView names, wtf, hoover, larry;
    Uri imageUri;
    ImageView Uimmg;
    public static final String hello = "hellol";
    public static final String qhello = "hellolol";
    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener nAuthListener;

    AlertDialog.Builder sett;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nAuth = FirebaseAuth.getInstance();
        nAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){
                    startActivity(new Intent(Home.this, MainActivity.class));
                }
            }
        };


        listView = (ListView) findViewById(R.id.listview);
        itemlist = new ArrayList<>();
        itemlist1 = new ArrayList<>();
        itemlist2 = new ArrayList<>();

        //adapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1, itemlist);
        adapter = new MyAdapter(getApplicationContext(), itemlist, itemlist1, itemlist2);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(this);

        aboutback  = (FloatingActionButton) findViewById(R.id.forward);
        aboutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sett = new AlertDialog.Builder(Home.this);
                final View mview = getLayoutInflater().inflate(R.layout.addapp,null);
                 editText = (EditText) mview.findViewById(R.id.editText);
                 watts = (EditText) mview.findViewById(R.id.num);
                 editText1 = (EditText) mview.findViewById(R.id.watts);

                FloatingActionButton sposted = (FloatingActionButton) mview.findViewById(R.id.button3);
                sett.setView(mview);
                final AlertDialog dialog = sett.create();

                sposted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String str3 = editText.getText().toString();
                        String send = editText1.getText().toString().trim();
                        String send1 = editText1.getText().toString();
                        String send3 = watts.getText().toString();

                        if (TextUtils.isEmpty(str3)) {
                            editText.setError("Field cant be Empty");
                            editText.requestFocus();
                            return;
                        }

                        if (TextUtils.isEmpty(send1)) {
                            editText1.setError("Field cant be Empty");
                            editText1.requestFocus();
                            return;
                        }

                        if (TextUtils.isEmpty(send3)) {
                            watts.setError("Field cant be Empty");
                            watts.requestFocus();
                            return;
                        }

                        else{

                            itemlist.add(editText.getText().toString());
                            itemlist1.add(editText1.getText().toString());
                            itemlist2.add(watts.getText().toString());

                            lolo = editText.getText().toString();
                            lolos = editText1.getText().toString();
                            loloss = watts.getText().toString();

                            String list = editText.getText().toString();
                            String list1 = editText1.getText().toString();
                            String list2 = watts.getText().toString();

                            //Toast.makeText(getApplicationContext(), list, Toast.LENGTH_SHORT).show();

//                            SharedPreferences sh1 = getSharedPreferences(qhello , 0);
//                            SharedPreferences.Editor edit = sh1.edit();
//                            Set<String> set = new HashSet<String>();
//                            set.addAll(itemlist);
//                            set.addAll(itemlist1);
//                            set.addAll(itemlist2);
//                            edit.putStringSet("DATE_LIST", set);
//
//                            edit.commit();
//
//                            SharedPreferences shs = getSharedPreferences(qhello, 0);
//                            Set<String> sett = shs.getStringSet("DATE_LIST", null);
//                            itemlist.addAll(sett);
//                            itemlist1.addAll(sett);
//                            itemlist2.addAll(sett);

                            //itemlist.add(get);


                            //Toast.makeText(getApplicationContext(), get, Toast.LENGTH_SHORT).show();

                            //Toast.makeText(getApplicationContext(), get, Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            editText1.setText("");
                            watts.setText("");
                            adapter.notifyDataSetChanged();

                            SweetAlertDialog su = new SweetAlertDialog(Home.this,SweetAlertDialog.SUCCESS_TYPE);
                            su.setTitleText("Appliance Added");
                            su.show();

                            dialog.dismiss();

//                            sett.setView(mview);
//                            AlertDialog dialog = sett.create();
//                            dialog.dismiss();
//                            mview.setVisibility(View.GONE);

                            //Toast.makeText(getApplicationContext(), "Appliance Added", Toast.LENGTH_SHORT).show();

                        }

                    }
                });

//                sett.setView(mview);
//                AlertDialog dialog = sett.create();
                dialog.show();

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                String food = String.valueOf(parent.getItemAtPosition(position));
                //Toast.makeText(getApplicationContext(), food, Toast.LENGTH_SHORT).show();
                Intent StartIntent = new Intent(getApplicationContext(), work.class);

                StartIntent.putExtra("appliance",lolo);
                StartIntent.putExtra("numoo",lolos);
                StartIntent.putExtra("watts",loloss);
                StartIntent.putExtra("food",food);



                startActivity(StartIntent);

            }

        });


        //MyCustomAdapter adapter = new MyCustomAdapter(this, hello);
        //

        //list = new ArrayList<String>(Arrays.asList("111,222,333,444,555,666".split(",")));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        View hvv = nav.getHeaderView(0);

        names = (TextView) hvv.findViewById(R.id.name);
        wtf = (TextView) hvv.findViewById(R.id.email);
        Uimmg = (ImageView) hvv.findViewById(R.id.Uimmg);
        hoover = (TextView) findViewById(R.id.hoover);

        names.setText(getIntent().getStringExtra("nm"));
        wtf.setText(getIntent().getStringExtra("em"));

        SharedPreferences sh = getSharedPreferences(hello, 0);
        names.setText(sh.getString("nmm", "nmoo"));
        wtf.setText(sh.getString("nmma", "nmoo1"));
        hoover.setText(sh.getString("nmmaa", "nmoo2"));

        String lolo = hoover.getText().toString();
        Uri c = Uri.parse(lolo);
        Picasso.with(getApplicationContext()).load(c).into(Uimmg);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;

    }

    @Override
    public boolean onQueryTextChange(String newText){
        String text = newText;
        adapter.getFilter().filter(newText);

        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Tap to Delete Appliance");
        menu.add(0,v.getId(),0,"Delete");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if (item.getTitle() == "Delete"){

            itemlist.remove(info.position);
            adapter.notifyDataSetChanged();

        }

            return super.onContextItemSelected(item);


    }

    class MyAdapter extends ArrayAdapter{
        ArrayList<String> applianceArray;
        ArrayList<String> numberArray;
        ArrayList<String> wattsArray;


        public MyAdapter(Context applicationContext, ArrayList<String> itemlist, ArrayList<String> itemlist1, ArrayList<String> itemlist2) {
            super(applicationContext, R.layout.customlayout, R.id.appliance, itemlist);
            this.applianceArray=itemlist;
            this.numberArray=itemlist1;
            this.wattsArray=itemlist2;

        }


        @NotNull
        @Override
        public View getView( int position, View convertView, ViewGroup parent) {
            View view = convertView;
            //if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlayout, parent, false);
           // }

          TextView appliance= (TextView)view.findViewById(R.id.appliance);
          TextView number= (TextView)view.findViewById(R.id.number);
          TextView watts= (TextView)view.findViewById(R.id.watts);
          appliance.setText(applianceArray.get(position));
          number.setText(numberArray.get(position));
          watts.setText(wattsArray.get(position));

            return view;
        }

    }

    @Override
    public void onBackPressed() {

        //finish();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {



        } else if (id == R.id.aboutus) {

            Intent StartIntent = new Intent(getApplicationContext(), Aboutus.class);

            startActivity(StartIntent);

        } else if (id == R.id.userguide) {

            Intent StartIntent = new Intent(getApplicationContext(), Userguide.class);

            startActivity(StartIntent);

        } else if (id == R.id.logout) {

            nAuth.signOut();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        nAuth.addAuthStateListener(nAuthListener);



    }


}
