package com.example.wheresurdaddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ActivitySignInUp extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tabLayout;
    private SigninFragment signinFragment;
    private SignupFragment signupFragment;







    private FirebaseAuth firebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinup);








        firebaseAuth = FirebaseAuth.getInstance();

        viewpager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        signinFragment = new SigninFragment();
        signupFragment = new SignupFragment();

        tabLayout.setupWithViewPager(viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(signinFragment, "SIGN IN");
        viewPagerAdapter.addFragment(signupFragment, "SIGN UP");
        viewpager.setAdapter(viewPagerAdapter);



    }

    public void signUp(final String email, final String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(ActivitySignInUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if(task.isSuccessful()){

                                Intent i = new Intent(ActivitySignInUp.this, UserActivity.class);
                                i.putExtra("email", email);
                                startActivity(i);

                        } else {

                                Toast.makeText(ActivitySignInUp.this, "Neuspesna prijava", Toast.LENGTH_SHORT).show();
                        }


                    }
                }
                });
    }

    public void signIn (final String email, final String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ActivitySignInUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(ActivitySignInUp.this, UserActivity.class);
                            i.putExtra("email", email);
                            startActivity(i);


                        } else {

                            Toast.makeText(ActivitySignInUp.this, "Neuspesna prijava ali nepravilni podatki", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }







    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List <Fragment> fragments = new ArrayList<>();
        private List <String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public void addFragment (Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitles.add(title);

        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }
}
