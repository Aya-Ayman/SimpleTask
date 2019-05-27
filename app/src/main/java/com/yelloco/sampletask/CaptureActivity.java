package com.yelloco.sampletask;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yelloco.sampletask.pizza_activity.model.Pizza;

import java.io.File;
import java.io.IOException;


public class CaptureActivity extends AppCompatActivity {

    TextView name, description;
    ImageButton imageButton;
    Uri photoURI = null;
    File photoFile = null;
    Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        imageButton = findViewById(R.id.image);
        Intent intent = getIntent();
        pizza = (Pizza) intent.getSerializableExtra("item");
        name.setText(pizza.getName());
        description.setText(pizza.getDescription());

    }


    public void onClick(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            return;
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            if (view.getId() == R.id.image && takePictureIntent.resolveActivity(this.getPackageManager()) != null) {

                {
                    try {
                        photoFile = createImageFile();

                        if (photoFile != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                                photoURI = FileProvider.getUriForFile(this,
                                        "com.yelloco.sampletask",
                                        photoFile);
                            } else {
                                photoURI = Uri.fromFile(photoFile);

                            }

                            this.grantUriPermission("com.yelloco.sampletask", photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, 1);
                        }
                    } catch (IOException ex) {

                    }
                }
            }

        }
    }

    private File createImageFile() throws IOException {

        String imageFileName = "IMG";
        int time = (int) (System.currentTimeMillis());

        File rootPath = new File(this.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Yello");
        if (!rootPath.exists()) {
            rootPath.mkdirs();
        }

        File image = new File(rootPath, imageFileName + time + ".jpg");
        return image;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED && resultCode == RESULT_OK) {

            try {
                Bitmap bitmap = MediaStore.Images.Media
                        .getBitmap(this.getContentResolver(), Uri.fromFile(photoFile));

                if (bitmap != null)
                    imageButton.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}