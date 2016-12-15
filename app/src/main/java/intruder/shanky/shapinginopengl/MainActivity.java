package intruder.shanky.shapinginopengl;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {
GLSurfaceView glSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glSurfaceView= (GLSurfaceView) findViewById(R.id.gl_surface_view);
        //supports OpenGL ES 2.0
        glSurfaceView.setEGLContextClientVersion(2);

        glSurfaceView.setRenderer(new GLRenderer(this));

        // GLSurfaceView renders its contents only when necessary
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public void changeEffect(View view) {
        switch (view.getId()) {
            case R.id.grayscale:
                GLRenderer.EFFECT_NUM = 0;
                break;
            case R.id.blackwhite:
                GLRenderer.EFFECT_NUM = 1;
                break;
            case R.id.contrast:
                GLRenderer.EFFECT_NUM = 2;
                break;
            case R.id.lomoish:
                GLRenderer.EFFECT_NUM = 3;
                break;
            case R.id.documentry:
                GLRenderer.EFFECT_NUM = 4;
                break;
            case R.id.crossprocess:
                GLRenderer.EFFECT_NUM = 5;
                break;
        }
        glSurfaceView.requestRender();

    }
}
