package com.vuforia.samples.VuforiaSamples.app.MapDirection;

import android.opengl.GLSurfaceView;

import com.vuforia.Device;
import com.vuforia.State;
import com.vuforia.samples.SampleApplication.SampleAppRenderer;
import com.vuforia.samples.SampleApplication.SampleAppRendererControl;
import com.vuforia.samples.SampleApplication.SampleApplicationSession;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapDirectionRenderer implements GLSurfaceView.Renderer, SampleAppRendererControl {

    private static final String LOGTAG = "TextRecoRenderer";

    private SampleApplicationSession vuforiaAppSession;
    private SampleAppRenderer mSampleAppRenderer;

    private boolean mIsActive = false;

    // Reference to main activity *
    public MapDirection mActivity;

    public float ROICenterX;
    public float ROICenterY;
    public float ROIWidth;
    public float ROIHeight;
    private int viewportPosition_x;
    private int viewportPosition_y;
    private int viewportSize_x;
    private int viewportSize_y;

    public MapDirectionRenderer(MapDirection activity, SampleApplicationSession session)
    {
        mActivity = activity;
        vuforiaAppSession = session;

        // SampleAppRenderer used to encapsulate the use of RenderingPrimitives setting
        // the device mode AR/VR and stereo mode
        mSampleAppRenderer = new SampleAppRenderer(this, mActivity, Device.MODE.MODE_AR, false, 10f, 5000f);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }

    @Override
    public void onDrawFrame(GL10 gl) {
    }

    @Override
    public void renderFrame(State state, float[] projectionMatrix) {
    }

    public void setActive(boolean active)
    {
        mIsActive = active;

        if(mIsActive)
            mSampleAppRenderer.configureVideoBackground();
    }

    public void setROI(float center_x, float center_y, float width, float height)
    {
        ROICenterX = center_x;
        ROICenterY = center_y;
        ROIWidth = width;
        ROIHeight = height;
    }

    public void setViewport(int vpX, int vpY, int vpSizeX, int vpSizeY)
    {
        viewportPosition_x = vpX;
        viewportPosition_y = vpY;
        viewportSize_x = vpSizeX;
        viewportSize_y = vpSizeY;
    }
}
