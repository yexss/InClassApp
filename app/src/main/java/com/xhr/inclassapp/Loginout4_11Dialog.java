package com.xhr.inclassapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class Loginout4_11Dialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireActivity())
                .setTitle("提示")
                .setMessage("确认退出？")
                .setPositiveButton("确认", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    Loginout4_11Dialog.this.requireActivity().finish();
                })
                .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                .create();
    }
}
