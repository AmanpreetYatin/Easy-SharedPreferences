package com.amn.sample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amn.easysharedpreferences.EasySharedPreference;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    EditText editText;
    Button buttonPutString;
    Button buttonGetString;
    Context context;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listener();

    }

    private void listener() {
        buttonPutString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();

                EasySharedPreference.Companion.putString("TEST_KEY", text);

                Toast.makeText(context, "String added to SharedPreferences", Toast.LENGTH_LONG).show();

            }
        });

        buttonGetString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = EasySharedPreference.Companion.getString("TEST_KEY", "default");

                tvOutput.setText(text);
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        context = this;
        editText = findViewById(R.id.editText);
        buttonPutString = findViewById(R.id.buttonPutString);
        buttonGetString = findViewById(R.id.buttonGetString);
        tvOutput = findViewById(R.id.tvText);
    }
}
