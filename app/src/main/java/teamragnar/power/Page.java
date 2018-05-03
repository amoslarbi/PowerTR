package teamragnar.power;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Page extends AppCompatActivity {

    EditText editText;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);

    fab = (FloatingActionButton) findViewById(R.id.fab);
        editText = (EditText) findViewById(R.id.editText);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String send = editText.getText().toString().trim();
                if(TextUtils.isDigitsOnly(send)){

                    Intent startMainScreen = new Intent(getApplicationContext(),Home.class);
                    startActivity(startMainScreen);

                }else {

                    Snackbar snack = Snackbar.make(findViewById(R.id.idLayout), "Please Enter Integer Only", Snackbar.LENGTH_SHORT)
                            .setAction("RETRY", new View.OnClickListener() {

                                @Override
                                public void onClick(View view) {

                                }
                            });

                            snack.show();
                }
                    //Toast.makeText(this,"", Toast.LENGTH_SHORT)
                }


        });

    }
}
