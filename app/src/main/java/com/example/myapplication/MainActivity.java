package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button sendBTN;
    EditText ETString;
    EditText ETInt;
    Button readBTN;
    TextView raedString;
    TextView raedInt;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            initViews();

            return insets;
        });
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

        sendBTN = findViewById(R.id.sendBTN);
        ETString = findViewById(R.id.ETString);
        ETInt = findViewById(R.id.ETInt);

        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("STR", String.valueOf(ETString.getText()));  // Store the string with the key "STR"
                editor.putInt("INT", Integer.parseInt(String.valueOf(ETInt.getText())));  // Store the integer with the key "INT"
                editor.apply();  // Use apply() instead of commit() for better performance
                Toast.makeText(MainActivity.this, "Information sent successfully", Toast.LENGTH_SHORT).show();
            }
        });

        readBTN = findViewById(R.id.readBTN);
            raedString = findViewById(R.id.raedString);
        raedInt = findViewById(R.id.readInt);

        readBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the stored string and integer using the correct keys
                raedString.setText(pref.getString("STR", "No data found"));  // Default value if no data is found
                raedInt.setText(String.valueOf(pref.getInt("INT", -1)));  // Default value if no data is found
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_login){
            Toast.makeText(this, "you selected login", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_register){
            Toast.makeText(this, "you selected register", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_start){
            Toast.makeText(this, "you selected start", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.new_item) {
            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
        }
        return true;
    }
}