package me.liwenkun.demo.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.liwenkun.demo.R;
import me.liwenkun.demo.servicetest.TestService;

public class TaskTest4Activity extends AppCompatActivity {

    private static String TAG = "TaskTest4Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_test4);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(TaskTest4Activity.this, TaskTest3Activity.class), 0);
//                startActivity(new Intent(TaskTest4Activity.this, TaskTest3Activity.class));
                startService(new Intent(TaskTest4Activity.this, TestService.class));
            }
        });

        setResult(RESULT_OK, new Intent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent has been called");
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode=" + requestCode + "; resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
