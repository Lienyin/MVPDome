package com.example.mvpdome.util;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

//保存图片的方法
public class ImageDownloader {

    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private Context context;

    public ImageDownloader(Context context) {
        this.context = context;
    }

    public void saveImage(String imageUrl) {
        // 检查权限
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
            Toast.makeText(context, "请授予存储权限后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        new DownloadImageTask().execute(imageUrl);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                return BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                saveImageToGallery(result);
            } else {
                Toast.makeText(context, "下载图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void saveImageToGallery(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            saveImageToGalleryQ(bitmap);
        } else {
            saveImageToGalleryLegacy(bitmap);
        }
    }

    @SuppressWarnings("deprecation")
    private void saveImageToGalleryLegacy(Bitmap bitmap) {
        try {
            // 创建图片目录
            File storageDir = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "MyAppImages");

            // 确保目录存在，如果不存在则创建（包括父目录）
            if (!storageDir.exists()) {
                if (!storageDir.mkdirs()) {
                    Toast.makeText(context, "无法创建图片目录", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            // 创建图片文件
            String fileName = "IMG_" + UUID.randomUUID().toString() + ".jpg";
            File imageFile = new File(storageDir, fileName);

            // 保存图片
            try (FileOutputStream out = new FileOutputStream(imageFile)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();

                // 通知系统相册有新图片
                MediaScannerConnection.scanFile(context,
                        new String[]{imageFile.getAbsolutePath()},
                        new String[]{"image/jpeg"},
                        null);

                Toast.makeText(context, "图片已保存至相册: " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "保存图片失败: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void saveImageToGalleryQ(Bitmap bitmap) {
        ContentResolver resolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();

        String fileName = "IMG_" + UUID.randomUUID().toString() + ".jpg";
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/MyAppImages");

        Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        if (imageUri != null) {
            try (OutputStream out = resolver.openOutputStream(imageUri)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                Toast.makeText(context, "图片已保存至相册", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, "保存图片失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
