package sg.edu.np.madexercise2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    private ImageView imageView2;
    private ArrayList<User> mUser = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
//        while (mUser.size()<20){
//            Boolean b = Math.random() < 0.5;
//            User user = new User(("Name" + Math.round((Math.random() * 1000000000)+1000000000)), ("Description " + String.valueOf(Math.round((Math.random() * 1000000000)+1000000000))),0, b);
//            mUser.add(user);
//        }

        DBHandler db = new DBHandler(this);

        mUser = db.getUsers();

//        for (int i=0; i<mUser.size(); i++){
//            User user = mUser.get(i);
//            if (user.followed == false){
//                db.insertUser(user.name,user.description,i,1);
//            }
//            else{
//                db.insertUser(user.name,user.description,i,0);
//            }
//        }


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mUser,this, db);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        imageView2 = findViewById(R.id.image1);
        
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        DBHandler db = new DBHandler(this);
//        mUser = db.getUsers();
//    }
}