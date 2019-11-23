package com.dgpt.handlersendandpost;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btSend;
    private Button btPost;
    private TextView textview;
    private static final int SEND_UPDATA_TEXT=0;
    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SEND_UPDATA_TEXT:
                    textview.setText("sendMessage发来的消息");
                    break;

                default:
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        setViews();
        //事件监听
        setListener();
    }

    private void setListener() {

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message=new Message();
                        message.what=SEND_UPDATA_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });

        btPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //使用post发送消息
                        handler.post(new Runnable() {
                            //run方法中的代码执行在UI线程中
                            @Override
                            public void run() {
                                textview.setText("post发来消息");
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void setViews() {
        btSend=(Button) findViewById(R.id.btn_send);
        btPost=(Button) findViewById(R.id.btn_post);
        textview=(TextView) findViewById(R.id.tv_receive);
    }

}
