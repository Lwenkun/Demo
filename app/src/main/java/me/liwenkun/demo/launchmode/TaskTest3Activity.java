package me.liwenkun.demo.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.liwenkun.demo.R;

public class TaskTest3Activity extends AppCompatActivity {

    private static String TAG = "TaskTest3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_test3);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(TaskTest3Activity.this, TaskTest4Activity.class));
                startActivityForResult(new Intent(TaskTest3Activity.this, TaskTest4Activity.class), 0);

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
