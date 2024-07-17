package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public final class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView pointsTextView;
    private FirebaseDatabase firebaseDatabase;
    private FragmentManager fragmentManager;
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;

    private static final String SHARE_URL = "https://raw.githubusercontent.com/sankethp44/Recycle-AppApk/main/app-debug.apk";

    private Map<Integer, Runnable> navigationDrawerActions;
    private Map<Integer, Runnable> bottomNavigationActions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        // Check if a user is authenticated
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String userId = user != null ? user.getUid() : "";
        DatabaseReference userPointsRef = firebaseDatabase.getReference("Users").child(userId).child("points");

        // Find views
        pointsTextView = findViewById(R.id.pointsTextView);

        // Set up ValueEventListener to update pointsTextView
        userPointsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double userPoints = dataSnapshot.getValue(Double.class);
                if (userPoints == null) {
                    userPoints = 0.0;
                }
                pointsTextView.setText("Points earned: " + String.format("%.2f", userPoints));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MainActivity", "Failed to read user points.", databaseError.toException());
            }
        });

        // Set up ActionBarDrawerToggle for navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Set up NavigationView for navigation drawer
        binding.navigationDrawer.setNavigationItemSelectedListener(this);

        // Set up BottomNavigationView
        binding.bottomNavigation.setBackground(null);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return onBottomNavigationItemSelected(item);
            }
        });

        // Set click listeners for FABs
        binding.fab.setOnClickListener(v -> openScannerFragment());
        binding.extendedFab.setOnClickListener(v -> openFragmentWithAnimation(new HowFragment()));
        binding.extendedFabHome.setOnClickListener(v -> openFragmentWithAnimation(new HomeFragment()));

        // Initialize navigation drawer actions
        initializeNavigationDrawerActions();

        // Initialize bottom navigation actions
        initializeBottomNavigationActions();

        // Initialize FragmentManager
        fragmentManager = getSupportFragmentManager();

        // Open the default fragment
        openFragment(new HomeFragment());
    }

    private void initializeNavigationDrawerActions() {
        navigationDrawerActions = new HashMap<>();
        navigationDrawerActions.put(R.id.nav_profile, () -> openFragment(new MyProfileFragment()));
        navigationDrawerActions.put(R.id.nav_newaccount, () -> {
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
            finish();
        });
        navigationDrawerActions.put(R.id.nav_homepage, () -> openFragment(new HomeFragment()));
        navigationDrawerActions.put(R.id.nav_scanner, () -> startActivity(new Intent(MainActivity.this, Scanner123Fragment.class)));
        navigationDrawerActions.put(R.id.nav_locationpage, () -> openFragment(new ExchangePointFragment()));
        navigationDrawerActions.put(R.id.nav_feedback, () -> openFragment(new FeedbackFragment()));
        navigationDrawerActions.put(R.id.nav_logout, () -> {
            firebaseAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
        navigationDrawerActions.put(R.id.nav_share, this::shareContent);
    }

    private void initializeBottomNavigationActions() {
        bottomNavigationActions = new HashMap<>();
        bottomNavigationActions.put(R.id.bottom_home, () -> openFragment(new HomeFragment()));
        bottomNavigationActions.put(R.id.bottom_location, () -> openFragment(new ExchangePointFragment()));
        bottomNavigationActions.put(R.id.bottom_cart, () -> openFragment(new ShopFragment()));
        bottomNavigationActions.put(R.id.bottom_proifle, () -> openFragmentWithAnimation(new MyProfileFragment()));
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    private void openFragmentWithAnimation(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openScannerFragment() {
        Intent intent = new Intent(this, Scanner123Fragment.class);
        startActivity(intent);
    }

    private boolean onBottomNavigationItemSelected(@NonNull MenuItem item) {
        Runnable action = bottomNavigationActions.get(item.getItemId());
        if (action != null) {
            action.run();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Runnable action = navigationDrawerActions.get(item.getItemId());
        if (action != null) {
            action.run();
            binding.drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_container);
            if (!(currentFragment instanceof HomeFragment)) {
                openFragment(new HomeFragment());
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@Nullable Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_share) {
            shareContent();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareContent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share App");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text) + SHARE_URL);
        startActivity(Intent.createChooser(intent, "Share via"));
    }
}
