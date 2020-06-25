package ir.papiloo.familyfund;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private APIGettingPosts apiGettingPosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiGettingPosts= new APIGettingPosts(this);

        Button nextpage = (Button) findViewById(R.id.btninsertuser);
        Button button = (Button) findViewById(R.id.btnget);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                apiGettingPosts.getPost(MainActivity.this);
            }
        });

        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, InsertUserActivity.class));

            }
        });
    }

    @Override
    public void onReceived(Posts posts){
        if(posts != null){
            Toast.makeText(this, posts.getTitle(), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, posts.getFullPost(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "خطا در دریافت اطلاعات", Toast.LENGTH_LONG).show();
        }
    }
}
