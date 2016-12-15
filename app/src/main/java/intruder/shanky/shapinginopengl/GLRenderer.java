package intruder.shanky.shapinginopengl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.effect.Effect;
import android.media.effect.EffectContext;
import android.media.effect.EffectFactory;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.provider.Settings;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Intruder Shanky on 12/15/2016.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private Bitmap photo;
    private static int photoWidth, photoHeight;
    private int textures[] = new int[2];
    private Shape shape;
    private EffectContext effectContext;
    private Effect effect;
    public static int EFFECT_NUM = 0;

    public GLRenderer(Context context) {
        super();
        photo = BitmapFactory.decodeResource(context.getResources(), R.drawable.songwriter);
        photoWidth = photo.getWidth();
        photoHeight = photo.getHeight();
    }

    private void generateSquare() {
        GLES20.glGenTextures(2, textures, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[0]);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, photo, 0);
        shape = new Shape();
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        GLES20.glClearColor(0, 0, 0, 1);
        generateSquare();
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        if (effectContext == null) {
            effectContext = EffectContext.createWithCurrentGlContext();
        }
        System.out.println(photoHeight+"  dimen1  "+photoWidth);
        if(effect!=null){
            effect.release();
        }
        drawEffect();
        shape.draw(textures[1]);
    }

    private void drawEffect() {
        EffectFactory factory = effectContext.getFactory();
        switch (EFFECT_NUM) {
            case 0:
                effect = factory.createEffect(EffectFactory.EFFECT_GRAYSCALE);
                break;
            case 1:
                effect = factory.createEffect(EffectFactory.EFFECT_BLACKWHITE);
                break;
            case 2:
                effect = factory.createEffect(EffectFactory.EFFECT_CONTRAST);
                break;
            case 3:
                effect = factory.createEffect(EffectFactory.EFFECT_LOMOISH);
                break;
            case 4:
                effect = factory.createEffect(EffectFactory.EFFECT_DOCUMENTARY);
                break;
            case 5:
                effect = factory.createEffect(EffectFactory.EFFECT_CROSSPROCESS);
                break;
        }
        effect.apply(textures[0], photoWidth, photoHeight, textures[1]);
    }
}
