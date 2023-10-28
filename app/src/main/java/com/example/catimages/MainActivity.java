package com.example.catimages;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
    private ImageView catImageView;
    private ProgressBar progressBar;
    private boolean loadCatImage = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catImageView = findViewById(R.id.catImageView);
        progressBar = findViewById(R.id.progressBar);


        new CatImages().execute();
    }

    private class CatImages extends AsyncTask<Void, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {

                int imageResource = loadCatImage ? R.mipmap.catimage : R.mipmap.kittyimage;
                loadCatImage = !loadCatImage;
                return BitmapFactory.decodeResource(getResources(), imageResource);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (result != null) {

                catImageView.setImageBitmap(result);
            } else {

                Toast.makeText(MainActivity.this, "Error loading image", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
