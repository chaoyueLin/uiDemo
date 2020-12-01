package com.example.renderthreaddemo;

import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/*****************************************************************
 * * File: - AsyncAnimHelper
 * * Description: 
 * * Version: 1.0
 * * Date : 2020/11/23
 * * Author: linchaoyue
 * *
 * * ---------------------- Revision History:----------------------
 * * <author>   <date>     <version>     <desc>
 * * linchaoyue 2020/11/23    1.0         create
 ******************************************************************/
public class AsyncAnimHelper {
    private static Object createViewPropertyAnimatorRT(View view) {
        try {
            final Class<?> animRtCalzz = Class.forName("android.view.ViewPropertyAnimatorRT");
            Constructor constructor=animRtCalzz.getConstructor(View.class);
            Log.d("AsyncAnimHelper","constructor.length="+constructor);
//            final Constructor<?> animRtConstructor = animRtCalzz.getDeclaredConstructor(View.class);
//            animRtConstructor.setAccessible(true);
//            return animRtConstructor.newInstance(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void setViewPropertyAnimatorRT(ViewPropertyAnimator animator, Object rt) {
//        try {
//            final Class<?> animClazz = Class.forName("android.view.ViewPropertyAnimator");
//            final Field animRtField = animClazz.getDeclaredField("mRTBackend");
//            animRtField.setAccessible(true);
//            animRtField.set(animator, rt);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public static void onStartBefore(ViewPropertyAnimator animator, View view) {
       createViewPropertyAnimatorRT(view);
    }
}
