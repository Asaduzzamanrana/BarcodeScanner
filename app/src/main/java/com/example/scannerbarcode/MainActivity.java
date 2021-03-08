package com.example.scannerbarcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    //private Button scan_btn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textViewId);
       // scan_btn = findViewById(R.id.btn_scanner);

        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
        //set promote
        intentIntegrator.setPrompt("Scanner Ready");
        //set beep
        intentIntegrator.setBeepEnabled(true);
        //orientation locked
        intentIntegrator.setOrientationLocked(true);
        //set Capture Activity
        intentIntegrator.setCaptureActivity(Capture.class);
        //initiate scan
        intentIntegrator.initiateScan();
    }
// if when use button
//        scan_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
//                //set promote
//                intentIntegrator.setPrompt("for flash use");
//                //set beep
//                intentIntegrator.setBeepEnabled(true);
//                //orientation locked
//                intentIntegrator.setOrientationLocked(true);
//                //set Capture Activity
//                intentIntegrator.setCaptureActivity(Capture.class);
//                //initiate scan
//                intentIntegrator.initiateScan();
//            }
//        });
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //initial intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        //check condition
        if (intentResult.getContents() !=null)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    //set title
                    builder.setTitle("Result");
                    textView.setText(intentResult.getContents());
                    //set message
                    builder.setMessage(intentResult.getContents());
                    //set positive button
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss dialog
                            dialog.dismiss();
                        }
                    });
                    //show dialog
                    builder.show();
                }
        else
            {
                Toast.makeText(this, "scan failed", Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setIcon(R.drawable.ques);
        alertDialogBuilder.setMessage(R.string.messasetext);
        alertDialogBuilder.setTitle(R.string.alerttext);

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialogBuilder.setNeutralButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}