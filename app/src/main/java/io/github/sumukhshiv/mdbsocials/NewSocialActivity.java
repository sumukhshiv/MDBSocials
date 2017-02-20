package io.github.sumukhshiv.mdbsocials;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewSocialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_social);

        EditText editTextEventName = (EditText) findViewById(R.id.editTextEventName);
        Button buttonUploadPicture = (Button) findViewById(R.id.buttonUploadPicture);
        EditText editTextDate = (EditText) findViewById(R.id.editTextDate);
        EditText editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        ImageView mImageView = (ImageView) findViewById(R.id.mImageView);

        buttonUploadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(NewSocialActivity.this).create();
                alertDialog.setTitle("Set a Photo");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Take a Photo",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Open Camera
                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                    startActivityForResult(takePictureIntent, 1);
                                }
//                                Bundle extras = takePictureIntent.getExtras();
//                                Bitmap imageBitmap = (Bitmap) extras.get("data");
//                                mImageView.setImageBitmap(imageBitmap);
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Upload from Gallery",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //launch gallery
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }
}
