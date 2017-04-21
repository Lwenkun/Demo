package me.liwenkun.demo.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import me.liwenkun.demo.R;


public class ViewAnimationActivity extends AppCompatActivity {

    public Button btnStartAnimation;
    public ImageView ivPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);


        ivPicture = ((ImageView) findViewById(R.id.iv_picture));
        //ivPicture.setVisibility(View.INVISIBLE);

        btnStartAnimation = (Button) findViewById(R.id.btn_start_animation);
        btnStartAnimation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ivPicture.setVisibility(View.VISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    int cx = (ivPicture.getLeft() + ivPicture.getRight()) / 2;
                    int cy = (ivPicture.getTop() + ivPicture.getBottom()) / 2;

                    int d = Math.max(ivPicture.getWidth(), ivPicture.getHeight());


                    Animator animator = ViewAnimationUtils.createCircularReveal(ivPicture, cx, cy, 0,  d );
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            ivPicture.setVisibility(View.INVISIBLE);
                        }
                    });
                    animator.start();

                }




            }
        });
    }


}
