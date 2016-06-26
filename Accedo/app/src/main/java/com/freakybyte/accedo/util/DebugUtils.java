package com.freakybyte.accedo.util;

import android.util.Log;

import com.freakybyte.accedo.BuildConfig;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
public class DebugUtils {
    private static DebugUtils mSingleton;

    public static final boolean DEBUG = BuildConfig.DEBUG;
    private static final boolean LOG_TO_FILE = false;
    private static final String TAG = "Accedo";

    public static DebugUtils getSingleton() {
        if (mSingleton == null)
            mSingleton = new DebugUtils();
        return mSingleton;
    }

    public void logInfo(String sText) {
        if (DEBUG)
            Log.i(TAG, String.valueOf(sText));
    }

    public void logInfo(String method, String sText) {
        if (DEBUG)
            Log.i(String.valueOf(method), String.valueOf(sText));
    }

    public void logInfo(String sClass, String method, String sText) {
        if (DEBUG)
            Log.i(sClass + " :: " + String.valueOf(method), String.valueOf(sText));
    }

    public void logError(Exception e) {
        if (DEBUG && e != null)
            Log.e(TAG, e.getMessage());
    }

    public void logError(String sText) {
        if (DEBUG && sText != null)
            Log.e(TAG, String.valueOf(sText));
    }

    public void logError(String method, String sText) {
        if (DEBUG && method != null && sText != null)
            Log.e(String.valueOf(method), String.valueOf(sText));
    }

    public void logError(String method, Exception e) {
        if (DEBUG && method != null && e != null)
            Log.e(String.valueOf(method), e.getMessage());
    }

    public void logError(String sClass, String method, String sText) {
        if (DEBUG && method != null && sText != null)
            Log.e(sClass + " :: " + String.valueOf(method), String.valueOf(sText));
    }

    public void logError(String sClass, String method, Exception ex) {
        if (DEBUG && method != null && ex != null)
            Log.e(sClass + " :: " + String.valueOf(method), ex.getMessage());
    }

    public void logDebug(String sText) {
        if (DEBUG && sText != null)
            Log.d(TAG, String.valueOf(sText));
    }

    public void logDebug(String method, String sText) {
        if (DEBUG && method != null && sText != null)
            Log.d(String.valueOf(method), String.valueOf(sText));
    }

    public void logDebug(String method, Object obj) {
        if (DEBUG && method != null && obj != null)
            Log.d(String.valueOf(method), String.valueOf(obj));
    }

    public void logDebug(String sClass, String method, String sText) {
        if (DEBUG && sClass != null && method != null && sText != null)
            Log.d(sClass + " :: " + String.valueOf(method), String.valueOf(sText));
    }


    public void logDebug(long sText) {
        if (DEBUG)
            Log.d(TAG, String.valueOf(sText));
    }

    public void logDebug(String method, long sText) {
        if (DEBUG)
            Log.d(String.valueOf(method), String.valueOf(sText));
    }

    public void logThrowable(Throwable e) {
        if (DEBUG) {
            Log.e(TAG, e.toString());
            e.printStackTrace();
            if (LOG_TO_FILE) {
                logToFile(e);
            }
        }
    }

    public void logToFile(Throwable e) {
        try {
            StringBuffer sb = new StringBuffer(e.toString() + "\n");
            StackTraceElement[] stElements = e.getStackTrace();
            String newLine = "";

            for (StackTraceElement stElement : stElements) {
                sb.append(newLine);
                sb.append("\tat ");
                sb.append(stElement.toString());
                newLine = "\n";
            }
        } catch (Exception ee) {
            e.getMessage();
        }
    }


}
