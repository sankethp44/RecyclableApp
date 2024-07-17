package com.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ml.ModelUnquant;

public class Scanner123Fragment extends AppCompatActivity {

    private TextView result;
    private TextView confidence;
    private ImageView imageView;
    private Button cameraButton;
    private Button uploadButton;
    private int imageSize = 224;
    private static final int PICK_IMAGE_REQUEST = 2;
    private ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_scanner123_fragment);

        findViewById(R.id.classified).setVisibility(View.GONE);
        findViewById(R.id.confidencesText).setVisibility(View.GONE);

        result = findViewById(R.id.result);
        confidence = findViewById(R.id.confidence);
        imageView = findViewById(R.id.imageView);
        cameraButton = findViewById(R.id.button);
        uploadButton = findViewById(R.id.uploadButton);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 1);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
                } else {
                    requestPermissions(new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 101);
                }
            }
        });
    }

    private void classifyImage(Bitmap image) {
        try {
            ModelUnquant model = ModelUnquant.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; ++i) {
                for (int j = 0; j < imageSize; ++j) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat((val >> 16 & 0xFF) * (1f / 255f));
                    byteBuffer.putFloat((val >> 8 & 0xFF) * (1f / 255f));
                    byteBuffer.putFloat((val & 0xFF) * (1f / 255f));
                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            ModelUnquant.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] confidences = outputFeature0.getFloatArray();
            int maxPos = 0;
            float maxConfidence = 0f;
            if (confidences != null && confidences.length > 0) {
                for (int i = 0; i < confidences.length; i++) {
                    if (confidences[i] > maxConfidence) {
                        maxConfidence = confidences[i];
                        maxPos = i;
                    }
                }
            }
            List<String> classes = Arrays.asList("Battery", "Keyboard", "Books", "Magazine", "Microwave", "Mobile", "Mouse", "News Paper", "Paper Cups", "PCB", "Plastic bottle", "Printer", "Soda Can", "Straw", "Television", "Washing Machine");
            result.setText(classes.get(maxPos));
            String predictedClass = maxPos < classes.size() ? classes.get(maxPos) : "Unknown Class";
            StringBuilder s = new StringBuilder();
            if (confidences != null && confidences.length > 0) {
                // Create a list of Pair(class, confidence) for sorting
                // Sort by confidence descending
                for (int i = 0; i < confidences.length; i++) {
                    s.append(String.format("%s: %.1f%%\n", classes.get(i), confidences[i] * 100));
                }
            }
            // Check if the predicted class is in the recyclable list
            boolean isRecyclable = classes.contains(predictedClass) && maxConfidence > 0.6;
            if (result != null && confidence != null) {
                result.setText((predictedClass.equals("Unknown Class") || !isRecyclable) ? "Not Recyclable" : "Recyclable");
                confidence.setText(s.toString());
            } else {
            }
            model.close();
        } catch (IOException e) {
        }
    }

    @Deprecated
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            findViewById(R.id.recycle_your_waste_materials).setVisibility(View.GONE);
            findViewById(R.id.graphics).setVisibility(View.GONE);
            findViewById(R.id.scannerimage).setVisibility(View.GONE);
            findViewById(R.id.classified).setVisibility(View.VISIBLE);
            findViewById(R.id.confidencesText).setVisibility(View.VISIBLE);
            Bitmap image = (Bitmap) data.getExtras().get("data");
            processAndClassifyImage(image);
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            findViewById(R.id.recycle_your_waste_materials).setVisibility(View.GONE);
            findViewById(R.id.graphics).setVisibility(View.GONE);
            findViewById(R.id.scannerimage).setVisibility(View.GONE);
            findViewById(R.id.classified).setVisibility(View.VISIBLE);
            findViewById(R.id.confidencesText).setVisibility(View.VISIBLE);
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                Bitmap image = BitmapFactory.decodeFile(picturePath);
                processAndClassifyImage(image);
            }
        }
    }

    private void processAndClassifyImage(Bitmap image) {
        if (image != null) {
            int dimension = Math.min(image.getWidth(), image.getHeight());
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(thumbnail);
            Bitmap scaledImage = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            classifyImage(scaledImage);
        }
    }
}
