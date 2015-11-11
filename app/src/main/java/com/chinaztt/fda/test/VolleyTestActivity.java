package com.chinaztt.fda.test;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chinaztt.fda.entity.UpdateBean;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

/**
 * 当前类注释:Volley 网络框架数据请求
 * 项目名：FastDev4Android
 * 包名：com.chinaztt.fda.test
 * 作者：江清清 on 15/11/10 13:23
 * 邮箱：jiangqqlmj@163.com
 * QQ： 781931404
 * 公司：江苏中天科技软件技术有限公司
 */
@EActivity(R.layout.volley_test_layout)
public class VolleyTestActivity  extends BaseActivity {
    private static final String TAG=VolleyTestActivity.class.toString();
    @ViewById
    LinearLayout top_bar_linear_back;
    @ViewById
    TextView top_bar_title,tv_result;
    @ViewById
    ImageView img_result;
    @ViewById
    Button btn_string,btn_json,btn_image;

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue=Volley.newRequestQueue(this);

    }
    @Click({R.id.top_bar_linear_back,R.id.btn_string,R.id.btn_json,R.id.btn_image})
    public void backLinearClick(View view){
        switch (view.getId()){
            case R.id.top_bar_linear_back:
                this.finish();
                break;
            case R.id.btn_string:
                //获取字符串
                Log.d(TAG, "点击获取字符串...");
                StringRequest request=new StringRequest(Request.Method.GET, "http://www.baidu.com", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        tv_result.setText(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(request);
                break;
            case R.id.btn_json:
                //获取json
                Log.d(TAG, "点击获取json...");
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                        "http://interface.zttmall.com/update/mallUpdate", null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson=new Gson();
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        tv_result.setText(gson.fromJson(response.toString(), UpdateBean.class).toString());

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsonObjectRequest);
                break;
            case R.id.btn_image:
                //获取图片
                //http:\/\/interface.zttmall.com\/Images\/upload\/image\/20150325\/20150325083110_0898.jpg
                Log.d(TAG, "点击获取图片...");
                ImageRequest imageRequest=new ImageRequest("http://interface.zttmall.com/Images/upload/image/20150325/20150325083110_0898.jpg"
                        , new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        tv_result.setVisibility(View.GONE);
                        img_result.setVisibility(View.VISIBLE);
                        img_result.setImageBitmap(response);
                    }
                }, 300, 300, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(imageRequest);
                break;
        }
    }
    @AfterViews
    public void setViews(){
        top_bar_title.setText("Volley网络框架测试实例");
    }
}
