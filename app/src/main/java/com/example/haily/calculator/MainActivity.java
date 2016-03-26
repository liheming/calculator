package com.example.haily.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String result;//真正的结果
    int op1length = 0;//第一个操作数的长度,为了定位第二个操作数的起止位置
    private boolean isOperate = false;//是否点击过操作符
    private char oprating = '0';//获取对应的操作符
    private double op1 = 0, op2 = 0, preRes = 0;//定义操作数1,操作数2,double类型结果
    private TextView text_show, text_result;//表达式和结果显示textView;
    private Button one, two, three, four, five, six, seven, eight, nine, zero, add, min, mul, div, delete, dot, equ;//所有按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();//调用初始化函数init()
    }

    //初始化各组件,实现按钮监听事件
    private void init() {
        text_show = (TextView) findViewById(R.id.text_show);
        text_result = (TextView) findViewById(R.id.text_result);
        text_show.setText("");
        text_result.setText("");
        one = (Button) this.findViewById(R.id.one);
        two = (Button) this.findViewById(R.id.two);
        three = (Button) this.findViewById(R.id.three);
        four = (Button) this.findViewById(R.id.four);
        five = (Button) this.findViewById(R.id.five);
        six = (Button) this.findViewById(R.id.six);
        seven = (Button) this.findViewById(R.id.seven);
        eight = (Button) this.findViewById(R.id.eight);
        nine = (Button) this.findViewById(R.id.nine);
        zero = (Button) this.findViewById(R.id.zero);
        add = (Button) this.findViewById(R.id.add);
        min = (Button) this.findViewById(R.id.min);
        mul = (Button) this.findViewById(R.id.mul);
        div = (Button) this.findViewById(R.id.div);
        delete = (Button) this.findViewById(R.id.delete);
        dot = (Button) this.findViewById(R.id.dot);
        equ = (Button) this.findViewById(R.id.equ);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        dot.setOnClickListener(this);
        add.setOnClickListener(this);
        min.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        equ.setOnClickListener(this);
        delete.setOnClickListener(this);
        delete.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                text_show.setText("");
                return true;
            }
        });
    }

    private void clickNuber(String s) {
        text_show.append(s);
        if (isOperate) {
            equ();
        }

    }

    @Override
    public void onClick(View v) {
        dot.setEnabled(true);
        switch (v.getId()) {
            case R.id.one:
                clickNuber("1");
                break;
            case R.id.two:
                clickNuber("2");
                break;
            case R.id.three:
                clickNuber("3");
                break;
            case R.id.four:
                clickNuber("4");
                break;
            case R.id.five:
                clickNuber("5");
                break;
            case R.id.six:
                clickNuber("6");
                break;
            case R.id.seven:
                clickNuber("7");
                break;
            case R.id.eight:
                clickNuber("8");
                break;
            case R.id.nine:
                clickNuber("9");
                break;
            case R.id.zero:
                clickNuber("0");
                break;
            case R.id.dot:
                if (!text_show.getText().toString().equals("")) {
                    for (int i = 0; i < text_show.getText().length(); i++) {
                        if (text_show.getText().charAt(i) == '.') {
                            System.out.println(text_show.getText().charAt(i) == '.');
//                            dot.setEnabled(false);
                            return;
                        }
                    }
                    text_show.append(".");
                }
                break;

            case R.id.add://加法
                if (!text_show.getText().toString().equals("")) {
                    op1length = text_show.getText().length();
                    op1 = Double.parseDouble(text_show.getText().toString());
                    System.out.println(op1);
//                    text_show.setText("");
                    text_show.append("＋");
                    oprating = '+';
                    isOperate = true;
                }
                break;
            case R.id.min://减法
                if (!text_show.getText().toString().equals("")) {
                    op1length = text_show.getText().length();
                    op1 = Double.parseDouble(text_show.getText().toString());
//                    text_show.setText("");
                    text_show.append("－");
                    oprating = '-';
                    isOperate = true;
                }
                break;
            case R.id.mul://乘法
                if (!text_show.getText().toString().equals("")) {
                    op1length = text_show.getText().length();
                    op1 = Double.parseDouble(text_show.getText().toString());
//                    text_show.setText("");
                    text_show.append("×");
                    oprating = '*';
                    isOperate = true;
                }
                break;
            case R.id.div://除法
                if (!text_show.getText().toString().equals("")) {
                    op1length = text_show.getText().length();
                    op1 = Double.parseDouble(text_show.getText().toString());
//                    text_show.setText("");
                    text_show.append("÷");
                    oprating = '/';
                    isOperate = true;
                }
                break;
            case R.id.equ:// 计算结果
                if (isOperate) {
                    equ();
                    isOperate = false;
                    text_show.setText(result);
                    text_result.setText("");
                }
                break;
            case R.id.delete://后退和删除

                if (!text_show.getText().toString().equals("")) {
                    text_show.setText(text_show.getText().subSequence(0, text_show.getText().length() - 1));

                    try {
                        if (isOperate) {
                            equ();
                        }
                    } catch (Exception e) {
                        text_result.setText("");
                    }

                    isOperate = false;
                } else {
                    text_show.setText("");

                }
                break;
            default:
        }
    }

    private void equ() {
        if (!text_show.getText().toString().equals("")) {
            System.out.println("error" + text_show.getText().toString().equals(""));
            int op2Length = text_show.getText().length();
//            System.out.println("op1Length"+op1length+"----op2Length"+op2Length);
            String str = text_show.getText().subSequence(op1length + 1, op2Length).toString();
            op2 = Double.parseDouble(str);
            System.out.println(op2);
            preRes = op2;
            switch (oprating) {
                case '+':
                    preRes = add(op1, op2);
                    break;
                case '-':
                    preRes = min(op1, op2);
                    break;
                case '*':
                    preRes = mul(op1, op2);
                    break;
                case '/':
                    preRes = div(op1, op2);
                    break;
            }
            DecimalFormat df = new DecimalFormat("#0.0");
            result = df.format(preRes);
            text_result.setText(result);
        }
    }
    //以下4个方法为四则运算


    public double add(double op1, double op2) {

        return op1 + op2;
    }

    public double min(double op1, double op2) {

        return op1 - op2;
    }

    public double mul(double op1, double op2) {
        return op1 * op2;

    }

    public double div(double op1, double op2) {
        return op1 / op2;
    }

    //以下为菜单初始化
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.about) {
            AlertDialog builder = new AlertDialog.Builder(this).setTitle("关于")
                    .setMessage(
                            "简易计算器1.0\n小数点后保留两位小数\n长按删除所有数字\nmade by haily on 2016.3.26")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
//                            finish();
                        }
                    }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
