package sg.edu.np.madexercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MessageGroup extends AppCompatActivity {

    Button b1;
    Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        b1 = (Button) findViewById(R.id.button7);
        b2 = (Button) findViewById(R.id.button8);

        TextView g1 = findViewById(R.id.button7);
        g1.setText("Group 1");
        TextView g2 = findViewById(R.id.button8);
        g2.setText("Group 2");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.messagegroup,new Fragment1()).commit();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.messagegroup,new Fragment2()).commit();
            }
        });
    }
}