package com.vuforia.samples.VuforiaSamples.app.MapDirection;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.vuforia.CameraDevice;
import com.vuforia.INIT_FLAGS;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.RectangleInt;
import com.vuforia.Renderer;
import com.vuforia.State;
import com.vuforia.TextTracker;
import com.vuforia.Tracker;
import com.vuforia.TrackerManager;
import com.vuforia.VideoBackgroundConfig;
import com.vuforia.VideoMode;
import com.vuforia.Vuforia;
import com.vuforia.samples.SampleApplication.SampleApplicationControl;
import com.vuforia.samples.SampleApplication.SampleApplicationException;
import com.vuforia.samples.SampleApplication.SampleApplicationSession;
import com.vuforia.samples.SampleApplication.utils.LoadingDialogHandler;
import com.vuforia.samples.SampleApplication.utils.SampleApplicationGLView;
import com.vuforia.samples.SampleApplication.utils.SampleUtils;
import com.vuforia.samples.VuforiaSamples.R;
import com.vuforia.samples.VuforiaSamples.app.TextRecognition.TextReco;
import com.vuforia.samples.VuforiaSamples.app.TextRecognition.TextRecoRenderer;
import com.vuforia.samples.VuforiaSamples.ui.SampleAppMenu.SampleAppMenu;
import com.vuforia.samples.VuforiaSamples.ui.SampleAppMenu.SampleAppMenuGroup;
import com.vuforia.samples.VuforiaSamples.ui.SampleAppMenu.SampleAppMenuInterface;

import java.util.ArrayList;

public class MapDirection extends Activity implements SampleApplicationControl,
        SampleAppMenuInterface {

    private static final String LOGTAG = "MapDirection";

    SampleApplicationSession vuforiaAppSession;

    private final static int COLOR_OPAQUE = Color.argb(178, 0, 0, 0);

    // Our OpenGL view:
    private SampleApplicationGLView mGlView;

    // My renderer:
    private MapDirectionRenderer mRenderer;

    private SampleAppMenu mSampleAppMenu;

    private ArrayList<View> mSettingsAdditionalViews;

    private RelativeLayout mUILayout;

    private LoadingDialogHandler loadingDialogHandler = new LoadingDialogHandler(this);

    private boolean mIsTablet = false;

    private boolean mIsVuforiaStarted = false;

    // Alert Dialog used to display SDK errors
    private AlertDialog mErrorDialog;

    // TODO: warning mCamera
    private int mCamera = CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_DEFAULT;

    // Flags
    private boolean mCameraRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(LOGTAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_direction);

        vuforiaAppSession = new SampleApplicationSession(this);

        startLoadingAnimation();
        Log.d("LoadingAnimation", "Done");

        vuforiaAppSession.initAR(this, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /*
        // Initialize vuforia
        int mProgressValue;
        Vuforia.setInitParameters(this, INIT_FLAGS.GL_20, "AVsTSZT/////AAADmY/ipgqeY0aJuktn/nwT08tHQCXKaoAV8QXosNVJJlYZEVma1zjmzP3ifnyWWy4e4GS6LvbbOAe9+zj5NrtueuVZXlI67tSxf5T2TTKP2D33G/aGfJNNVOOalNVEvPdQhnP+MnG7mO3rjVXfOEkvWERf2a349VEgldTud1Ok15AcoOg7PmuOrVbPr6hhRXnl3uArDHISX9roAVlwVsjr9eO05KbEKgIcCF0LsPiEnVZzLfHStdiEhW7vZwh6QN+bxGzjHL2RvtAH+R4oNgSqU2ZRkBNK0f/eiQWBZWou0Rpvbq81c35zqZ00SZMTj54Ar5k2g7edVEAoUV+Fhp1P+hm25ZAcePJM2GbZgLe8jApk");
        do
        {
            mProgressValue = Vuforia.init();
        }
        while (mProgressValue >= 0 && mProgressValue < 100);
        Log.d("Progress Bar","Vuforia initialized");

        // Initializing the trackers
        TrackerManager tManager = TrackerManager.getInstance();
        Tracker tracker = tManager.initTracker(TextTracker.getClassType());

        // Tracker data loading

        initApplicationAR();
        Log.d("Init", "AR Done");

        CameraDevice.getInstance().init(mCamera);

        // TODO configureVideoBackground();

        CameraDevice.getInstance().selectVideoMode(
                CameraDevice.MODE.MODE_DEFAULT);

        CameraDevice.getInstance().start();

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
*/
       // onInitARDone(null);
    }

    @Override
    protected void onPause() {
        Log.d(LOGTAG, "onPause");
        super.onPause();

        stopCamera();

        if (mGlView != null)
        {
            mGlView.setVisibility(View.INVISIBLE);
            mGlView.onPause();
        }

        Vuforia.onPause();
    }

    @Override
    protected void onResume()
    {
        Log.d(LOGTAG, "onResume");
        super.onResume();

        showProgressIndicator(true);

        vuforiaAppSession.onResume();
      /*  Vuforia.onResume();

        if (mGlView != null)
        {
            mGlView.setVisibility(View.VISIBLE);
            mGlView.onResume();
        }

        try {
            startCamera(mCamera);
        } catch (SampleApplicationException e) {
            Log.e(LOGTAG, "StartCamera: Could not start AR with exception: " + e);
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onDestroy()
    {
        Log.d(LOGTAG, "onDestroy");
        super.onDestroy();

        try
        {
            vuforiaAppSession.stopAR();
        } catch (SampleApplicationException e)
        {
            Log.e(LOGTAG, e.getString());
        }

        System.gc();
/*
        //doStopTrackers();
        stopCamera();

        //deinitTracker();

        Vuforia.deinit();*/
    }

    private void startLoadingAnimation()
    {
        LayoutInflater inflater = LayoutInflater.from(this);
        mUILayout = (RelativeLayout) inflater.inflate(
                R.layout.camera_overlay_textreco, null, false);

        mUILayout.setVisibility(View.VISIBLE);
        mUILayout.setBackgroundColor(Color.BLACK);

        // Gets a reference to the loading dialog
        loadingDialogHandler.mLoadingDialogContainer = mUILayout
                .findViewById(R.id.loading_indicator);

        // Shows the loading indicator at start
        loadingDialogHandler
                .sendEmptyMessage(LoadingDialogHandler.SHOW_LOADING_DIALOG);

        // Adds the inflated layout to the view
        addContentView(mUILayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

    }

    // Initializes AR application components.
    private void initApplicationAR()
    {
        // Create OpenGL ES view:
        int depthSize = 16;
        int stencilSize = 0;
        boolean translucent = Vuforia.requiresAlpha();

        mGlView = new SampleApplicationGLView(this);
        mGlView.init(translucent, depthSize, stencilSize);

        mRenderer = new MapDirectionRenderer(this, vuforiaAppSession);
        mGlView.setRenderer(mRenderer);

        showLoupe(false);
    }

    private void postStartCamera()
    {
        // Sets the layout background to transparent
        mUILayout.setBackgroundColor(Color.TRANSPARENT);
        Log.d("postStartCamera", "set background color transparent");

        // start the image tracker now that the camera is started
        /*Tracker t = TrackerManager.getInstance().getTracker(
                TextTracker.getClassType());
        if (t != null)
            t.start();
*/
      //  configureVideoBackgroundROI();
    }

    void configureVideoBackgroundROI()
    {
        VideoMode vm = CameraDevice.getInstance().getVideoMode(
                CameraDevice.MODE.MODE_DEFAULT);
        VideoBackgroundConfig config = Renderer.getInstance()
                .getVideoBackgroundConfig();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;
        int screenHeight = metrics.heightPixels;

        {
            // calc ROI
            // width of margin is :
            // 5% of the width of the screen for a phone
            // 20% of the width of the screen for a tablet
            int marginWidth = mIsTablet ? (screenWidth * 20) / 100
                    : (screenWidth * 5) / 100;

            // loupe height is :
            // 15% of the screen height for a phone
            // 10% of the screen height for a tablet
            int loupeHeight = mIsTablet ? (screenHeight * 10) / 100
                    : (screenHeight * 15) / 100;

            // lupue width takes the width of the screen minus 2 margins
            int loupeWidth = screenWidth - (2 * marginWidth);

            // definition of the region of interest
            mRenderer.setROI(screenWidth / 2, marginWidth + (loupeHeight / 2),
                    loupeWidth, loupeHeight);
        }

        // Get the camera rotation
        int cameraDirection;
        switch (CameraDevice.getInstance().getCameraDirection()) {
            case CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_BACK:
                cameraDirection = Camera.CameraInfo.CAMERA_FACING_BACK;
                break;
            case CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_FRONT:
                cameraDirection = Camera.CameraInfo.CAMERA_FACING_FRONT;
                break;
            default:
                cameraDirection = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        int cameraRotation = 0;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == cameraDirection) {
                cameraRotation = cameraInfo.orientation;
                break;
            }
        }

        // Get the display rotation
        Display display = ((WindowManager)this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int displayRotation = display.getRotation();

        // convert into camera coords
        int[] loupeCenterX = { 0 };
        int[] loupeCenterY = { 0 };
        int[] loupeWidth = { 0 };
        int[] loupeHeight = { 0 };
        SampleUtils.screenCoordToCameraCoord((int) mRenderer.ROICenterX,
                (int) mRenderer.ROICenterY, (int) mRenderer.ROIWidth,
                (int) mRenderer.ROIHeight, screenWidth, screenHeight,
                vm.getWidth(), vm.getHeight(), loupeCenterX, loupeCenterY,
                loupeWidth, loupeHeight, displayRotation, cameraRotation);

        // Compute the angle by which the camera image should be rotated clockwise so that it is
        // shown correctly on the display given its current orientation.
        int correctedRotation = ((((displayRotation*90)-cameraRotation)+360)%360)/90;

        int upDirection;
        switch (correctedRotation){
            case 0:
                upDirection = TextTracker.UP_DIRECTION.REGIONOFINTEREST_UP_IS_0_HRS;
                break;
            case 1:
                upDirection = TextTracker.UP_DIRECTION.REGIONOFINTEREST_UP_IS_3_HRS;
                break;
            case 2:
                upDirection = TextTracker.UP_DIRECTION.REGIONOFINTEREST_UP_IS_6_HRS;
                break;
            case 3:
                upDirection = TextTracker.UP_DIRECTION.REGIONOFINTEREST_UP_IS_9_HRS;
                break;
            default:
                upDirection = TextTracker.UP_DIRECTION.REGIONOFINTEREST_UP_IS_9_HRS;
        }

        RectangleInt detROI = new RectangleInt(loupeCenterX[0]
                - (loupeWidth[0] / 2), loupeCenterY[0] - (loupeHeight[0] / 2),
                loupeCenterX[0] + (loupeWidth[0] / 2), loupeCenterY[0]
                + (loupeHeight[0] / 2));

        TextTracker tt = (TextTracker) TrackerManager.getInstance().getTracker(
                TextTracker.getClassType());

        if (tt != null)
            tt.setRegionOfInterest(detROI, detROI,
                    upDirection);

        mRenderer.setViewport(0, 0, metrics.widthPixels, metrics.heightPixels);
    }

    private void stopCamera()
    {
        //doStopTrackers();

        CameraDevice.getInstance().stop();
        CameraDevice.getInstance().deinit();
    }

    /*@Override
    public boolean doStopTrackers()
    {
        // Indicate if the trackers were stopped correctly
        boolean result = true;

        Tracker textTracker = TrackerManager.getInstance().getTracker(
                TextTracker.getClassType());
        if (textTracker != null)
            textTracker.stop();

        return result;
    }*/

    private void startCamera(int camera) throws SampleApplicationException
    {
        String error;
        if(mCameraRunning)
        {
            error = "Camera already running, unable to open again";
            Log.e(LOGTAG, error);
            throw new SampleApplicationException(
                    SampleApplicationException.CAMERA_INITIALIZATION_FAILURE, error);
        }

        mCamera = camera;
        if (!CameraDevice.getInstance().init(camera))
        {
            error = "Unable to open camera device: " + camera;
            Log.e(LOGTAG, error);
            throw new SampleApplicationException(
                    SampleApplicationException.CAMERA_INITIALIZATION_FAILURE, error);
        }

        if (!CameraDevice.getInstance().selectVideoMode(
                CameraDevice.MODE.MODE_DEFAULT))
        {
            error = "Unable to set video mode";
            Log.e(LOGTAG, error);
            throw new SampleApplicationException(
                    SampleApplicationException.CAMERA_INITIALIZATION_FAILURE, error);
        }

        if (!CameraDevice.getInstance().start())
        {
            error = "Unable to start camera device: " + camera;
            Log.e(LOGTAG, error);
            throw new SampleApplicationException(
                    SampleApplicationException.CAMERA_INITIALIZATION_FAILURE, error);
        }

        mCameraRunning = true;
    }

    @Override
    public boolean doInitTrackers() {
        return true;
    }

    @Override
    public boolean doLoadTrackersData() {
        return true;
    }

    @Override
    public boolean doStartTrackers() {
        return true;
    }

    @Override
    public boolean doStopTrackers() {
        return true;
    }

    @Override
    public boolean doUnloadTrackersData() {
        return true;
    }

    @Override
    public boolean doDeinitTrackers() {
        return true;
    }

    @Override
    public void onInitARDone(SampleApplicationException exception) {
        Log.d("onInitARDone", "enter");
        if (exception == null)
        {
            initApplicationAR();

            // Hint to the virtual machine that it would be a good time to
            // run the garbage collector:
            //
            // NOTE: This is only a hint. There is no guarantee that the
            // garbage collector will actually be run.
            System.gc();

            mRenderer.setActive(true);

            // Now add the GL surface view. It is important
            // that the OpenGL ES surface view gets added
            // BEFORE the camera is started and video
            // background is configured.
            addContentView(mGlView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));


            // Hides the Loading Dialog
            loadingDialogHandler
                    .sendEmptyMessage(LoadingDialogHandler.HIDE_LOADING_DIALOG);
            showLoupe(true);

            // Sets the UILayout to be drawn in front of the camera
            mUILayout.bringToFront();

            vuforiaAppSession.startAR(CameraDevice.CAMERA_DIRECTION.CAMERA_DIRECTION_DEFAULT);
            Log.d("onInitARDone", "startAR complete");

            mIsVuforiaStarted = true;

            postStartCamera();

            setSampleAppMenuAdditionalViews();
            mSampleAppMenu = new SampleAppMenu(this, this, "Text Reco",
                    mGlView, mUILayout, mSettingsAdditionalViews);
            setSampleAppMenuSettings();

        } else
        {
            Log.e(LOGTAG, exception.getString());
            showInitializationErrorMessage(exception.getString());
        }
    }

    @Override
    public void onVuforiaUpdate(State state) {
    }

    @Override
    public void onVuforiaResumed() {

        if (mGlView != null)
        {
            mGlView.setVisibility(View.VISIBLE);
            mGlView.onResume();
        }
    }

    @Override
    public void onVuforiaStarted() {

        // Set camera focus mode
        if(!CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_CONTINUOUSAUTO))
        {
            // If continuous autofocus mode fails, attempt to set to a different mode
            if(!CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_TRIGGERAUTO))
            {
                CameraDevice.getInstance().setFocusMode(CameraDevice.FOCUS_MODE.FOCUS_MODE_NORMAL);
            }
        }

        showProgressIndicator(false);
    }

    public void showProgressIndicator(boolean show)
    {
        if (loadingDialogHandler != null)
        {
            if (show)
            {
                loadingDialogHandler
                        .sendEmptyMessage(LoadingDialogHandler.SHOW_LOADING_DIALOG);
            }
            else
            {
                loadingDialogHandler
                        .sendEmptyMessage(LoadingDialogHandler.HIDE_LOADING_DIALOG);
            }
        }
    }


    @Override
    public boolean menuProcess(int command) {
        return false;
    }

    private void showLoupe(boolean isActive)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        // width of margin is :
        // 5% of the width of the screen for a phone
        // 20% of the width of the screen for a tablet
        int marginWidth = mIsTablet ? (width * 20) / 100 : (width * 5) / 100;

        // loupe height is :
        // 33% of the screen height for a phone
        // 20% of the screen height for a tablet
        int loupeHeight = mIsTablet ? (height * 10) / 100 : (height * 15) / 100;

        // lupue width takes the width of the screen minus 2 margins
        int loupeWidth = width - (2 * marginWidth);

        int wordListHeight = height - (loupeHeight + marginWidth);

        // definition of the region of interest
        mRenderer.setROI(width / 2, marginWidth + (loupeHeight / 2),
                loupeWidth, loupeHeight);

        // Gets a reference to the loading dialog
        View loadingIndicator = mUILayout.findViewById(R.id.loading_indicator);

        RelativeLayout loupeLayout = (RelativeLayout) mUILayout
                .findViewById(R.id.loupeLayout);

        ImageView topMargin = (ImageView) mUILayout
                .findViewById(R.id.topMargin);

        ImageView leftMargin = (ImageView) mUILayout
                .findViewById(R.id.leftMargin);

        ImageView rightMargin = (ImageView) mUILayout
                .findViewById(R.id.rightMargin);

        ImageView loupeArea = (ImageView) mUILayout.findViewById(R.id.loupe);

        RelativeLayout wordListLayout = (RelativeLayout) mUILayout
                .findViewById(R.id.wordList);

        wordListLayout.setBackgroundColor(COLOR_OPAQUE);

        if (isActive)
        {
            topMargin.getLayoutParams().height = marginWidth;
            topMargin.getLayoutParams().width = width;

            leftMargin.getLayoutParams().width = marginWidth;
            leftMargin.getLayoutParams().height = loupeHeight;

            rightMargin.getLayoutParams().width = marginWidth;
            rightMargin.getLayoutParams().height = loupeHeight;

            RelativeLayout.LayoutParams params;

            params = (RelativeLayout.LayoutParams) loupeLayout
                    .getLayoutParams();
            params.height = loupeHeight;
            loupeLayout.setLayoutParams(params);

            loupeArea.getLayoutParams().width = loupeWidth;
            loupeArea.getLayoutParams().height = loupeHeight;
            loupeArea.setVisibility(View.VISIBLE);

            params = (RelativeLayout.LayoutParams) wordListLayout
                    .getLayoutParams();
            params.height = wordListHeight;
            params.width = width;
            wordListLayout.setLayoutParams(params);

            loadingIndicator.setVisibility(View.GONE);
            loupeArea.setVisibility(View.VISIBLE);
            topMargin.setVisibility(View.VISIBLE);
            loupeLayout.setVisibility(View.VISIBLE);
            wordListLayout.setVisibility(View.VISIBLE);

        } else
        {
            loadingIndicator.setVisibility(View.VISIBLE);
            loupeArea.setVisibility(View.GONE);
            topMargin.setVisibility(View.GONE);
            loupeLayout.setVisibility(View.GONE);
            wordListLayout.setVisibility(View.GONE);
        }

    }

    // This method sets the additional views to be moved along with the GLView
    private void setSampleAppMenuAdditionalViews()
    {
        mSettingsAdditionalViews = new ArrayList<View>();
        mSettingsAdditionalViews.add(mUILayout.findViewById(R.id.topMargin));
        mSettingsAdditionalViews.add(mUILayout.findViewById(R.id.loupeLayout));
        mSettingsAdditionalViews.add(mUILayout.findViewById(R.id.wordList));
    }

    // This method sets the menu's settings
    private void setSampleAppMenuSettings()
    {
        SampleAppMenuGroup group;

        group = mSampleAppMenu.addGroup("", false);
        group.addTextItem(getString(R.string.menu_back), -1);

        mSampleAppMenu.attachMenu();
    }

    // Shows initialization error messages as System dialogs
    public void showInitializationErrorMessage(String message)
    {
        final String errorMessage = message;
        runOnUiThread(new Runnable()
        {
            public void run()
            {
                if (mErrorDialog != null)
                {
                    mErrorDialog.dismiss();
                }

                // Generates an Alert Dialog to show the error message
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        MapDirection.this);
                builder
                        .setMessage(errorMessage)
                        .setTitle(getString(R.string.INIT_ERROR))
                        .setCancelable(false)
                        .setIcon(0)
                        .setPositiveButton(getString(R.string.button_OK),
                                new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialog, int id)
                                    {
                                        finish();
                                    }
                                });

                mErrorDialog = builder.create();
                mErrorDialog.show();
            }
        });
    }
}
