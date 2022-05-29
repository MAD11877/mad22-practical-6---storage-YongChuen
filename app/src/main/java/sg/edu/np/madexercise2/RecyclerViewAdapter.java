package sg.edu.np.madexercise2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<User> mImageUser = new ArrayList<>();
    private Context mContext;
    private DBHandler Db;

    public RecyclerViewAdapter(ArrayList<User> imageUser, Context context, DBHandler db) {
        mImageUser = imageUser;
        mContext = context;
        Db = db;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Intent act = new Intent(mContext, MainActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Profile");
        builder.setMessage(mImageUser.get(position).name);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                User u = mImageUser.get(position);
                User upU = Db.updateUserOnClick(u.getName());

                act.putExtra("key", upU);
                mContext.startActivity(act);
            }

        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        holder.imageName.setText(mImageUser.get(position).name);
        holder.imageDesc.setText(mImageUser.get(position).description);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mImageUser.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView imageName;
        TextView imageDesc;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image1);
            imageName = itemView.findViewById(R.id.image_name);
            imageDesc = itemView.findViewById(R.id.image_desc);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mImageUser.get(position).name.charAt(mImageUser.get(position).name.length() - 1) == '7') {
            return 1;
        } else {
            return 0;
        }
    }
}
