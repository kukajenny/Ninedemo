package com.example.zjp.nine;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.zjp.nine.dialog.MyDialog;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import at.markushi.ui.CircleButton;

/**
 * Created by zjp on 15-11-27.
 */
public class NineActivity extends AppCompatActivity implements View.OnClickListener{

    private Button [][]buttons;
    private boolean ischeck;
    int cnt,num1,runtime;
    private int [][] a={{6,3,5,2,9,8,7,1,4},
            {2,7,4,3,5,1,6,8,9},
            {1,8,9,7,6,4,5,3,2},

            {7,4,8,9,3,6,2,5,1},
            {9,5,2,1,8,7,4,6,3},
            {3,6,1,5,4,2,9,7,8},

            {4,9,3,8,7,5,1,2,6},
            {8,1,7,6,2,9,3,4,5},
            {5,2,6,4,1,3,8,9,7}};
    private static final int update_button=1;
    private static final int update_time=2;
    private TextView text_runtime;
    private Button button_pause;
    private Timer mytimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        init();
    }
    public void init(){
        /*GridView gridview = (GridView) findViewById(R.id.gridview);

        //生成动态数组，并且转入数据
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        Intent intent=getIntent();
        int data=intent.getIntExtra("data",0);
        String [][] a=new String[10][10];
        while(data--!=0){
            int x = (int)(Math.random() * 9);
            int y = (int)(Math.random() * 9);
            int d=1+(int)(Math.random() * 9);
            a[x][y]=d+"";
        }
        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemText", a[i][j]);//按序号做ItemText
                lstImageItem.add(map);
            }

        }
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应
        SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释
                lstImageItem,//数据来源
                R.layout.griditem,//night_item的XML实现

                //动态数组与ImageItem对应的子项
                new String[] {"ItemText"},

                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.button});
        //添加并且显示
        gridview.setAdapter(saImageItems);
        //添加消息处理
        gridview.setOnItemClickListener(new ItemClickListener());*/
        text_runtime = (TextView)findViewById(R.id.text_runtime);
        final Resources resources = getResources();
        int  []lin = {R.id.line1,R.id.line2,R.id.line3,R.id.line4,R.id.line5,R.id.line6,R.id.line7,R.id.line8,R.id.line9};
        LinearLayout [] linearLayouts = new LinearLayout[10];
        buttons= new Button[10][10];
        float screenWidth  = getWindowManager().getDefaultDisplay().getWidth();        // 屏幕宽（像素，如：480px）
        float screenHeight = getWindowManager().getDefaultDisplay().getHeight();

        Intent intent=getIntent();
        int data=intent.getIntExtra("data", 0);
        cnt=data;num1=0;runtime=1;
        ischeck = intent.getBooleanExtra("ischeck",false);

        int num=0;
        while(true){
            int x = getradom(3);
            int y = getradom(3);
            if(x==y)continue;
            for(int i=0;i<3;i++){
                for(int j=0;j<9;j++){
                    int t=a[i+x*3][j];
                    a[i+x*3][j]=a[i+y*3][j];
                    a[i+y*3][j]=t;
                }
            }

            x = getradom(3);
            y = getradom(3);
            for(int i=0;i<3;i++){
                for(int j=0;j<9;j++){
                    int t=a[j][i+x*3];
                    a[j][i+x*3]=a[j][i+y*3];
                    a[j][i+y*3]=t;
                }
            }

            for(int i=0;i<10;i++){
                int d=getradom(3);
                x = getradom(3);
                y = getradom(3);
                if(x==y)continue;
                for(int j=0;j<9;j++){
                    int t=a[d*3+x][j];
                    a[d*3+x][j]=a[d*3+y][j];
                    a[d*3+y][j]=t;
                }
            }

            for(int i=0;i<10;i++){
                int d=getradom(3);
                x = getradom(3);
                y = getradom(3);
                if(x==y)continue;
                for(int j=0;j<9;j++){
                    int t=a[j][d*3+x];
                    a[j][d*3+x]=a[j][d*3+y];
                    a[j][d*3+y]=t;
                }
            }
            num++;
            if(num>2)break;
        }
        Log.d("data",data+"");
        while(true){
            int x1 = (int)(Math.random() * 9);
            int y1 = (int)(Math.random() * 9);
            if(a[x1][y1]==0)continue;
            data--;
            a[x1][y1]=0;
            if(data==0)break;
        }

        for(int i=0;i<9;i++){
            linearLayouts[i] = (LinearLayout)findViewById(lin[i]);
            for(int j=0;j<9;j++){
                buttons[i][j]=new Button(this);
                if(a[i][j]==0)buttons[i][j].setText("");
                else  buttons[i][j].setText(a[i][j]+"");

                if(j!=0){
                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(3, (int) screenWidth / 9));

                    imageView.setBackgroundColor(resources.getColor(R.color.main));
                    linearLayouts[i].addView(imageView);
                }
                if(j!=0&&j%3==0) {
                    ImageView imageView = new ImageView(this);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(5, (int) screenWidth / 9));

                    imageView.setBackgroundColor(resources.getColor(R.color.main));
                    linearLayouts[i].addView(imageView);
                }
                if(a[i][j]==0){
                    buttons[i][j].setBackground(getResources().getDrawable(R.drawable.style_button));

                    Log.d("dialogg",buttons[i][j]+"");
                }
                else buttons[i][j].setBackground(getResources().getDrawable(R.drawable.style_button_select));
                buttons[i][j].setTag(i * 9 + j);
                buttons[i][j].setOnClickListener(this);
                buttons[i][j].setLayoutParams(new LinearLayout.LayoutParams( ((int)screenWidth/9)-5,(int)screenWidth/9));
                linearLayouts[i].addView(buttons[i][j]);
            }
        }

        Button button_newgame=(Button)findViewById(R.id.button_newgame);
        button_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NineActivity.this.finish();
            }
        });


        newtimer();
        button_pause = (Button)findViewById(R.id.button_pause);
        button_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button_pause.getText().equals("暂停")){
                    mytimer.cancel();
                    button_pause.setText("开始");
                    for(int i=0;i<9;i++){
                        for(int j=0;j<9;j++){
                            buttons[i][j].setClickable(false);
                        }
                    }
                }
                else {
                    newtimer();
                    button_pause.setText("暂停");
                    for(int i=0;i<9;i++){
                        for(int j=0;j<9;j++){
                            buttons[i][j].setClickable(true);
                        }
                    }
                }
            }
        });



    }

    public void newtimer(){
        TimerTask task = new TimerTask(){
            public void run() {
                Message message = new Message();
                message.what = update_time;
                handler.sendMessage(message);
            }
        };
        Timer timer = new Timer(true);
        timer.schedule(task, 0, 1000);//延时0s执行,每100ms执行一次
        mytimer = timer;
    }
    public void setcolor(int x,int y,int num){
        if(ischeck){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    if(i==x&&j==y)continue;
                    if(buttons[i][j].getText().equals(num+"")){
                        buttons[i][j].setBackgroundColor(getResources().getColor(R.color.select_color8));
                    }
                    else {
                        if(a[i][j]==0)buttons[i][j].setBackground(getResources().getDrawable(R.drawable.style_button));
                        else buttons[i][j].setBackground(getResources().getDrawable(R.drawable.style_button_select));
                    }
                }
            }
        }
    }

    public int getradom(int x){
        return (int)(Math.random()*x);
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case update_button:
                    Log.d("abc","yes1");
                    final String [] co = {"#BC8F8F","#F08080","#CD5C5C","#A52A2A","#800000","#FF0000","#FA8072","#FF2400","#FF6347","#E9967A","#FF7F50","#FF8033","#A16B47","#E69966","#8B4513","#D2691E","#CC5500","#FF7300","#FFDAB9","#F4A460","#FFB366","#FFE4C4","#D2B48C","#FFEBCD","#F5DEB3","#DAA520","#B8860B","#E6C35C","#FFBF00","#FFF8DC","#FFFACD"};
                    num1++;
                    if(num1<30){
                        int d=getradom(9);
                        for(int i=0;i<d;i++){
                            int x=getradom(9);
                            int c=getradom(co.length);
                            for(int j=0;j<9;j++){
                                buttons[j][x].setBackgroundColor(Color.parseColor(co[c]));
                            }
                        }
                    }
                    else if(num1>=30&&num1<60){
                        int d=getradom(9);
                        for(int i=0;i<d;i++){
                            int x=getradom(9);
                            int c=getradom(co.length);
                            for(int j=0;j<9;j++){
                                buttons[x][j].setBackgroundColor(Color.parseColor(co[c]));
                            }
                        }
                    }
                    else if(num1<100){
                        int d=getradom(81);
                        for(int i=0;i<d;i++){
                            int x=getradom(9);
                            int y=getradom(9);
                            int c=getradom(co.length);
                            buttons[x][y].setBackgroundColor(Color.parseColor(co[c]));
                        }

                    }

                        /*try {
                            Thread.sleep(1000); //1000 毫秒，也就是1秒.
                        } catch(InterruptedException ex) {
                            Thread.currentThread().interrupt();
                        }*/
                    break;
                case update_time:text_runtime.setText("耗时:"+runtime++);
                    break;

            }
        }
    };
    @Override
    public void onClick(View v) {
        int data= (int) v.getTag();
        final int x=data/9;
        final int y=data%9;
        Log.d("dialogg", x + " " + y);
        if(a[x][y]==0){

            Log.d("abc","abc"+cnt);
            final Dialog dialog = new MyDialog(NineActivity.this,
                    R.style.MyDialog, new MyDialog.LoginInputListener() {
                @Override
                public void onLoginInputComplete(int num, int color) {
                    Log.d("dialogg", buttons[x][y] + " " + num + " " + color);
                    if(buttons[x][y].getText().equals(""))cnt--;
                    buttons[x][y].setText(num + "");
                    buttons[x][y].setTextColor(color);
                    setcolor(x, y, num);
                    buttons[x][y].setBackgroundColor(getResources().getColor(R.color.select_color5));
                    boolean [][]visx=new boolean[10][10];
                    boolean [][]visy=new boolean[10][10];
                    boolean [][][]visxy = new boolean[4][4][10];
                    boolean flag=false;
                    if(cnt==0){
                        for(int i=0;i<9;i++){
                            for(int j=0;j<9;j++){
                                int b=Integer.parseInt(buttons[i][j].getText().toString());
                                if(visx[i][b]){flag=true;break;}
                                if(visy[j][b]){flag=true;break;}
                                if(visxy[i/3][j/3][b]){flag=true;break;}
                                visx[i][b]=true;
                                visy[j][b]=true;
                                visxy[i/3][j/3][b]=true;
                            }
                        }

                        if(!flag){
                            TimerTask task1 = new TimerTask(){
                                       public void run() {
                                           Message message = new Message();
                                           message.what = update_button;
                                           handler.sendMessage(message);
                                        }
                                 };
                            Timer timer1 = new Timer(true);
                            timer1.schedule(task1,0, 50);//延时0s执行,每100ms执行一次
                            if(num1>200)timer1.cancel();
                            mytimer.cancel();
                            button_pause.setClickable(false);
                        }
                    }
                }
            });
            dialog.show();
        }
        else {
            int flag=Integer.parseInt(buttons[x][y].getText().toString());
            setcolor(x,y,flag);
            buttons[x][y].setBackgroundColor(getResources().getColor(R.color.select_color8));
        }


    }

    //当AdapterView被单击(触摸屏或者键盘)，则返回的Item单击事件
    class  ItemClickListener implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> arg0,//The AdapterView where the click happened
                                View arg1,//The view within the AdapterView that was clicked
                                int arg2,//The position of the view in the adapter
                                long arg3//The row id of the item that was clicked
        ) {
            //在本例中arg2=arg3
            HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);
            //显示所选Item的ItemText
            setTitle((String)item.get("ItemText"));
        }

    }
}
