package com.github.chenyoca.validation;

import android.widget.EditText;

/**
 * Created by YooJia.Chen
 * YooJia.Chen@gmail.com
 * 2014-07-14
 */
public interface Display {
    /**
     * Dismiss the message
     * @param field Target view.
     */
    void dismiss(EditText field);

    /**
     * Show the message
     * @param field Target view.
     * @param message Message to show.
     */
    void show(EditText field, String message);
}
