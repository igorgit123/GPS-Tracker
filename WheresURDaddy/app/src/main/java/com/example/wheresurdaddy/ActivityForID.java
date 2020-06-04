package com.example.wheresurdaddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;


public class ActivityForID extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forid);

        Button nadaljuj = (Button) findViewById(R.id.nadaljuj);
        final TextInputEditText et = (TextInputEditText) findViewById(R.id.editText);

            //to je za random id
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 9;
            Random random = new Random();
            StringBuilder buffer = new StringBuilder(targetStringLength);
            for (int i = 0; i < targetStringLength; i++) {
                int randomLimitedInt = leftLimit + (int)
                        (random.nextFloat() * (rightLimit - leftLimit + 1));
                buffer.append((char) randomLimitedInt);
            }
            String generatedString = buffer.toString();
            et.setText(generatedString);




        nadaljuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(et.getText().toString().length()==0){
                   Toast.makeText(getApplicationContext(), "Vnesite ID", Toast.LENGTH_SHORT).show();
               }else{

                   Intent i = new Intent(ActivityForID.this, MainActivity.class);
                   i.putExtra("id_uporabnika", et.getText().toString());

                   startActivity(i);

               }





            }
        });




}
}
