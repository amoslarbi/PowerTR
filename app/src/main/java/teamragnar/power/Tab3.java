package teamragnar.power;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kh$y on 5/5/2018.
 */

public class Tab3 extends Fragment {

    private Chronometer ch;
    int count = 0;
    Button b1, b2;
    TextView tx, cou;
    double sum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tab3, container, false);

        ch = (Chronometer) view.findViewById(R.id.ch);
        b1 = (Button) view.findViewById(R.id.b1);
        b2 = (Button) view.findViewById(R.id.b2);
        tx = (TextView) view.findViewById(R.id.tx);
        cou = (TextView) view.findViewById(R.id.cou);

        ch.setBase(SystemClock.elapsedRealtime());
        ch.start();

        Thread t = new Thread(){
            public void run(){
                while(!isInterrupted()){

                    try {
                        Thread.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                            count++;
                                tx.setText(String.valueOf(count));

                                double hello = Double.parseDouble(tx.getText().toString());
                                sum = hello + 100;
                                cou.setText(Double.toString(sum));

                            }
                        });



                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

            }

        };
        t.start();


//        final long elapsedMillis = 100 * ch.getBase();
//        Toast.makeText(getActivity(), "Elapsed milliseconds: " + elapsedMillis,
//                Toast.LENGTH_SHORT).show();

//        ch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                tx.setText(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ch.setBase(SystemClock.elapsedRealtime());
//                ch.start();
//
//            }
//        });


        return view;


    }


}
