package com.example.effectAndEdit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

public class Addition extends AppCompatActivity implements View.OnClickListener{
    static Bitmap image;
    String type;
    ImageView imageView, sticker;
    Bitmap bitmapSticker, bitmapFrame;
    TextView textView;
    ViewGroup viewGroup;
    Button btn;
    Button okButton;
    AlertDialog dialog;
    EditText editText;
    private int xDelta;
    private int yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        image = FirstPage.Companion.getImageFile();
        imageView = findViewById(R.id.image_addition);
        imageView.setImageBitmap(image);
        //editText = findViewById(R.id.edittext);


        btn = findViewById(R.id.button_addition_next);
        btn.setOnClickListener(this);
        if (type.equals("text")){
            setText();
        }else if(type.equals("frame")){
            setFrame();
        }else if(type.equals("sticker")){
            setSticker();
        }

    }

    private void setSticker() {
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_sticker,null);
        ImageView[] imageViews = new ImageView[11];
        imageViews[0] = view.findViewById(R.id.image1);
        imageViews[1] = view.findViewById(R.id.image2);
        imageViews[2] = view.findViewById(R.id.image3);
        imageViews[3] = view.findViewById(R.id.image4);
        imageViews[4] = view.findViewById(R.id.image5);
        imageViews[5] = view.findViewById(R.id.image6);
        imageViews[6] = view.findViewById(R.id.image7);
        imageViews[7] = view.findViewById(R.id.image8);
        imageViews[8] = view.findViewById(R.id.image9);
        imageViews[9] = view.findViewById(R.id.image10);
        imageViews[10] = view.findViewById(R.id.image11);
        for (int i = 0; i < 11; i++) {
            imageViews[i].setOnClickListener(new StickerTouch());
        }

        alerDialog.setView(view);
        dialog = alerDialog.create();
        dialog.show();

        viewGroup = (ViewGroup)findViewById(R.id.top_layout);
        sticker = new ImageView(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(240,240);

        sticker.setLayoutParams(layoutParams);
        sticker.setOnTouchListener(new ChoiceTouchListener());
        viewGroup.addView(sticker);
    }

    private void setFrame() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.frame_layout,null);
        ImageView[] imageViews = new ImageView[3];
        imageViews[0] = view.findViewById(R.id.image1_frame);
        imageViews[1] = view.findViewById(R.id.image2_frame);
        imageViews[2] = view.findViewById(R.id.image3_frame);
        for (int i = 0; i < 3; i++) {
            imageViews[i].setOnClickListener(new frameTouch());
        }

        alertDialog.setView(view);
        dialog = alertDialog.create();
        dialog.show();
    }
    final int[] color = new int[1];
    private void setText() {

        AlertDialog.Builder colorDialog = new AlertDialog.Builder(this);
        colorDialog.setTitle("رنگ قلم را انتخاب کنید");
        String[] colorDialogItems = {"آبی", "سبز", "قرمز", "زرد", "سفید", "سیاه"};
        colorDialog.setItems(colorDialogItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        color[0] = Color.BLUE;
                        break;
                    case 1:
                        color[0] = Color.GREEN;
                        break;
                    case 2:
                        color[0] = Color.RED;
                        break;
                    case 3:
                        color[0] = Color.YELLOW;
                        break;
                    case 4:
                        color[0] = Color.WHITE;
                        break;
                    case 5:
                        color[0] = Color.BLACK;
                        break;
                }
            }
        });
        colorDialog.show();

        viewGroup = findViewById(R.id.small_layout);
        textView = new TextView(this);
        editText = findViewById(R.id.edittext);
        editText.setVisibility(View.VISIBLE);
        okButton = findViewById(R.id.ok_button_text);
        okButton.setOnClickListener(this);
        okButton.setVisibility(View.VISIBLE);
        textView.setDrawingCacheEnabled(true);



    }
    private class frameTouch implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            ImageView frame;
            switch (v.getId()){
                case R.id.image1_frame:
                    frame = findViewById(R.id.image1_frame);
                    bitmapFrame = BitmapFactory.decodeResource(getResources(),R.drawable.image_border);
                    sticker.setImageBitmap(bitmapFrame);
                    dialog.dismiss();

                    Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                    Canvas canvas = new Canvas(merge);
                    canvas.drawBitmap(image, new Matrix(), null);
                    if (bitmapFrame != null) {
                        //canvas.drawBitmap(bitmapSticker, bitmapSticker.getHeight() + imageView.getX(), bitmapSticker.getWidth() + imageView.getY(), null);
                        canvas.drawBitmap(bitmapFrame, imageView.getX(), imageView.getY(), null);

                    }

                    image =  merge;
                    imageView.setImageBitmap(merge);

                case R.id.image2_frame:
                    frame = findViewById(R.id.image2_frame);
                    dialog.dismiss();
                case R.id.image3_frame:
                    frame = findViewById(R.id.image3_frame);
                    dialog.dismiss();


            }
        }
    }

    private class StickerTouch implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.image1:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.smiling_face_with_heart_shaped_eyes_1f60d);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image2:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.multiple_musical_notes_1f3b6);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image3:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.male_technologist_type_1_2_1f468_1f3fb_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image4:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_raising_both_hands_in_celebration_emoji_modifier_fitzpatrick_type_1_2_1f64c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image5:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_with_folded_hands_emoji_modifier_fitzpatrick_type_1_2_1f64f_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image6:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.person_with_headscarf_emoji_modifier_fitzpatrick_type_1_2_1f9d5_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image7:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.raised_hand_with_fingers_splayed_emoji_modifier_fitzpatrick_type_1_2_1f590_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image8:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.victory_hand_emoji_modifier_fitzpatrick_type_1_2_270c_1f3fb_1f3fb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image9:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.white_heavy_check_mark_2705);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image10:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.female_technologist_type_3_1f469_1f3fc_200d_1f4bb);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;
                case R.id.image11:
                    bitmapSticker = BitmapFactory.decodeResource(getResources(), R.drawable.flag_for_iran_1f1ee_1f1f7);
                    sticker.setImageBitmap(bitmapSticker);
                    dialog.dismiss();
                    break;

            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addition_next:
                if (type.equals("sticker")) {
                    Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                    Canvas canvas = new Canvas(merge);
                    canvas.drawBitmap(image, new Matrix(), null);
                    if (bitmapSticker != null && sticker.getX() - imageView.getX() > 0 && sticker.getY() - imageView.getY() > 0) {
                        canvas.drawBitmap(bitmapSticker, sticker.getX() - imageView.getX(), sticker.getY() - imageView.getY(), null);
                    }
                    image =  merge;
                    FirstPage.Companion.setImageFile(merge);

                }
                else if(type.equals("frame")){

                }
                else if(type.equals("text")){
                    Bitmap merge = Bitmap.createBitmap(image.getWidth(), image.getHeight(), image.getConfig());
                    Canvas canvas = new Canvas(merge);
                    canvas.drawBitmap(image, new Matrix(), null);

                    Bitmap text = Bitmap.createBitmap(textView.getDrawingCache());

                    if (text != null && textView.getX() - imageView.getX() >0 && textView.getY() - imageView.getY() > 0) {

                        canvas.drawBitmap(text, textView.getX() - imageView.getX() + 30 , textView.getY() - imageView.getY() + 30, null);
                    }
                    image = merge;
                    FirstPage.Companion.setImageFile(merge);
                }
                Intent intent = new Intent(this, SecondPage.class);
                SecondPage.fa.finish();
                startActivity(intent);
                finish();
                break;
            case R.id.ok_button_text:
                textView.setText(editText.getText().toString());
                okButton.setVisibility(View.GONE);
                editText.setVisibility(View.GONE);

                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(400,400);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(20,20,20,20);
                textView.setTextSize(22f);
                textView.setTextColor(color[0]);
                viewGroup.addView(textView);
                textView.setOnTouchListener(new ChoiceTouchListener());
                

        }
    }



    private class ChoiceTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            final int X = (int) event.getRawX();
            final int Y = (int) event.getRawY();
            switch (event.getAction() & MotionEvent.ACTION_MASK){
                case MotionEvent.ACTION_DOWN:
                    RelativeLayout.LayoutParams lparams =(RelativeLayout.LayoutParams)v.getLayoutParams();
                    xDelta = X - lparams.leftMargin;
                    yDelta = Y - lparams.topMargin;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    layoutParams.leftMargin = X - xDelta;
                    layoutParams.topMargin = Y - yDelta;
                    layoutParams.rightMargin = -250;
                    layoutParams.bottomMargin = -250;
                    v.setLayoutParams(layoutParams);
                    break;
            }
            viewGroup.invalidate();
            return true;
        }
    }
}
