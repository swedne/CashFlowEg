package dy.swed.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<View> viewList;
    private List<ViewHolder> viewHolderList;
    private int mark = 0;
    private String classNames = "";
    private String proIds = "";
    private LinearLayout parent;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = findViewById(R.id.parent);
        initView();
    }


    protected void initView() {
        viewList = new ArrayList<>();
        viewHolderList = new ArrayList<>();
        View addView = LayoutInflater.from(this).inflate(R.layout.item_rec_layout, null);
        addView.setId(mark);
        parent.addView(addView, mark);
        getViewInstance(addView);
        findViewById(R.id.v_add_class).setOnClickListener(this);
        findViewById(R.id.v_delete_class).setOnClickListener(this);
        btn = findViewById(R.id.bt_add_class);
        btn.setOnClickListener(this);
    }

    private void getViewInstance(View addView) {
        ViewHolder vh = new ViewHolder();
        vh.id = addView.getId();
        vh.create_class_et_class_name = (EditText) addView.findViewById(R.id.create_class_et_class_name);
        vh.create_class_ll_pro_name = (LinearLayout) addView.findViewById(R.id.create_class_ll_pro_name);
        vh.create_class_tv_pro_name = (TextView) addView.findViewById(R.id.create_class_tv_pro_name);
        // 设置监听
        vh.create_class_ll_pro_name.setOnClickListener(selectProListener);

        viewHolderList.add(vh);
        viewList.add(addView);
    }

    View.OnClickListener selectProListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View view = (View) v.getParent();
            for (int i = 0; i < parent.getChildCount(); i++) {
                ViewHolder viewHolder = viewHolderList.get(i);
                Log.e("createclass", "view.getId()==" + view.getId() + "  viewHolder.id==" + viewHolder.id);
                if (view.getId() == viewHolder.id) {
//                    onProfession(App.sp.getString(GlobalConstants.ORGID, ""), App.sp.getString(GlobalConstants.SCHID, ""), viewHolder.create_class_tv_pro_name, viewHolder.id);
                }
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.v_add_class:
                mark++;
                View addView = LayoutInflater.from(this).inflate(R.layout.item_rec_layout, null);
                addView.setId(mark);
                parent.addView(addView);
                getViewInstance(addView);
                break;
            case R.id.v_delete_class:
                int childNum = parent.getChildCount();
                if (childNum > 1) {
                    parent.removeViewAt(childNum - 1);
                    viewHolderList.remove(viewHolderList.size() - 1);
                    viewList.remove(viewList.size() - 1);
                    mark--;
                }
                break;
            case R.id.bt_add_class:
                showMsg();
                break;
        }
    }

    private void showMsg() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < parent.getChildCount(); i++) {
            ViewHolder viewHolder = viewHolderList.get(i);
            String className = viewHolder.create_class_et_class_name.getText().toString().trim();
            String proName = viewHolder.create_class_tv_pro_name.getText().toString().trim();
            String proId = (String) viewHolder.create_class_tv_pro_name.getTag();
            sb.append("班级==" + className + "\tid==" + proId);
            sb.append("\n");
        }
        btn.setText(sb);
    }

    public class ViewHolder {
        private int id = -1;
        private EditText create_class_et_class_name;
        private LinearLayout create_class_ll_pro_name;
        private TextView create_class_tv_pro_name;
    }
}
