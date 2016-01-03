package com.example.narayankoirala.androidreside;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {
    RecyclerView rv;
    private Object list;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View row = inflater.inflate(R.layout.fragment_home, container, false);

        rv = (RecyclerView) row.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new MyAdapter(getActivity(), getList()));
        return row;
    }


    public ArrayList<Model> getList() {
        ArrayList<Model> mList = new ArrayList<Model>();
        mList.add(new Model("Hackthone without Internet", 900));
        mList.add(new Model("Drowry de natra mardinxu ", 800));
        mList.add(new Model("AAphu le chene ko manche lai matra gass diyo ", 900));
        mList.add(new Model("Hackthone without Internet", 900));
        mList.add(new Model("Hackthone without Internet", 900));


        return mList;
    }

    public class Model {
        String details;
        int likesCount;


        public Model(String details, int likesCount) {
            this.details = details;
            this.likesCount = likesCount;
        }

        public String getDetails() {
            return details;
        }

        public int getLikesCount() {
            return likesCount;
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        Context context;
        ArrayList<Model> mList;

        public MyAdapter(Context context, ArrayList<Model> mList) {
            this.context = context;
            this.mList = mList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View row = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            MyViewHolder holder = new MyViewHolder(row);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.cv.setRadius(30);
            holder.details.setTextColor(Color.BLACK);
            holder.likesCount.setTextColor(Color.BLACK);
            holder.details.setText(mList.get(position).getDetails());
            holder.likesCount.setText(mList.get(position).getLikesCount() + " likes");
            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(),VideoPlayBack.class);
                    intent.putExtra("id",position);
                    startActivity(intent);
                }
            });
  }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView details;
        TextView likesCount;
        Button view;

        public MyViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            details = (TextView) itemView.findViewById(R.id.details);
            likesCount = (TextView) itemView.findViewById(R.id.likesCount);
            view = (Button)itemView.findViewById(R.id.btn_view);
        }
    }
}