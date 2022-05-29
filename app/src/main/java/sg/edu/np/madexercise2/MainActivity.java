package sg.edu.np.madexercise2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button3;
    int fInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent act = new Intent(MainActivity.this, MessageGroup.class);

        Intent receivingEnd = getIntent();
        User u = (User)receivingEnd.getSerializableExtra("key");

        DBHandler db = new DBHandler(this);//Context needs to change!!!!!!!!!!!!!!!!!!!!!!!!!

        Button btn = (Button)findViewById(R.id.button2);
        btn.setText(u.followed ? "Unfollow" : "Follow");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                u.followed = !u.followed;
                btn.setText(u.followed ? "Unfollow" : "Follow");
                Toast.makeText(getApplicationContext(), u.followed? "Followed":"Unfollowed", Toast.LENGTH_SHORT).show();

                db.updateUser(u);
            }
        });

        TextView name = findViewById(R.id.textView);
        name.setText(u.name);

        TextView desc = findViewById(R.id.textView2);
        desc.setText(u.description);

        button3 = (Button) findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(act);
            }
        });

    }

}