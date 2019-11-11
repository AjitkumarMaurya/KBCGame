package com.silverwingtechnoligies.kbcgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    EditText editName;
    ImageView imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);

        editName = findViewById(R.id.editName);
        imageProfile = findViewById(R.id.imageProfile);

        if (SaveData.profil2 != null) {
            imageProfile.setImageBitmap(SaveData.profil2);
        } else if (SaveData.profile != null) {
            imageProfile.setImageURI(SaveData.profile);

        }

        if (SaveData.Name.length() > 2) {
            editName.setText(SaveData.Name);
        }


    }

    public void saveClick(View view) {

        if (editName.getText().toString().trim().length() > 2) {

            SaveData.Name = editName.getText().toString().trim();

            finish();

        } else {
            Toast.makeText(this, "Enter Valid Name", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeProfile(View view) {

        final CharSequence[] options = {"Take Photo", "Choose From Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    dialog.dismiss();
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    SettingActivity.this.startActivityForResult(intent, 101);
                } else if (options[item].equals("Choose From Gallery")) {
                    dialog.dismiss();
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    SettingActivity.this.startActivityForResult(pickPhoto, 111);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 101) {

            if (data != null && data.getExtras() != null) {
                Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
                imageProfile.setImageBitmap(imageBitmap);
                SaveData.profil2 = imageBitmap;
            } else {
                Uri selectedImage;
                if (data != null) {
                    selectedImage = data.getData();
                    imageProfile.setImageURI(selectedImage);
                    SaveData.profile = selectedImage;
                }

            }
        } else if (requestCode == 111) {
            Uri selectedImage;
            if (data != null) {
                selectedImage = data.getData();
                imageProfile.setImageURI(selectedImage);
                SaveData.profile = selectedImage;

            }

        }
    }

    public void goBack(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
