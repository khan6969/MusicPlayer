package SP19.BSE_O17_ASSIGN_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
     FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigationView = findViewById(R.id.nav);
        navigationView.setOnNavigationItemSelectedListener(nav);
        if (savedInstanceState == null) {
            navigationView.setSelectedItemId(R.id.home);
            fragmentManager = getSupportFragmentManager();
            Home homeFragment = new Home();
            fragmentManager.beginTransaction().replace(R.id.fragmentc,homeFragment).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener nav =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.home:
                            fragment = new Home();
                            break;

                        case R.id.music:

                            fragment = new Music();

                            break;

                        case R.id.Account:
                            fragment = new Account();
                            break;

                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentc,fragment).commit();
                    return true;

                }

            };

}