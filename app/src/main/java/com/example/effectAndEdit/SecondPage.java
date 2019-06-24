package com.example.effectAndEdit;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SecondPage extends AppCompatActivity implements View.OnClickListener {
    static Bitmap image;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image = FirstPage.Companion.getImageFile();

        imageView = findViewById(R.id.image_view_sec);
        Button resizeButton = findViewById(R.id.resize_button);
        Button cropButton = findViewById(R.id.crop_button);
        Button gridButton = findViewById(R.id.grid_button);
        Button blurButton = findViewById(R.id.blur_button);
        Button paintButton = findViewById(R.id.paint_button);
        Button effectButton = findViewById(R.id.effect_button);
        Button additionButton = findViewById(R.id.addition_button);
        imageView.setImageBitmap(image);

        resizeButton.setOnClickListener(this);
        cropButton.setOnClickListener(this);
        gridButton.setOnClickListener(this);
        blurButton.setOnClickListener(this);
        paintButton.setOnClickListener(this);
        effectButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
    }



    public void chooseEffect() {
        System.out.println("Effect Clicked.");
        AlertDialog.Builder effectsDialog = new AlertDialog.Builder(this);
        effectsDialog.setTitle("Choose an Effect");
        String[] effectsDialogItems = {"Effect 1", "Effect 2", "Effect 3"};
        effectsDialog.setItems(effectsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Effects.effect1(image);
                        break;
                    case 1:
                        Effects.effect2(image);
                        break;
                    case 2:
                        Effects.effect3(image);
                        break;
                }
            }
        });
        effectsDialog.show();
    }

    public void chooseAddition() {
        System.out.println("Addition Clicked.");
        AlertDialog.Builder additionsDialog = new AlertDialog.Builder(this);
        additionsDialog.setTitle("Choose an Addition");
        String[] additionsDialogItems = {"Sticker", "Text", "Frame"};
        additionsDialog.setItems(additionsDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //Here a sticker applies. From Addition class.
                        System.out.println("Sticker Clicked.");
                        break;
                    case 1:
                        //Here a text applies. From Text class.
                        System.out.println("Text Clicked.");
                        break;
                    case 2:
                        //Here a frame applies. From Frame class.
                        System.out.println("Frame Clicked.");
                        break;
                }
            }
        });
        additionsDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resize_button:
                System.out.println("Resize Clicked.");
                break;
            case R.id.addition_button:
                chooseAddition();
                break;
            case R.id.blur_button:
                System.out.println("Blur Clicked.");
                break;
            case R.id.crop_button:
                System.out.println("Crop Clicked.");
                break;
            case R.id.effect_button:
                chooseEffect();
                break;
            case R.id.grid_button:
                System.out.println("Grid Clicked.");
                break;
            case R.id.paint_button:
                System.out.println("Paint Clicked.");
                break;

        }
    }
}
