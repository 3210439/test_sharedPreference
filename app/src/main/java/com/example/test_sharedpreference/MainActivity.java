package com.example.test_sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private EditText editText2;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        checkBox = findViewById(R.id.checkBox);

        //File이란 파일로 저장해둔 값을 가져오기위한 설정
        SharedPreferences sf = getSharedPreferences("File",MODE_PRIVATE);
        //text1에 값이 있으면 가져오고 두번째 인자는 없을경우 가져오는 값이다.
        String text1 = sf.getString("text1","");

        // text!가 그냥 공백이 아니라면 아이디 저장이된 상태이기 때문에 체크박스를
        // 체크 상태로 변환한다.
        if(!(text1.equals("")))
            checkBox.setChecked(true);

        editText1.setText(text1);


    }

    public void erase(View view) {
        editText1.setText("");
        editText2.setText("");
    }

    public void confirm(View view) {

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences("File",MODE_PRIVATE);
        //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //체크 박스에 체크가 됬다면 아이디를 저장한다.
        if(checkBox.isChecked()) {
            String text1 = editText1.getText().toString();
            editor.putString("text1", text1);
        }
        else
        {
            editor.putString("text1", "");
        }

        // 값을 다 넣었으면 commit으로 완료한다.
        editor.commit();
    }
}
