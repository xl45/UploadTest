package com.example.xingliu.uploadtest;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });


        UploadThread thread = new UploadThread();
        thread.start();

//        String key = "iwantgirlfriend"; // needs to be at least 8 characters for DES

//        String inFilePath = "/sdcard/ss.zip";
//        String outFilePath = inFilePath + ".encrypted";
//
//        try {
//            Encryptor.encrypt(key, inFilePath, outFilePath);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }

//        String inFilePath = "/sdcard/ss.zip.encrypted";
//        String outFilePath = "/sdcard/ss.zip.decrypted";
//
//        try {
//            Encryptor.decrypt(key, inFilePath, outFilePath);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
    }
}
