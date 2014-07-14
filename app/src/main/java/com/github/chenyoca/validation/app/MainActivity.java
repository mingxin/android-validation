package com.github.chenyoca.validation.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.chenyoca.validation.Display;
import com.github.chenyoca.validation.Types;
import com.github.chenyoca.validation.Config;
import com.github.chenyoca.validation.FormValidator;
import com.github.chenyoca.validation.ResultWrapper;

public class MainActivity extends Activity {

    Display testDisplay = new Display() {
        @Override
        public void dismiss(EditText field) {

        }

        @Override
        public void show(EditText field, String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Config conf = Config.from(Types.Required, "必填选项！");
        conf.add(Types.LengthInMax, 20);
        conf.add(Types.Email);

        final EditText test = (EditText) findViewById(R.id.single_test);

        final Button commit = (Button) findViewById(R.id.single_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultWrapper rw = FormValidator.testField(test, conf, testDisplay);
                int color = rw.passed ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                commit.setTextColor(getResources().getColor(color));
            }
        });

        final LinearLayout form = (LinearLayout) findViewById(R.id.form);
        final FormValidator fv = new FormValidator(testDisplay);
        fv.addField(R.id.form_field_1, Types.ChineseMobilePhone, Types.Required);
        fv.addField(R.id.form_field_2, Types.CreditCard);
        fv.addField(R.id.form_field_3, Config.from(Types.Digits));
        fv.addField(R.id.form_field_4, Config.from(Types.Email));
        fv.addField(R.id.form_field_5, Config.from(Types.EqualTo, "chenyoca"));
        fv.addField(R.id.form_field_6, Config.from(Types.Host));
        fv.addField(R.id.form_field_7, Config.from(Types.HTTPURL));
        fv.addField(R.id.form_field_8, Config.from(Types.LengthInMax));
        fv.addField(R.id.form_field_9, Config.from(Types.LengthInMin));
        fv.addField(R.id.form_field_10,Config.from(Types.LengthInRange));
        fv.addField(R.id.form_field_11,Config.from(Types.NotBlank));
        fv.addField(R.id.form_field_12,Config.from(Types.Numeric));
        fv.addField(R.id.form_field_13,Config.from(Types.ValueInMax, 100));
        fv.addField(R.id.form_field_14,Config.from(Types.ValueInMin, 20.0));
        fv.addField(R.id.form_field_15,Config.from(Types.ValueInRange, 18, 30));

        fv.bind(form)
          .applyTypeToView();

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int color = fv.testFormAll(form) ?
                        android.R.color.holo_green_dark : android.R.color.holo_red_dark;
                formCommit.setTextColor(getResources().getColor(color));
            }
        });

    }
}
