package com.example.taku.briteanddelight.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taku.briteanddelight.R;
import com.example.taku.briteanddelight.data.User;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Action1<List<User>> {

    List<User> users;

    public UserAdapter() {
        users = new ArrayList<>();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.userNameTextView.setText(user.getFullName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public void call(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userNameTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            userNameTextView = (TextView) itemView.findViewById(R.id.userNameTextView);
        }

    }
}
