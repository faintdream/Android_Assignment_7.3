package com.akashdubey.imageviewdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView pic;
    Button browse;
    Uri uri;
    static final Integer PICK_IMAGE_STATUS=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating objects of view(controls)
        pic=(ImageView)findViewById(R.id.picIV);
        browse=(Button)findViewById(R.id.browseBtn);


        //button click event
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // creating an intent to get content
                Intent selectImage= new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                selectImage.setType("image/*"); //forcing intent to recognise only images
                startActivityForResult(selectImage,PICK_IMAGE_STATUS);
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode==PICK_IMAGE_STATUS) && (resultCode==RESULT_OK)){
            uri=data.getData();
            pic.setImageURI(uri);
            Toast.makeText(this, "Operation Success", Toast.LENGTH_SHORT).show();
        }
        else{
            pic.setImageDrawable(null);
            Toast.makeText(this, "Operation Failed", Toast.LENGTH_SHORT).show();
        }

        }
    }

