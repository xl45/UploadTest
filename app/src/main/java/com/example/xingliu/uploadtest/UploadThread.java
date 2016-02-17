package com.example.xingliu.uploadtest;

import android.util.Log;

import java.io.File;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;

/**
 * Created by xingliu on 2/11/16.
 */
public class UploadThread extends Thread {

    private FTPClient client;

    private static final String SERVER_HOST = "156.56.83.26";
    private static final String FTP_USERNAME = "userstudy2";
    private static final String FTP_PASSWORD = "1234567";

    @Override
    public void run() {
        try {
            client = new FTPClient();

            client.connect(SERVER_HOST, 6002);
            Log.v("uploadtest", "connect success");
            client.login(FTP_USERNAME, FTP_PASSWORD);
            client.changeDirectory("data");
            try {
                client.changeDirectory("uploadtest");
            } catch(FTPException e) {
                client.createDirectory("uploadtest");
                client.changeDirectory("uploadtest");
            }
            Log.v("uploadtest", "changeDirectory success, current dir: " + client.currentDirectory());

            File file = new File("/sdcard/test.pdf");

            client.upload(file);

            Log.v("uploadtest", "upload success");
        } catch(Exception e) {
            Log.e("uploadtest", e.toString());
        }
    }
}
