package com.freakybyte.accedo.di.manager;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Jose Torres in FreakyByte on 25/06/16.
 */
@Singleton
public class WidgetManager {

    private Toast mToast;
    private Context mContext;
    private Handler mHandler;

    @Inject
    public WidgetManager(Context context) {
        this.mContext = context;
        mHandler = new Handler();
    }

    /**
     * Method that creates a short toast based in a String
     *
     * @param message The String that is going to be show
     */
    public void createShortToast(final String message) {
        handlerPost(new Runnable() {
            public void run() {
                if (!isToastShowing()) {
                    mToast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
        });
    }

    /**
     * Method that creates a long Toast Message based in a string
     *
     * @param message The String that is going to be show
     */
    public void createLongToast(final String message) {
        handlerPost(new Runnable() {
            public void run() {
                if (!isToastShowing()) {
                    mToast = Toast.makeText(mContext, message, Toast.LENGTH_LONG);
                    mToast.show();
                }
            }
        });
    }

    /**
     * Method that creates a short Toast based in a string id
     *
     * @param id The Int from the String id
     */
    public void createShortToast(final int id) {
        handlerPost(new Runnable() {
            public void run() {
                if (!isToastShowing()) {
                    mToast = Toast.makeText(mContext, mContext.getString(id), Toast.LENGTH_SHORT);
                    mToast.show();
                }
            }
        });
    }

    /**
     * Method that creates a long Toast Message based in a string idsoi
     *
     * @param id
     */
    public void createLongToast(final int id) {
        handlerPost(new Runnable() {
            public void run() {
                if (!isToastShowing()) {
                    mToast = Toast.makeText(mContext, mContext.getString(id), Toast.LENGTH_LONG);
                    mToast.show();
                }
            }
        });
    }

    private boolean isToastShowing() {
        if (mToast == null)
            return false;
        else if (mToast.getView().isShown())
            return true;
        else
            return false;
    }

    public void handlerPost(Runnable runnable) {
        mHandler.post(runnable);
    }

}
