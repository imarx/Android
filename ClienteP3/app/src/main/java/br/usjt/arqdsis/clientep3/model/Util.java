package br.usjt.arqdsis.clientep3.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/**
 * Created by asbonato on 9/19/16.
 */
public class Util {
    public static Drawable getDrawable(Activity context, String drawableName){
        /*
        //procurar a imagem por meio de reflection
        Class<?> c = R.drawable.class;
        try {
            Field idField = c.getDeclaredField(drawableName);
            int id = idField.getInt(idField);
            return context.getResources().getDrawable(id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        */

        ShapeDrawable mDrawable;
        final int DRKGREEN = 0xff74AC23;
        int x = 0;
        int y = 0;
        int width = 70;
        int height = 70;
        float[] outerRadii = {10f, 10f, 10f, 10f, 10f, 10f, 10f, 10f };
        int[] color = {Color.MAGENTA, DRKGREEN, Color.BLUE, Color.RED };

        mDrawable = new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
       // mDrawable.getPaint().setColor(0xff74AC23);
        mDrawable.getPaint().setColor(color[(int)(drawableName.charAt(0))%color.length]);
        mDrawable.setBounds(x, y, x + width, y + height);
        Bitmap b = Bitmap.createBitmap(70, 70, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        mDrawable.draw(c);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(40);
        paint.setTypeface(Typeface.MONOSPACE);
        float text_x = 10;
        float text_y = 50;
        c.drawText(drawableName, text_x, text_y, paint);


        return new BitmapDrawable(context.getResources(), b);
    }


}
