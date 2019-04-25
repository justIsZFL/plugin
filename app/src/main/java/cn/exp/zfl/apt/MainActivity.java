package cn.exp.zfl.apt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.zfl.aptlib.BindView;
import cn.zfl.aptlib.Test;

/**
 * @author zfl
 */
@Test(path = "main")
public class MainActivity extends AppCompatActivity {
    @BindView(value = R.id.tv1)
    TextView tv1;
    @BindView(value = R.id.tv2)
    TextView tv2;
    @BindView(value = R.id.tv3)
    TextView tv3;
    @BindView(value = R.id.tv4)
    TextView tv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1.setText("自定义注解1");
        tv2.setText("自定义注解2");
        tv3.setText("自定义注解3");
        tv4.setText("自定义注解4");
    }


//    public void startActivity(View view) {
//        startActivity(new Intent(this, Main2Activity.class));
//    }
}
