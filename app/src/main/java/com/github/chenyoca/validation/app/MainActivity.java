package com.github.chenyoca.validation.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.github.chenyoca.validation.Types;
import com.github.chenyoca.validation.Config;
import com.github.chenyoca.validation.FormValidator;
import com.github.chenyoca.validation.ResultWrapper;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Config conf = Config.from(Types.Required, "必填选项！");
        conf.add(Types.LengthInMax, 20);
        conf.add(Types.Email);

        final android.widget.EditText test = (android.widget.EditText) findViewById(R.id.single_test);

        final Button commit = (Button) findViewById(R.id.single_commit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultWrapper rw = FormValidator.testField(test, conf);
                if (rw.passed){
                    commit.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }else{
                    commit.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

        final LinearLayout form = (LinearLayout) findViewById(R.id.form);
        final FormValidator fv = new FormValidator();
        fv.addField(Config.from(Types.ChineseMobilePhone), R.id.form_field_1);
        fv.addField(Config.from(Types.CreditCard), R.id.form_field_2);
        fv.addField(Config.from(Types.Digits), R.id.form_field_3);
        fv.addField(Config.from(Types.Email), R.id.form_field_4);
        fv.addField(Config.from(Types.EqualTo, "chenyoca"), R.id.form_field_5);
        fv.addField(Config.from(Types.Host), R.id.form_field_6);
        fv.addField(Config.from(Types.HTTPURL), R.id.form_field_7);
        fv.addField(Config.from(Types.LengthInMax, 5), R.id.form_field_8);
        fv.addField(Config.from(Types.LengthInMin, 4), R.id.form_field_9);
        fv.addField(Config.from(Types.LengthInRange, 4, 8), R.id.form_field_10);
        fv.addField(Config.from(Types.NotBlank), R.id.form_field_11);
        fv.addField(Config.from(Types.Numeric), R.id.form_field_12);
        fv.addField(Config.from(Types.ValueInMax, 100), R.id.form_field_13);
        fv.addField(Config.from(Types.ValueInMin, 20.0), R.id.form_field_14);
        fv.addField(Config.from(Types.ValueInRange, 18, 30), R.id.form_field_15);

        fv.bind(form)
          .applyTypeToView();

        final Button formCommit = (Button) findViewById(R.id.form_commit);
        formCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fv.testFormAll(form)){
                    formCommit.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                }else{
                    formCommit.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                }
            }
        });

    }
}
