package com.example.doma.talithakoum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class joinus extends AppCompatActivity {
    private EditText data1, data2, data3, data4;
    private Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joinus);
        NetworkUtil.setNetworkPolicy();
        data1 = (EditText)findViewById(R.id.editText_joinUs_email);
        data2 = (EditText)findViewById(R.id.editText_joinUs_nickname);
        data3 = (EditText)findViewById(R.id.editText_joinUs_pswd);
        data4 = (EditText)findViewById(R.id.editText_joinUs_repswd);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(data1.getText().toString().length()==0){
                    Toast.makeText(joinus.this, "이메일을 입력하세요",Toast.LENGTH_SHORT).show();
                    data1.requestFocus();
                    return;
                }else if(data2.getText().toString().length()==0){
                    Toast.makeText(joinus.this, "닉네임을 입력하세요",Toast.LENGTH_SHORT).show();
                    data2.requestFocus();
                    return;
                }
                else if(data3.getText().toString().length()==0){
                    Toast.makeText(joinus.this, "비밀번호를 입력하세요",Toast.LENGTH_SHORT).show();
                    data3.requestFocus();
                    return;
                }else if(data4.getText().toString().length()==0){
                    Toast.makeText(joinus.this, "비밀번호를 재입력하세요",Toast.LENGTH_SHORT).show();
                    data4.requestFocus();
                    return;
                }else if(!data3.getText().toString().equals(data4.getText().toString())){
                    Toast.makeText(joinus.this, "비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();

                    data3.requestFocus();
                    return;
                }else if(!Util.validateEmail(data1.getText().toString())){
                    Toast.makeText(joinus.this, "잘못된 형식의 이메일 주소입니다.",Toast.LENGTH_SHORT).show();
                    data1.requestFocus();
                    return;
                }else if(!Util.validatePassword(data3.getText().toString())){
                    Toast.makeText(joinus.this, "영문+숫자 조합 6~12자의 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
                    data3.requestFocus();
                    return;
                }else {

                    // 데이터 서버로 보내기전 세팅.
                    ArrayList<String> DBFieldNameList = new ArrayList<String>();
                    DBFieldNameList.add("email");
                    DBFieldNameList.add("nickname");
                    DBFieldNameList.add("password");
                    
                    ArrayList<String> DBDataList = new ArrayList<String>();
                    DBDataList.add(String.valueOf(data1.getText()));
                    DBDataList.add(String.valueOf(data2.getText()));
                    DBDataList.add(String.valueOf(data3.getText()));


                    try {

                    ////
                        PHPRequest request = new PHPRequest("http://222.239.249.90/Data_insert.php");
                        String result = request.PhPtest(DBFieldNameList,DBDataList);
                        if (result.equals("1")) {
                            Toast.makeText(getApplication(), "가입완료", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(joinus.this, login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplication(), "중복된 아이디입니다.", Toast.LENGTH_SHORT).show();
                            data1.requestFocus();
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}

