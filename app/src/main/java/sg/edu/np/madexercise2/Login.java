package sg.edu.np.madexercise2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    Button loginBtn;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.loginButton);
        username = findViewById(R.id.UsernameEditText);
        password = findViewById(R.id.PasswordEditText);

        firebaseDatabase = FirebaseDatabase.getInstance("https://mad-exercise-2a341-default-rtdb.asia-southeast1.firebasedatabase.app");
        myRef = firebaseDatabase.getReference("Users");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                String uPassword = password.getText().toString();
                if (TextUtils.isEmpty(uName) && TextUtils.isEmpty(uPassword)) {
                    Toast.makeText(Login.this, "Please enter Username and Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    myRef.child(uName).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            ArrayList<String> loginCreds = new ArrayList<>();
                            for(DataSnapshot snapshot : task.getResult().getChildren()){
                                loginCreds.add(snapshot.getValue().toString());
                            }

                            if(loginCreds.contains(uName) && loginCreds.contains(uPassword)){
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this, ListActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        });
    }
}