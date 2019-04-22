package com.example.commonui.widget;

import android.content.ClipboardManager;
import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class PasteObserverEditText extends AppCompatEditText {

    private PasteCallback pasteCallback;

    public PasteObserverEditText(Context context) {
        super(context);
    }

    public PasteObserverEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasteObserverEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTextContextMenuItem(int id) {

        if (id == android.R.id.paste) {
            //拿到粘贴板的文本，setSpan的时候第二个参数last+文本的长度
            ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            String text = clip.getPrimaryClip().getItemAt(0).getText().toString();
            if (pasteCallback != null) {
                pasteCallback.onPaste(text);
            }
        }

        return super.onTextContextMenuItem(id);
    }

    public void setPasteCallback(PasteCallback pasteCallback) {
        this.pasteCallback = pasteCallback;
    }

    interface PasteCallback {
        void onPaste(String text);
    }
}
