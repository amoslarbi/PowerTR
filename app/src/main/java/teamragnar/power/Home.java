package teamragnar.power;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    ListView listView;
    SearchView search;
    FloatingActionButton aboutback;
    EditText editText, editText1;
    ArrayList<String> itemlist;
    String [] tt = {"hellol"};
    //ArrayAdapter<String> adapter;
    MyAdapter adapter;

    private TextView names, wtf, hoover, larry;
    Uri imageUri;
    ImageView Uimmg;
    public static final String hello = "hellol";
    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener nAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        itemlist = new ArrayList<>();

        //adapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_1, itemlist);
        adapter = new MyAdapter(getApplicationContext(), itemlist);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);

        search = (SearchView) findViewById(R.id.search);
        search.setOnQueryTextListener(this);

        aboutback  = (FloatingActionButton) findViewById(R.id.forward);
        aboutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder sett = new AlertDialog.Builder(Home.this);
                final View mview = getLayoutInflater().inflate(R.layout.addapp,null);
                 editText = (EditText) mview.findViewById(R.id.editText);
                 editText1 = (EditText) mview.findViewById(R.id.num);
                FloatingActionButton sposted = (FloatingActionButton) mview.findViewById(R.id.button3);

                sposted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String str3 = editText.getText().toString();
                        String send = editText1.getText().toString().trim();
                        String send1 = editText1.getText().toString();

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

//                        if(TextUtils.isDigitsOnly(send)){
//
//
//                        }

                        else{

                            itemlist.add(editText.getText().toString());
                            editText.setText("");
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Appliance Added", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                            editText1.setText("");
                        }

                    }
                });

                sett.setView(mview);
                AlertDialog dialog = sett.create();
                dialog.show();

            }
        });

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                SparseBooleanArray positionc = listView.getCheckedItemPositions();
//
//                int count = listView.getCount();
//                for(int item = count -1; item >=0; item--) {
//
//                    if (positionc.get(item)) {
//
//                        adapter.remove(itemlist.get(item));
//
//                    }
//
//                }
//                positionc.clear();
//
//                adapter.notifyDataSetChanged();
//
//                return false;
//
//
//            }
//        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){

                String food = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(), food, Toast.LENGTH_SHORT).show();
                Intent StartIntent = new Intent(getApplicationContext(), Play.class);
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

//        names = (TextView) findViewById(R.id.name);
//        wtf = (TextView) findViewById(R.id.email);
        hoover = (TextView) findViewById(R.id.hoover);

        names.setText(getIntent().getStringExtra("nm"));
        wtf.setText(getIntent().getStringExtra("em"));
        //hoover.setText(getIntent().getStringExtra("larry"));

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
        String[] numberArray;
        String[] wattsArray;

        public MyAdapter(Context c, String[] appliance1, String[] number1, String[] watts1){
            super(c, R.layout.customlayout, R.id.appliance, appliance1);
            //this.applianceArray=appliance1;
            this.numberArray = number1;
            this.wattsArray = watts1;

        }

        public MyAdapter(Context applicationContext, ArrayList<String> itemlist) {
            super(applicationContext, R.layout.customlayout, R.id.appliance, itemlist);
            this.applianceArray=itemlist;

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
          //TextView number= (TextView)view.findViewById(R.id.number);
          //TextView watts= (TextView)view.findViewById(R.id.watts);
          appliance.setText(applianceArray.get(position));
          //number.setText(numberArray[position]);
          //watts.setText(wattsArray[position]);

//            //Handle buttons and add onClickListeners
//           // Switch callbtn = (Switch) view.findViewById(R.id.swtch);

//
//            callbtn.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    //do something
//
//                }
//            });
//        addBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //do something
//                notifyDataSetChanged();
//
//            }
//        });

            return view;
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {



    }
}
