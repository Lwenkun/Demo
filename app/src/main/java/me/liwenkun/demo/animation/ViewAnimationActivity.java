package me.liwenkun.demo.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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
        ivPicture.setCameraDistance(0f);
        //ivPicture.setVisibility(View.INVISIBLE);

        btnStartAnimation = (Button) findViewById(R.id.btn_start_animation);

        btnStartAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation a = new Rotate3dAnimation(0f, 360f, 0.5f, 0.5f, 20f, false);
                ivPicture.startAnimation(a);

            }
        });

//        btnStartAnimation.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                ivPicture.setVisibility(View.VISIBLE);
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//                    int cx = (ivPicture.getLeft() + ivPicture.getRight()) / 2;
//                    int cy = (ivPicture.getTop() + ivPicture.getBottom()) / 2;
//
//                    int d = Math.max(ivPicture.getWidth(), ivPicture.getHeight());
//
//
//                    Animator animator = ViewAnimationUtils.createCircularReveal(ivPicture, cx, cy, 0,  d );
//                    animator.addListener(new AnimatorListenerAdapter() {
//                        @Override
//                        public void onAnimationEnd(Animator animation) {
//                            ivPicture.setVisibility(View.INVISIBLE);
//                        }
//                    });
//                    animator.start();
//
//                }
//
//            }
//        });
    }


    public class Rotate3dAnimation extends Animation {
        private final float mFromDegrees;
        private final float mToDegrees;
        private final float mCenterX;
        private final float mCenterY;
        private final float mDepthZ;
        private final boolean mReverse;
        private Camera mCamera;
        /**
         * Creates a new 3D rotation on the Y axis. The rotation is defined by its
         * start angle and its end angle. Both angles are in degrees. The rotation
         * is performed around a center point on the 2D space, definied by a pair
         * of X and Y coordinates, called centerX and centerY. When the animation
         * starts, a translation on the Z axis (depth) is performed. The length
         * of the translation can be specified, as well as whether the translation
         * should be reversed in time.
         *
         * @param fromDegrees the start angle of the 3D rotation
         * @param toDegrees the end angle of the 3D rotation
         * @param centerX the X center of the 3D rotation
         * @param centerY the Y center of the 3D rotation
         * @param reverse true if the translation should be reversed, false otherwise
         */
        public Rotate3dAnimation(float fromDegrees, float toDegrees,
                                 float centerX, float centerY, float depthZ, boolean reverse) {
            mFromDegrees = fromDegrees;
            mToDegrees = toDegrees;
            mCenterX = centerX;
            mCenterY = centerY;
            mDepthZ = depthZ;
            mReverse = reverse;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(3000);
            mCamera = new Camera();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            final float fromDegrees = mFromDegrees;
            float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
            final float centerX = mCenterX;
            final float centerY = mCenterY;
            final Camera camera = mCamera;
            final Matrix matrix = t.getMatrix();
            matrix.setRotate(degrees);
//            camera.save();
//            if (mReverse) {
//               // camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
//            } else {
//                camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
//            }
//            camera.rotateY(degrees);
//            camera.getMatrix(matrix);
//            camera.restore();
           // matrix.preTranslate(-centerX, -centerY);
           // matrix.postTranslate(centerX, centerY);
        }
    }


}
