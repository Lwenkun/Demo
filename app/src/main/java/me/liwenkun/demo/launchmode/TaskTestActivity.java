package me.liwenkun.demo.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import me.liwenkun.demo.R;

public class TaskTestActivity extends AppCompatActivity {

    private static String TAG = "TaskTestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_test);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.button).requestLayout();

              //  startActivityForResult( new Intent(TaskTestActivity.this, TaskTest2Activity.class), 0);
//                startActivity( new Intent(TaskTestActivity.this, TaskTest2Activity.class));

            }
        });

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
