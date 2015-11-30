package com.chinaztt.fda.test;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chinaztt.fda.entity.UpdateBean;
import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.ui.base.BaseActivity;
import com.chinaztt.fda.utils.Log;
import com.chinaztt.fdv.Fdv_CallBackListener;
import com.chinaztt.fdv.Fdv_ImageCache;
import com.chinaztt.fdv.Fdv_JsonObjectRequest;
import com.chinaztt.fdv.Fdv_StringRequest;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    Button btn_string,btn_json,btn_image_request,btn_image_loader,btn_image_network,btn_string_post,btn_loader_list,btn_gson;
    @ViewById
    Button btn_fdv_get_params,btn_fdv_post_params;
    @ViewById
    NetworkImageView img_result_network;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue=Volley.newRequestQueue(this);
    }
    @Click({R.id.top_bar_linear_back,R.id.btn_string,R.id.btn_json,R.id.btn_image_request,R.id.btn_image_loader,R.id.btn_image_network,R.id.btn_string_post,R.id.btn_loader_list,R.id.btn_gson,R.id.btn_fdv_get_params,R.id.btn_fdv_post_params})
    public void backLinearClick(View view){
        switch (view.getId()){
            case R.id.top_bar_linear_back:
                this.finish();
                break;
            case R.id.btn_string:
                //获取字符串
                Log.d(TAG, "点击获取字符串...");
                new Fdv_StringRequest(VolleyTestActivity.this).get("http://www.baidu.com", new Fdv_CallBackListener<String>() {
                    @Override
                    public void onSuccessResponse(String response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(response.toString());
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                break;
            case R.id.btn_json:
                //获取json
                Log.d(TAG, "点击获取json...");
                new Fdv_JsonObjectRequest(VolleyTestActivity.this).get("http://interface.zttmall.com/update/mallUpdate", new Fdv_CallBackListener<JSONObject>() {
                    @Override
                    public void onSuccessResponse(JSONObject response) {
                        Gson gson=new Gson();
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(gson.fromJson(response.toString(),UpdateBean.class).toString());
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                break;
            case R.id.btn_image_request:
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
                        img_result_network.setVisibility(View.GONE);
                    }
                }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(imageRequest);
                break;
            case R.id.btn_image_loader:
                //使用imageloader进行获取图片
                ImageLoader imageLoader=new ImageLoader(requestQueue, new Fdv_ImageCache());
                tv_result.setVisibility(View.GONE);
                img_result.setVisibility(View.VISIBLE);
                img_result_network.setVisibility(View.GONE);
                ImageLoader.ImageListener listener=ImageLoader.getImageListener(img_result,R.drawable.ic_loading,R.drawable.ic_loading);
                imageLoader.get("http://interface.zttmall.com//Images//upload//image//20150328//20150328105404_2392.jpg", listener);
                break;
            case R.id.btn_image_network:
                //采用NetworkImageView imageview控件
                ImageLoader network_imageLoader=new ImageLoader(requestQueue, new Fdv_ImageCache());
                img_result.setVisibility(View.GONE);
                tv_result.setVisibility(View.GONE);
                img_result_network.setVisibility(View.VISIBLE);
                img_result_network.setImageUrl("http://interface.zttmall.com//Images//upload//image//20150325//20150325083214_8280.jpg",network_imageLoader);
                break;
            case R.id.btn_string_post:
                //修改Volley源代码,扩展StringRequest支持post参数设置
                final Map<String,String> params=new HashMap<String,String>();
                params.put("username","张三");
                params.put("password","12345");
                StringRequest post_stringRequest=new StringRequest(Request.Method.POST,"http://10.18.3.123:8080/SalesWebTest/TestVolleyPost", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        return params;
                    }
                };
                requestQueue.add(post_stringRequest);
                break;
            case R.id.btn_loader_list:
                //进行使用ImageLoader加载图片列表
                openActivity(VolleyLoaderActivity_.class);
                break;
            case R.id.btn_gson:
                //使用扩展工具 GsonRequest进行请求
                GsonRequest<UpdateBean> gsonRequest=new GsonRequest<UpdateBean>("http://interface.zttmall.com/update/mallUpdate", new Response.Listener<UpdateBean>() {
                    @Override
                    public void onResponse(UpdateBean response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }, UpdateBean.class);
                requestQueue.add(gsonRequest);
                break;
            case R.id.btn_fdv_get_params:
                //get请求  传入请求参数
                Map<String,String> params_get=new HashMap<String,String>();
                params_get.put("username","张三");
                params_get.put("password","12345");
                new Fdv_StringRequest(this).get("http://10.18.3.123:8080/SalesWebTest/TestVolleyPost", new Fdv_CallBackListener<String>() {
                    @Override
                    public void onSuccessResponse(String response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(response.toString());
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                },params_get);
                break;
            case  R.id.btn_fdv_post_params:
                //post请求  传入请求参数
                Map<String,String> params_post=new HashMap<String,String>();
                params_post.put("username","张三");
                params_post.put("password","12345");
                new Fdv_StringRequest(this).post("http://10.18.3.123:8080/SalesWebTest/TestVolleyPost", new Fdv_CallBackListener<String>() {
                    @Override
                    public void onSuccessResponse(String response) {
                        tv_result.setVisibility(View.VISIBLE);
                        img_result.setVisibility(View.GONE);
                        img_result_network.setVisibility(View.GONE);
                        tv_result.setText(response.toString());
                    }
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                },params_post);
                break;
        }
    }
    @AfterViews
    public void setViews(){
        top_bar_title.setText("Volley网络框架测试实例");
    }
}
