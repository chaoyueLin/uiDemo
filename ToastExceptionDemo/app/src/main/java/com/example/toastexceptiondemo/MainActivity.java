package com.example.toastexceptiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast toast=Toast.makeText(this, "哈哈哈", Toast.LENGTH_SHORT);
        //注销下面会再25机型上crash
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            try {
                /**
                 * 获取mTN对象
                 * 并获取它的class类型
                 */
                Class<Toast> clazzToast = Toast.class;
                Field fieldTN = clazzToast.getDeclaredField("mTN");
                fieldTN.setAccessible(true);
                Object objTn = fieldTN.get(toast);
                Class clazzTn = objTn.getClass();
                /**
                 * 获取TN中的mHandler对象
                 * 然后用我们自定义的HandlerProxy类包裹它
                 * 使得它能捕获异常
                 */
                Field fieldHandler = clazzTn.getDeclaredField("mHandler");
                fieldHandler.setAccessible(true);
                fieldHandler.set(objTn, new HandlerProxy((Handler) fieldHandler.get(objTn)));
            } catch (Throwable throwable) {
                Log.e(TAG, "hack toast handler error: " + throwable.getMessage());
            }

        }
        toast.show();
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "哈哈哈哈哈哈哈", Toast.LENGTH_SHORT).show();
    }

    private static class HandlerProxy extends Handler {
        private Handler mHandler;

        public HandlerProxy(Handler handler) {
            this.mHandler = handler;
        }

        @Override
        public void handleMessage(Message msg) {
            try {
                mHandler.handleMessage(msg);
            } catch (Throwable throwable) {
                Log.e(TAG, "toast error: " + throwable.getMessage());
            }
        }
    }
}