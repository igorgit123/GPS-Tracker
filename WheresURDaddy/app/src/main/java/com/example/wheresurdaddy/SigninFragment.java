package com.example.wheresurdaddy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SigninFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment {
    TextInputEditText signin_email;
    TextInputEditText signin_password;
    Button signin_button;
    private FirebaseAuth firebaseAuth;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FirebaseAuth mAuth;
   // private ActivityEmailpasswordBinding mBinding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SigninFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SigninFragment newInstance(String param1, String param2) {
        SigninFragment fragment = new SigninFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signin, container, false);
        signin_button = (Button) v.findViewById(R.id.signin_button);
        signin_email = (TextInputEditText) v.findViewById(R.id.signin_email);
        signin_password = (TextInputEditText) v.findViewById(R.id.signin_password);

        //TextView z intentom za activityForID
        TextView tv = (TextView) v.findViewById(R.id.textview_withcolor);
        SpannableString ss= new SpannableString("Or you can share your location by clicking here!");
        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLUE);
        ss.setSpan(fcs, 43, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(getActivity() , ActivityForID.class);

                startActivity(i);
            }
        };
        ss.setSpan(cs, 43, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ss);
        tv.setMovementMethod(LinkMovementMethod.getInstance());

        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signin_email.getText().toString().trim();
                String password = signin_password.getText().toString().trim();


                if (TextUtils.isEmpty(email)){

                    Toast.makeText(getActivity(), "Prosim vnesite email", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(password)){


                    Toast.makeText(getActivity(), "Prosim vnesite geslo", Toast.LENGTH_SHORT).show();

                }
                if (password.length()<6){

                    Toast.makeText(getActivity(), "Prekratko geslo, vnesite vec kot 6 znakov", Toast.LENGTH_SHORT).show();
                }
                ((ActivitySignInUp) getActivity()).signIn(email,password);
            }
        });


        return v;
    }


}
