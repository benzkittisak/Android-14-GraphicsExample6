package kp.enterprise.graphicsexample6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {

    private Paint p1;
    private Bitmap image;
    private int xPic, yPic, x1, y1;
    private int speedXPic, speedYPic, speedX1, speedY1;
    private int size;
    private boolean animate = false;
    private int Width = 1080;
    private int Height = 1500;
    CountDownTimer timer;
    public GraphicsView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        p1 = new Paint();
        p1.setColor(Color.RED);
        p1.setStyle(Paint.Style.FILL);
        image = BitmapFactory.decodeResource( getResources(),

                R.drawable.robot);

        xPic = yPic = 500;
        x1 = y1 = 200;
        size = 50;
        speedXPic = speedYPic = 5;
        speedX1 = speedY1 = -4;
        timer = new CountDownTimer(20000, 50) {
            public void onTick(long millisUntilFinished) {
                xPic += speedXPic;
                if (xPic < 1) speedXPic = -speedXPic;
                else if (xPic+size > Width) speedXPic = -speedXPic;
                yPic += speedYPic;
                if (yPic < 1) speedYPic = -speedYPic;
                else if (yPic+size > Height) speedYPic = -speedYPic;
                x1 += speedX1;
                if (x1 < 1) speedX1 = -speedX1;
                else if (x1+size > Width) speedX1 = -speedX1;
                y1 += speedY1;
                if (y1 < 1) speedY1 = -speedY1;
                else if (y1+size > Height) speedY1 = -speedY1;
                invalidate();
            }
            public void onFinish() {
                animate = false;
            }
        };
    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (!animate) {
            timer.start();
            animate = true;
        }
        invalidate();
        return(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
// draw rectangle
        canvas.drawRect( x1, y1 , x1 + size , y1 + size , p1);
// draw picture
        canvas.drawBitmap( image, xPic, yPic, null);
    }
}
