package me.liwenkun.demo.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.liwenkun.demo.R;

public class TaskTest2Activity extends AppCompatActivity {

    private static String TAG = "TaskTest2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_test2);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TaskTest2Activity.this, TaskTest3Activity.class), 0);
//                startActivity(new Intent(TaskTest2Activity.this, TaskTest3Activity.class));
            }
        });

        setResult(RESULT_OK, new  Intent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, "onNewIntent has been called");
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "requestCode=" + requestCode + ";resultCode=" + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
