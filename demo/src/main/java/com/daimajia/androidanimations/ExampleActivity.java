package com.daimajia.androidanimations;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.androidanimations.library.YoYoChain;

public class ExampleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

        final TextView t = (TextView) findViewById(R.id.notice);
        t.setText("Please input your Email and Password");

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYoChain chain = new YoYoChain();

                chain.addYoYoString(YoYo.with(Techniques.Tada)
                        .duration(700)
                        .playOn(findViewById(R.id.edit_area)));

                chain.addYoYoString(YoYo.with(Techniques.Shake).playOn(findViewById(R.id.edit_area2)));
                chain.addYoYoString(new YoYo.YoYoCallFuncString(new YoYo.YoYoCallFuncString.handle() {
                    @Override
                    public void callback() {
                        Toast.makeText(ExampleActivity.this, "Toast", Toast.LENGTH_SHORT).show();
                    }
                }));
                chain.play();
                t.setText("Wrong password!");
            }
        });

        final TextView t2 = (TextView) findViewById(R.id.notice2);
        t2.setText("Please input your Email and Password");

        findViewById(R.id.submit2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                YoYo.with(Techniques.Shake).playOn(findViewById(R.id.edit_area2));

                t2.setText("Wrong password!");
            }
        });
    }
}
