package com.freakybyte.accedo.controller.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.freakybyte.accedo.R;
import com.freakybyte.accedo.listener.ListenerDialog;
import com.freakybyte.accedo.ui.material.ButtonFlat;
import com.freakybyte.accedo.util.AndroidUtil;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SaveScoreDialog extends DialogFragment implements View.OnClickListener {

    public static final String TAG = "SaveScoreDialog";

    private View rootView;
    private RelativeLayout mainLayout;
    private MaterialEditText editUserName = null;
    private ButtonFlat btnSave = null;
    private ButtonFlat btnCancel = null;

    private Animation anim;

    private ListenerDialog mListenerDialog;

    private int numBacks = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.Theme_Dialog_Transparent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && numBacks == 0) {
                    onDismissDialog();

                    return true;
                }
                return false;
            }
        });

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        rootView = inflater.inflate(R.layout.dialog_save_score, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);

        getBtnSave().setOnClickListener(this);
        getBtnCancel().setOnClickListener(this);

        anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
    }

    private void onDismissDialog() {
        if (numBacks == 0) {
            numBacks++;
        }
    }

    private boolean isValidUser() {
        boolean isValid = AndroidUtil.isValidField(getEditUserName().getText().toString());

        if (!isValid)
            getEditUserName().setError(getString(R.string.error_invalid_user));

        return isValid;
    }

    @Override
    public void onStart() {
        getLayoutMain().setAnimation(anim);
        numBacks = 0;
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_cancel:
                dismiss();
                break;
            case R.id.buttonSave:
                if (isValidUser() && mListenerDialog != null) {
                    mListenerDialog.btnOkClick(getEditUserName().getText().toString());
                    dismiss();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void dismiss() {
        mListenerDialog.btnOnCancel();
        AndroidUtil.hideKeyboard(getLayoutMain());
        super.dismiss();
    }

    public void setListenerDialog(ListenerDialog mListenerDialog) {
        this.mListenerDialog = mListenerDialog;
    }

    public RelativeLayout getLayoutMain() {
        if (mainLayout == null)
            mainLayout = (RelativeLayout) rootView.findViewById(R.id.dialog_rootView);
        return mainLayout;
    }

    public ButtonFlat getBtnSave() {
        if (btnSave == null)
            btnSave = (ButtonFlat) rootView.findViewById(R.id.buttonSave);
        return btnSave;
    }

    public ButtonFlat getBtnCancel() {
        if (btnCancel == null)
            btnCancel = (ButtonFlat) rootView.findViewById(R.id.button_cancel);
        return btnCancel;
    }

    private MaterialEditText getEditUserName() {
        if (editUserName == null)
            editUserName = (MaterialEditText) rootView.findViewById(R.id.editUserName);
        return editUserName;
    }

}
