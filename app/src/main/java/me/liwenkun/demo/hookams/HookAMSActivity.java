package me.liwenkun.demo.hookams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.liwenkun.demo.R;

public class HookAMSActivity extends AppCompatActivity {

    private static String TAG = "HookAMSActivity";

    private Intent target;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_ams);

        AMSHookHelper.hook(this);
    }

    public void startUnregisteredActivity(View v) {
        startActivity(new Intent(this, UnregisteredActivity.class));
    }

}
