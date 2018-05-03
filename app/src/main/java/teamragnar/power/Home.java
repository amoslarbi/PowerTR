package teamragnar.power;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    String[] hello = {"Hello"};
    FloatingActionButton aboutback;
    EditText editText;
    ArrayList itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listview);
        itemlist = new ArrayList<>();

        final ArrayAdapter adapter = new ArrayAdapter<String>(Home.this, android.R.layout.simple_list_item_multiple_choice, itemlist);
        listView.setAdapter(adapter);

        aboutback  = (FloatingActionButton) findViewById(R.id.forward);

        aboutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder sett = new AlertDialog.Builder(Home.this);
                final View mview = getLayoutInflater().inflate(R.layout.addapp,null);
                 editText = (EditText) mview.findViewById(R.id.editText);
                FloatingActionButton sposted = (FloatingActionButton) mview.findViewById(R.id.button3);

                sposted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String str3 = editText.getText().toString();

                        if (TextUtils.isEmpty(str3)) {
                            editText.setError("Field cant be Empty");
                            editText.requestFocus();
                            return;
                        }

                        else{

                            itemlist.add(editText.getText().toString());
                            editText.setText("");
                            adapter.notifyDataSetChanged();
                            //mview.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Appliance Added", Toast.LENGTH_SHORT).show();
                            editText.setText("");
                        }
                    }
                });

                sett.setView(mview);
                AlertDialog dialog = sett.create();
                dialog.show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SparseBooleanArray positionc = listView.getCheckedItemPositions();

                int count = listView.getCount();
                for(int item = count -1; item >=0; item--) {

                    if (positionc.get(item)) {

                        adapter.remove(itemlist.get(item));

                    }

                }
                positionc.clear();

                adapter.notifyDataSetChanged();

                return false;
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Appliance Added");
        menu.add(0,v.getId(),0,"Delete");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if (item.getTitle() == "Delete"){


        }

       return true;
    }

//    class MyCustomAdapter extends ArrayAdapter{
//
//        String[] hellooArray;
//
//        public MyCustomAdapter(Context c, String[] hello1){
//            super(c, R.layout.customlayout, R.id.textView, hello1);
//            this.hellooArray=hello1;
//
//        }
//
//        @NotNull
//        @Override
//        public View getView( int position, View convertView, ViewGroup parent) {
//            View view = convertView;
//            //if (view == null) {
//                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(R.layout.customlayout, parent, false);
//           // }
//
//        TextView mytextView= (TextView)view.findViewById(R.id.textView);
////        tvContact.setText(list.get(position));
//
//            //Handle buttons and add onClickListeners
//           // Switch callbtn = (Switch) view.findViewById(R.id.swtch);
//
//            mytextView.setText(hellooArray[position]);
////
////            callbtn.setOnClickListener(new View.OnClickListener(){
////                @Override
////                public void onClick(View v) {
////                    //do something
////
////                }
////            });
////        addBtn.setOnClickListener(new View.OnClickListener(){
////            @Override
////            public void onClick(View v) {
////                //do something
////                notifyDataSetChanged();
////
////            }
////        });
//
//            return view;
//        }

    //}

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
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
}
