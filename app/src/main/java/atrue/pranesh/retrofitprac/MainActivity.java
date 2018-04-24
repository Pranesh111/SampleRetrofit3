package atrue.pranesh.retrofitprac;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import atrue.pranesh.retrofitprac.ui.HomeUserFragment;


public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    Toolbar my_toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        frameLayout=findViewById(R.id.base_container);
        my_toolbar=findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        setHomeFragment();
    }

    private void setHomeFragment() {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.base_container,new HomeUserFragment(),"Home");
        fragmentTransaction.commit();

    }
}
