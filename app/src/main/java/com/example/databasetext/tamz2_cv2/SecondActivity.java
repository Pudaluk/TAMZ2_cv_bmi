package com.example.databasetext.tamz2_cv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


/**
 * Created by Lukas on 03.10.2017.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private ImageView cap_foto_preview;
    private File output=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);


        TextView textView = (TextView) findViewById(R.id.bmi_res);
        textView.setText(message);

        Button info = (Button) findViewById(R.id.btn_info);
        info.setOnClickListener(this);
        Button cap_img = (Button) findViewById(R.id.btn_cap_img);
        cap_img.setOnClickListener(this);
        Button share = (Button) findViewById(R.id.btn_share);
        share.setOnClickListener(this);

        cap_foto_preview = (ImageView)this.findViewById(R.id.cap_foto_preview);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_info:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://cs.wikipedia.org/wiki/Index_t%C4%9Blesn%C3%A9_hmotnosti"));
                startActivity(i);
                break;

            case R.id.btn_cap_img:
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                File dir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                output=new File(dir, "CameraContentDemo.jpeg");
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));
                break;

            case R.id.btn_share:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "My BMI with FOTO !");
                intent.putExtra(Intent.EXTRA_TEXT, "My BMI: " + MainActivity.EXTRA_MESSAGE);
                intent.putExtra(Intent.EXTRA_STREAM, MediaStore.EXTRA_OUTPUT);
                intent.setData(Uri.parse("mailto:"));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            default:
                break;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            cap_foto_preview.setImageBitmap(photo);
            Intent i=new Intent(Intent.ACTION_VIEW);

            i.setDataAndType(Uri.fromFile(output), "image/jpeg");
            startActivity(i);
            finish();
        }
    }
}
