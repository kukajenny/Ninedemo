package com.example.zjp.nine.dialog;

/**
 * Created by zjp on 15-11-29.
 */
        import android.app.Dialog;
        import android.content.Context;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;

        import com.example.zjp.nine.R;

        import at.markushi.ui.CircleButton;

public class MyDialog extends Dialog implements View.OnClickListener{

    private Button[] dialog_button;
    private int[] id_button = {R.id.dialog_button1, R.id.dialog_button2, R.id.dialog_button3, R.id.dialog_button4, R.id.dialog_button5, R.id.dialog_button6, R.id.dialog_button7, R.id.dialog_button8, R.id.dialog_button9};
    Context context;
    LoginInputListener listener;
    int color;
    public MyDialog(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.context = context;
    }
    public MyDialog(Context context, int theme, LoginInputListener listener){
        super(context, theme);
        this.context = context;
        this.listener = listener;
    }
    public interface LoginInputListener
    {
        void onLoginInputComplete(int num, int color);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog);


         color= context.getResources().getColor(R.color.select_color1);
        dialog_button = new Button[10];
        for (int k = 0; k < 9; k++) {
            dialog_button[k] = (Button) findViewById(id_button[k]);
            dialog_button[k].setTag(k);
            Log.d("Button", id_button[k] + " " + dialog_button[k]);
            dialog_button[k].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i= (int) v.getTag();
                    Log.d("dialogg",i+" "+color);
                    listener.onLoginInputComplete(i+1,color);
                    dismiss();
                }
            });
        }
        CircleButton circleButton1 = (CircleButton) findViewById(R.id.circlebutton1);
        circleButton1.setOnClickListener(this);
        CircleButton circleButton2 = (CircleButton) findViewById(R.id.circlebutton2);
        circleButton2.setOnClickListener(this);
        CircleButton circleButton3 = (CircleButton) findViewById(R.id.circlebutton3);
        circleButton3.setOnClickListener(this);
        CircleButton circleButton4 = (CircleButton) findViewById(R.id.circlebutton4);
        circleButton4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circlebutton1:
                for (int k = 0; k < 9; k++) {
                    dialog_button[k] = (Button) findViewById(id_button[k]);
                    dialog_button[k].setTextColor(context.getResources().getColor(R.color.select_color1));
                 }
                color= context.getResources().getColor(R.color.select_color1);
                break;
            case R.id.circlebutton2:
                for (int k = 0; k < 9; k++) {
                    dialog_button[k] = (Button) findViewById(id_button[k]);
                    dialog_button[k].setTextColor(context.getResources().getColor(R.color.select_color2));
                }
                color= context.getResources().getColor(R.color.select_color2);
                break;
            case R.id.circlebutton3:
                for (int k = 0; k < 9; k++) {
                    dialog_button[k] = (Button) findViewById(id_button[k]);
                    dialog_button[k].setTextColor(context.getResources().getColor(R.color.select_color7));
                }
                color= context.getResources().getColor(R.color.select_color7);
                break;
            case R.id.circlebutton4:
                for (int k = 0; k < 9; k++) {
                    dialog_button[k] = (Button) findViewById(id_button[k]);
                    dialog_button[k].setTextColor(context.getResources().getColor(R.color.select_color8));
                }
                color= context.getResources().getColor(R.color.select_color8);
                break;
        }
    }
}
