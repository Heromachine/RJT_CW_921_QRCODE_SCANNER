package com.example.jessi.rjt_cw_921_qrcode_scanner;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button btn;
    TextView textView;
    IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        intentIntegrator = new IntentIntegrator(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(TAG, "onActivityResult: ");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null)
        {
            if(result.getContents() == null)
            {
                Toast.makeText(this, "RESULTS ARE EMPTY", Toast.LENGTH_LONG);
            }
            else
            {
                try{
                    //JSONObject jsonObject = new JSONObject(result.getContents());
                    Toast.makeText(this, "OUTPUT: "+result.getContents().toString(), Toast.LENGTH_SHORT).show();

                }catch(Exception e){
                    Toast.makeText(this, "EXCEPTION", Toast.LENGTH_LONG);
                    e.printStackTrace();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
