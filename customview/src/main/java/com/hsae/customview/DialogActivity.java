package com.hsae.customview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class DialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.TOP|Gravity.LEFT;
        params.x = 22;
        params.y = 22;
        //        params.height = 500;
        //        params.width = 300;
        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setAttributes(params);

        setContentView(R.layout.activity_dialog);

        getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL);
        getWindow().setLayout(1280, 720);





//               mUIPresenterManager =new UIPresenterManager(this, (LinearLayout) findViewById(R.id.root), getIntent(), savedInstanceState);
//               mUIPresenterManager.onCreate(savedInstanceState);
//               ivCloseApp = findViewById(R.id.ivCloseApp);
    }


}
