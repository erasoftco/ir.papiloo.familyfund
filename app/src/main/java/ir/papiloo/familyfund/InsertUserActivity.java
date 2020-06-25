package ir.papiloo.familyfund;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.io.IOException;

public class InsertUserActivity extends AppCompatActivity {

    Button btnsend;
    TextView name, user, pass, tresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_user);


        name = findViewById(R.id.txtname);
        user = findViewById(R.id.txtuser);
        pass = findViewById(R.id.txtpass);
        tresult = findViewById(R.id.result);
        btnsend = findViewById(R.id.btnSend);

        btnsend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (isOnline() == false) {
                    tresult.setText("اتصال به اینترنت را بررسی نمایید");
                    return;
                }


                String n, u, p;

                n = name.getText().toString();
                u = user.getText().toString();
                p = pass.getText().toString();

                if (n.isEmpty()) {
                    tresult.setText("نام را بنویسید");
                    return;
                }

                if (u.isEmpty()) {
                    tresult.setText("نام کاربری را بنویسید");
                    return;
                }

                if (p.isEmpty()) {
                    tresult.setText("کلمه عبور را بنویسید");
                    return;
                }

                JSONObject jInnerObject = new JSONObject();


            }
        });

    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
