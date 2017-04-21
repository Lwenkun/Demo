package me.liwenkun.demo.droidplugin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lwenkun on 2017/4/14.
 */

public class ZhihuTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button button = new Button(this);
        button.setText("start zhihu");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.zhihu.android", "com.zhihu.android.app.ui.activity.MainActivity"));
                startActivity(intent);
//                i.addCategory(Intent.CATEGORY_LAUNCHER);
//                List<ResolveInfo> activityInfoList = getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
              //  i.addCategory(Intent.CATEGORY_APP_MARKET);
                //i.addCategory(Intent.CATEGORY_DEFAULT);
             //   i.addCategory(Intent.CATEGORY_DEFAULT);
              //  startActivity(i);
            }
        });

        setContentView(button);
    }
}
