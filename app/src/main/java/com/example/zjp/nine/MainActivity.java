package com.example.zjp.nine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.zjp.nine.myswitch.SlideSwitch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button [] buttons = new Button[3];
    private int ans;
    private Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        buttons[0]=(Button)findViewById(R.id.nine_button1);
        buttons[1]=(Button)findViewById(R.id.nine_button2);
        buttons[2]=(Button)findViewById(R.id.nine_button3);
        buttons[0].setOnClickListener(this);
        buttons[1].setOnClickListener(this);
        buttons[2].setOnClickListener(this);
         switch1= (Switch) findViewById(R.id.switch1);
        switch1.setChecked(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nine_button1:ans=2;break;
            case R.id.nine_button2:ans=30;break;
            case R.id.nine_button3:ans=20;break;
        }
        Intent intent = new Intent(MainActivity.this,NineActivity.class);
        intent.putExtra("data",ans);
        intent.putExtra("ischeck",switch1.isChecked());
        startActivity(intent);
    }
}
