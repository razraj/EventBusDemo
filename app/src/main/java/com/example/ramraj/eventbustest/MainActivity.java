package com.example.ramraj.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private EditText textView;
    private Button button;
    static EventBus eventBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventBus.register(this);
        textView = (EditText)findViewById(R.id.text);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                 MessageObject messageObject = EventBus.getDefault().getStickyEvent(MessageObject.class);
//// Better check that an event was actually posted before
//                if(messageObject == null) {
//                    // Now do something with it
//                    eventBus.postSticky(new MessageObject(textView.getText().toString().trim()));
//                }
                Intent intent = new Intent(getApplicationContext(),ScrollingActivity.class);
                startActivity(intent);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageObject messageObject ){
        this.textView.setText(messageObject.getMessage());
    }

    @Override
    protected void onDestroy() {
        eventBus.unregister(this);
        super.onDestroy();
    }
}
