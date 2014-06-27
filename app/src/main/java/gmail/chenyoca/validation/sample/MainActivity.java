package gmail.chenyoca.validation.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import gmail.chenyoca.validation.FormValidator;
import gmail.chenyoca.validation.ResultWrapper;
import gmail.chenyoca.validation.BuildInTypes;
import gmail.chenyoca.validation.Configuration;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Configuration conf = Configuration.buildIn(this, BuildInTypes.Required, "必填选项！");
        conf.add(BuildInTypes.LengthInMax, 20);
        conf.add(BuildInTypes.Email);

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
        fv.configFor(Configuration.buildIn(this, BuildInTypes.ChineseMobilePhone), R.id.form_field_1);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.CreditCard), R.id.form_field_2);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.Digits), R.id.form_field_3);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.Email), R.id.form_field_4);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.EqualTo, "chenyoca"), R.id.form_field_5);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.Host), R.id.form_field_6);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.HTTPURL), R.id.form_field_7);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.LengthInMax, 5), R.id.form_field_8);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.LengthInMin, 4), R.id.form_field_9);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.LengthInRange, 4,8), R.id.form_field_10);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.NotBlank), R.id.form_field_11);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.Numeric), R.id.form_field_12);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.ValueInMax, 100), R.id.form_field_13);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.ValueInMin, 20.0), R.id.form_field_14);
        fv.configFor(Configuration.buildIn(this, BuildInTypes.ValueInRange, 18, 30), R.id.form_field_15);

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
