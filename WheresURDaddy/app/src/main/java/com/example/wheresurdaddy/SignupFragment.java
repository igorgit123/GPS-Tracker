package com.example.wheresurdaddy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {


    TextInputEditText signupEmail;
    TextInputEditText signupPassword;
    TextInputEditText repeatPassword;
    Button signupButton;
    TextInputLayout errorLayout;
    private FirebaseAuth firebaseAuth;




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        View v = inflater.inflate(R.layout.fragment_signup, container, false);

        signupButton = (Button) v.findViewById(R.id.signup_button);
        signupEmail = (TextInputEditText) v.findViewById(R.id.signup_email);
        signupPassword = (TextInputEditText) v.findViewById(R.id.signup_password);
        repeatPassword = (TextInputEditText) v.findViewById(R.id.repeat_password);
        errorLayout = (TextInputLayout) v.findViewById(R.id.tf5);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();
                String confpass = repeatPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getActivity(), "Prosim vnesite email", Toast.LENGTH_SHORT).show();

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getActivity(), "Prosim vnesite geslo", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(confpass)){
                    Toast.makeText(getActivity(), "Prosim potrdite geslo", Toast.LENGTH_SHORT).show();

                }
                if (password.length()<6){
                    Toast.makeText(getActivity(), "Prekratko geslo, vnesite vec kot 6 znakov", Toast.LENGTH_SHORT).show();
                }
                if(password.equals(confpass)){

                    ((ActivitySignInUp) getActivity()).signUp(email,password);


                }




            }
        });





        return v;

    }
}
