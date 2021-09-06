package com.project.imdb_clone_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Settings extends Fragment {

    Button sign_out_btn;
    Button delete_acc_btn;
    TextView favourites_tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        sign_out_btn=view.findViewById(R.id.signOut_btn);
        delete_acc_btn=view.findViewById(R.id.delete_account_btn);
        favourites_tv=view.findViewById(R.id.user_frag_favourites_textView);


        sign_out_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPreferences.setLoginStatus(getContext(),false);
                startActivity(new Intent(getContext(), Activity_Login.class));
            }
        });

        delete_acc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySharedPreferences.setLoginStatus(getContext(),false);
                MySharedPreferences.removeUser(getContext());
                startActivity(new Intent(getContext(), Activity_SignUp_or_Login.class));
            }
        });

        return view;
    }
}
