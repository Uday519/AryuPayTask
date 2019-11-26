package com.uday.aryupaytask.repo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;

public class DownloadImages extends AsyncTask<String , Void, Bitmap> {
    ImageView image;

    public DownloadImages(ImageView image) {
        this.image = image;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        String url = strings[0];
        Bitmap bit = null;
        try{
            java.net.URL jurl = new java.net.URL(url);
            HttpURLConnection connection =(HttpURLConnection) jurl.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }
        catch (Exception e){
            Log.e("Error loading Image",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        image.setImageBitmap(bitmap);

    }
}
