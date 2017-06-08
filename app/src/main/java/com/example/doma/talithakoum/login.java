package com.example.doma.talithakoum;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class login extends AppCompatActivity {

    CallbackManager callbackManager;

    String what;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);


        final EditText editText_email = (EditText) findViewById(R.id.editText_login_email);
        final EditText editText_pswd = (EditText) findViewById(R.id.editText_login_pswd);


        Button Button = (Button) findViewById(R.id.button2);
        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(login.this, joinus.class);

                startActivity(intent);
            }
        });

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton_login);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = String.valueOf(editText_email.getText());
                String pswd = String.valueOf(editText_pswd.getText());

                ArrayList<String> DBFieldNameList = new ArrayList<String>();
                ArrayList<String> DBDataList = new ArrayList<String>();

                DBFieldNameList.add("email");
                DBFieldNameList.add("password");

                DBDataList.add(email);
                DBDataList.add(pswd);


                if (editText_email.getText().toString().length() == 0) {
                    Toast.makeText(login.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    editText_email.requestFocus();
                    return;
                } else if (editText_pswd.getText().toString().length() == 0) {
                    Toast.makeText(login.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    editText_pswd.requestFocus();
                    return;
                } else if (!Util.validateEmail(editText_email.getText().toString())) {
                    Toast.makeText(login.this, "잘못된 형식의 이메일 주소입니다.", Toast.LENGTH_SHORT).show();
                    editText_email.requestFocus();
                    return;
                } else if (!Util.validatePassword(editText_pswd.getText().toString())) {
                    Toast.makeText(login.this, "영문+숫자 조합 6~12자의 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    editText_pswd.requestFocus();
                    return;
                } else {
                    insertToDatabase(email, pswd);
                }


//                try {
//                    PHPRequest request = new PHPRequest("http://222.239.249.90/getdata.php");
//
//                    String result = request.PhPtest(DBFieldNameList,DBDataList);
//                    if (result.equals("1")) {
//                        Toast.makeText(getApplication(), "로그인", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getApplication(), "로그인 실패", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }

//                Intent intent =new Intent(login.this, home2.class);
//                startActivity(intent);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.v("result",object.toString());
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("LoginErr",error.toString());

            }
        });
    }

    private String insertToDatabase(String email, String password) {

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(login.this, "대기중..", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals(" 1")) {
                    Toast.makeText(getApplication(), "로그인", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplication(), "로그인 실패", Toast.LENGTH_SHORT).show();
                }
                  loading.dismiss();
//                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String email = (String) params[0];
                    String password = (String) params[1];

                    String link = "http://222.239.249.90/getdata.php";
                    String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                    data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    //HttpURLConnection은 기본적으로 세션 유지를 위한 쿠키 관리를 하지 않는다.
                    //세션 유지를 위해서 클라이언트 쪽에서 서버가 Set-Cookie 라는 헤더로 보내주는 세션 쿠키 값을 저장하고 있다가 다음 연결 시에 헤더에 세션 쿠키를 넣어서 보내주면 서버에서는 해당 세션의 값을 사용할 수 있도록 해줌.

//                    String cookieString = android.webkit.CookieManager.getInstance().getCookie(link);
//                    if(cookieString != null){
//                        conn.setRequestProperty("Cookie", cookieString);
//                    }

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }

                    what = sb.toString();
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        return String.valueOf(task.execute(email, password));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
