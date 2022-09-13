package com.buggysofts.androidzipimpl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;

import com.buggysofts.androidzip.AndroidZip;
import com.buggysofts.streamzip.ZipEntry;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // log tag
    private static final String LOG_TAG = "MainActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101: {
                long s = System.currentTimeMillis();
                Uri uri = null;
                DocumentFile documentFile =
                    (data != null && (uri = data.getData()) != null) ?
                        DocumentFile.fromSingleUri(MainActivity.this, uri) :
                        null;

                if(documentFile != null){
                    AndroidZip zip = null;
                    try {
                        zip = new AndroidZip(MainActivity.this, documentFile);

                        List<ZipEntry> entries = zip.entries();
                        long e = System.currentTimeMillis();
                        for (int i = 0; entries != null &&  i < entries.size(); ++i) {
                            ZipEntry zipEntry = entries.get(i);
                            if (!zipEntry.isDirectory()) {
                                Log.d(
                                    LOG_TAG,
                                    String.format(
                                        "\nFILE: %s --- %d --- %d - %s",
                                        zipEntry.getFileName(),
                                        zipEntry.getCompressedSize(),
                                        zipEntry.getUncompressedSize(),
                                        new String(StreamUtils.readFully(zip.getInputStream(zipEntry), Integer.MAX_VALUE, false))
                                    )
                                );
                            } else {
                                // do anything
                                System.out.printf(
                                    "\nDIR: %s",
                                    zipEntry.getFileName()
                                );
                            }
                        }
                        System.out.println("\n\nTime taken: " + (e - s) + "ms");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            default: {
                // nothing
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivityForResult(
            new Intent(Intent.ACTION_OPEN_DOCUMENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("application/zip"),
            101
        );
    }
}