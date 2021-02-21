package com.jihong.onehandedkeyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    InputMethodManager inputMethodManager;
    List<InputMethodInfo> enabledInputMethodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.editText);
        editText.requestFocus();

        //키보드 보이게 하는 부분
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        // 기본 키보드 체크
        this.inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (checkActivated() && !checkDefaulted()) {
            this.inputMethodManager.showInputMethodPicker();
        }

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        // right : 오른손 모드
        // left : 왼손 모드

        editor.putString("side", "left");
        editor.apply();
    }

    protected void onResume() {
        super.onResume();
        if (!checkActivated()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
            builder.setTitle("한 손 조작 키패드");
            builder.setMessage("키보드를 활성화 해주세요");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    Intent intent = new Intent();
                    intent.setAction("android.settings.INPUT_METHOD_SETTINGS");
                    MainActivity.this.startActivityForResult(intent, 100);
                    dialog.dismiss();
                }
            });
            AlertDialog alert = builder.create();
            alert.setCanceledOnTouchOutside(false);
            alert.show();
        }
    }


    private boolean checkActivated() {
        this.enabledInputMethodList = this.inputMethodManager.getEnabledInputMethodList();
        int size = this.enabledInputMethodList.size();
        for (int i = 0; i < size; i++) {
            if (((InputMethodInfo) this.enabledInputMethodList.get(i)).getServiceName().contentEquals("com.jihong.onehandedkeyboard.OneHandIME")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDefaulted() {
        if (Settings.Secure.getString(getContentResolver(), "default_input_method").contentEquals("com.jihong.onehandedkeyboard/.OneHandIME")) {
            return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            this.inputMethodManager.showInputMethodPicker();
        }
    }
}
