package me.liwenkun.demo.hookams;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.liwenkun.demo.R;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class AnotherUnregisteredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_unregistered);

    }

    public void back(View v) {
        Intent result = new Intent();
        result.putExtra("result", "This is the result you want");
        setResult(RESULT_OK, result);
        finish();
    }
}
