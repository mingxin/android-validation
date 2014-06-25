package gmail.chenyoca.validation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout form = (RelativeLayout) findViewById(R.id.form);

        FormValidator validator = new FormValidator();
        validator.test(form);

    }
}
