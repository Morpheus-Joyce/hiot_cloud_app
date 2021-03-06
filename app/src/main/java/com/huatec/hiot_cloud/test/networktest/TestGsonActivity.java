package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huatec.hiot_cloud.R;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TestGsonActivity extends AppCompatActivity {

    private static final String TAG = "TestGsonActivity";
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gson);

        //json转对象
        Button btnFromJson = findViewById(R.id.btn_from_json);
        btnFromJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = gson.fromJson("{\n" +
                        "\t\"height\": 175,\n" +
                        "\t\"id\": 11,\n" +
                        "\t\"graduted\": false,\n" +
                        "\t\"name\": \"李四\"\n" +
                        "}",Student.class);
                if (student != null) {
                    String str = String.format("姓名：%s,id：%s,身高：%d,是否毕业：%b",
                            student.getName(), student.getId(),student.getHeight(), student.isGraduted());
                    Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //对象转json
        Button btnToJson = findViewById(R.id.btn_to_json);
        btnToJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student2 = new Student();
                student2.setHeight(175);
                student2.setId(11);
                student2.setName("李四");
                student2.setGraduted(true);
                String json = gson.toJson(student2);
                Log.d(TAG, "onClick: " + json);
            }
        });

        //json转list
        Button btnGsonList = findViewById(R.id.btn_gson_list);
        btnGsonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "[\n" +
                        "\t{\n" +
                        "\t\t\"height\": 180,\n" +
                        "\t\t\"id\": 10,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"height\": 175,\n" +
                        "\t\t\"id\": 11,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"李四\"\n" +
                        "\t},\n" +
                        "\t{\n" +
                        "\t\t\"height\": 170,\n" +
                        "\t\t\"id\": 12,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"王五\"\n" +
                        "\t}\n" +
                        "]";
                Type type = new TypeToken<List<Student>>(){}.getType();
                List<Student> studentList = gson.fromJson(json, type);
                String str = "";
                if (studentList != null || !studentList.isEmpty()){
                    for (Student student : studentList){
                        str = str + String.format("姓名：%s,id：%s,身高：%d,是否毕业：%b",
                                student.getName(), student.getId(), student.getHeight(), student.isGraduted());
                    }
                    Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //json转map
        Button btnGsonMap = findViewById(R.id.btn_gson_map);
        btnGsonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "{\n" +
                        "\t\"1\":{\n" +
                        "\t\t\"height\": 180,\n" +
                        "\t\t\"id\": 10,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"张三\"\n" +
                        "\t},\n" +
                        "\t\"2\":{\n" +
                        "\t\t\"height\": 175,\n" +
                        "\t\t\"id\": 11,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"李四\"\n" +
                        "\t},\n" +
                        "\t\"3\":{\n" +
                        "\t\t\"height\": 170,\n" +
                        "\t\t\"id\": 12,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"王五\"\n" +
                        "\t}\n" +
                        "}";
                Type type = new TypeToken<Map<String, Student>>(){}.getType();
                Map<String, Student> map = gson.fromJson(json, type);
                String str = "";
                if (map != null){
                    for (String key : map.keySet()){
                        str = str + String.format("序号：%s,姓名：%s,id：%s,身高：%d,是否毕业：%b",
                                key, map.get(key).getName(), map.get(key).getId(), map.get(key).getHeight(), map.get(key).isGraduted());
                    }
                    Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //json转嵌套对象
        Button btnGsonNest = findViewById(R.id.btn_gson_nest);
        btnGsonNest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = "{\n" +
                        "\t\"data\":\n" +
                        "\t{\n" +
                        "\t\t\"height\": 175,\n" +
                        "\t\t\"id\": 11,\n" +
                        "\t\t\"graduted\": false,\n" +
                        "\t\t\"name\": \"李四\"\n" +
                        "\t},\n" +
                        "\t\"status\":1,\n" +
                        "\t\"msg\":\"正常\"\n" +
                        "}\n";
                Type type = new TypeToken<ResultBase<Student>>(){}.getType();
                ResultBase<Student> resultBase = gson.fromJson(json, type);
                String str = String.format("姓名：%s,id：%s,身高：%d,是否毕业：%b",
                        resultBase.data.getName(), resultBase.data.getId(), resultBase.data.getHeight(), resultBase.data.isGraduted());
                Toast.makeText(TestGsonActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
