package me.liwenkun.demo.hookams;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.liwenkun.demo.R;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class UnregisteredActivity extends AppCompatActivity {

    private static final int START_FOR_RESULT = 0;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {







        setContentView(R.layout.activitiy_unregistered);
    }

    public void startAnotherActivityForResult(View v) {
        startActivityForResult(new Intent(this, AnotherUnregisteredActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == START_FOR_RESULT) {
            if (resultCode == RESULT_OK) Toast.makeText(this,
                    data == null ? "data is null" : data.getStringExtra("name"), Toast.LENGTH_SHORT)
                    .show();
        }
        super.onActivityResult(requestCode, resultCode, data);

    }
}
