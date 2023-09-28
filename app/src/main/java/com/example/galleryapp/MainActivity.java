package com.example.galleryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import android.Manifest;
import android.view.View;
import android.widget.Toast;

import com.example.galleryapp.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List <String> n=new ArrayList<>();
    ActivityMainBinding binding;

    private Uri imageUri;

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;
    private static final int IMAGE_CAPTURE_CODE = 103;

    RecyclerViewImage recyclerViewImage =new RecyclerViewImage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        binding.camera.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,CameraActivity.class));
                        finish();
                    }
                }
        );
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1) { // Check the request code
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                n=imagePath();
                Log.d("t", String.valueOf(n.size()));
                recyclerViewImage.setList((ArrayList<String>) n);
                binding.recyclerImage.setAdapter(recyclerViewImage);

            } else {
                Toast.makeText(MainActivity.this,"no permission",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<String> imagePath() {
        List<String> pathImag = new ArrayList<>();
        String[] projection = {MediaStore.Images.Media.DATA};

        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                null
        );

        if (cursor != null) {
            while (cursor.moveToNext()) {
                // Get the image path from the cursor
                @SuppressLint("Range") String imagePath = cursor.getString(
                        cursor.getColumnIndex(MediaStore.Images.Media.DATA)
                );
                pathImag.add(imagePath);
            }
            cursor.close();

        }
        return pathImag;
    }


}