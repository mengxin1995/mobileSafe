package com.my.mobilesafe.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.my.mobilesafe.R;
import com.my.mobilesafe.utils.ConstantValue;
import com.my.mobilesafe.utils.SpUtils;
import com.my.mobilesafe.utils.StreamUtil;
import com.my.mobilesafe.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends myActivity {


    /**
     * 更新新版本状态码
     */
    private static final int UPDATE_VERSION = 100;
    /**
     * 进入应用程序主界面状态码
     */
    private static final int ENTER_HOME = 101;
    /**
     * url异常
     */
    private static final int URL_ERROR = 102;
    private TextView tv_version_name;
    private int mLocalVersionCode;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_VERSION :
                    showUpdateDialog();
                    break;
                case ENTER_HOME :
                    //进入主界面
                    enterHome();
                    break;
                case URL_ERROR :
                    ToastUtil.show(getApplicationContext(), "URL异常");
                    enterHome();
                    break;
            }
        }
    };
    private String mVersionDes;
    private String mDownloadUrl;
    private View rl_root;

    /**
     *  弹出对话框,提示用户更新
     */
    private void showUpdateDialog() {
        //这里只能用this环境
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.home_apps);
        builder.setTitle("版本更新");
        builder.setMessage(mVersionDes);

        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //下载apk 用xutils实现断点下载
                downloadAAPK();
            }
        });

        builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                enterHome();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                enterHome();
            }
        });

        builder.show();
    }

    private void downloadAAPK() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "mobilesafe.apk";

            HttpUtils httpUtils = new HttpUtils();
            httpUtils.download(mDownloadUrl, path, new RequestCallBack<File>() {
                @Override
                public void onSuccess(ResponseInfo<File> responseInfo) {
                    File file = responseInfo.result;

                    //提示用户安装
                    installApk(file);
                }

                @Override
                public void onFailure(HttpException e, String s) {

                }

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onLoading(long total, long current, boolean isUploading) {
                    super.onLoading(total, current, isUploading);
                }
            });
        }
    }

    /**
     *  那幢Apk
     */
    private void installApk(File file) {
        //系统应用界面
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
//        intent.setData(Uri.fromFile(file));
//        intent.setType("application/vnd.android.package-archive");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        enterHome();
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     *  进入应用程序主界面
     */
    private void enterHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        //开启新的界面后将导航界面关闭
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //初始化UI
        initUI();
        initData();
        initAnimation();
    }

    private void initAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(3000);
        rl_root.startAnimation(alphaAnimation);
    }

    private void initData() {
        tv_version_name.setText("版本名称:" + getVersionName());
        //检测是否有更新
        mLocalVersionCode= getVersionCode();

        if(SpUtils.getBoolean(getApplicationContext(), ConstantValue.OPEN_UPDATE, false)){
            checkVersion();
        }else{
            mHandler.sendEmptyMessageDelayed(ENTER_HOME, 4000);
        }
    }

    private void checkVersion() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获得对象
                Message msg = Message.obtain();
                long startTime = System.currentTimeMillis();
                try {
                    URL url = new URL("http://192.168.1.109/update.json");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    //设置请求头
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setRequestMethod("GET");

                    if(connection.getResponseCode() == 200){
                        InputStream is = connection.getInputStream();
                        String json = StreamUtil.streamToString(is);
                        //json解析
                        JSONObject jsonObject = new JSONObject(json);
                        String versionName =  jsonObject.getString("versionName");
                        mVersionDes = jsonObject.getString("versionDes");
                        String versionCode = jsonObject.getString("versionCode");
                        mDownloadUrl = jsonObject.getString("downloadUrl");

                        if(Integer.parseInt(versionCode) > mLocalVersionCode){
                            //用到了更新UI,用消息机制
                            msg.what = UPDATE_VERSION;
                        }else{
                            msg.what = ENTER_HOME;
                        }
                    }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                    msg.what = URL_ERROR;
                }catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    //制定睡眠时间
                    long endTime = System.currentTimeMillis();
                    if(endTime - startTime < 4000){
                        try {
                            Thread.sleep(4000 - (endTime - startTime) );
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mHandler.sendMessage(msg);
                }

            }
        }).start();
    }

    private int getVersionCode() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * @return
     */
    private String getVersionName() {
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 初始化UI方法
    *
    * */
    private void initUI() {
        tv_version_name = (TextView) findViewById(R.id.tv_version_name);
        rl_root = findViewById(R.id.rl_root);
    }
}