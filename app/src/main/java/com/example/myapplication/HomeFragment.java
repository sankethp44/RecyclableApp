package com.example.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R.id;
import com.example.myapplication.R.layout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HomeFragment extends Fragment {
    private static HomeFragment this$0;
    private TextView pointsTextView;
    private DatabaseReference userPointsRef;

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(layout.fragment_home, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        View var3 = view.findViewById(id.pointsTextView);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(...)");
        this.pointsTextView = (TextView)var3;
        FirebaseDatabase var4 = FirebaseDatabase.getInstance();
        Intrinsics.checkNotNullExpressionValue(var4, "getInstance(...)");
        FirebaseDatabase firebaseDatabase = var4;
        FirebaseUser var5 = FirebaseAuth.getInstance().getCurrentUser();
        String userId = var5 != null ? var5.getUid() : null;
        DatabaseReference var8 = firebaseDatabase.getReference("Users").child(userId != null ? userId : "").child("points");
        Intrinsics.checkNotNullExpressionValue(var8, "child(...)");
        this.userPointsRef = var8;
        var8 = this.userPointsRef;
        DatabaseReference var10000;
        if (var8 != null) {
            var10000 = var8;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("userPointsRef");
            var10000 = null;
        }

        var10000.addValueEventListener((ValueEventListener)(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intrinsics.checkNotNullParameter(dataSnapshot, "dataSnapshot");
                Double var5 = (Double)dataSnapshot.getValue(Double.TYPE);
                Double var4 = var5 != null ? var5 : 0.0;
                double userPoints = ((Number)var4).doubleValue();
                TextView var6 = HomeFragment.this.pointsTextView;
                TextView var10000;
                if (var6 != null) {
                    var10000 = var6;
                } else {
                    Intrinsics.throwUninitializedPropertyAccessException("pointsTextView");
                    var10000 = null;
                }

                var10000.setText((CharSequence)("Points earned: " + userPoints));
            }

            public void onCancelled(DatabaseError databaseError) {
                Intrinsics.checkNotNullParameter(databaseError, "databaseError");
                Log.e("HomeFragment", "Failed to read user points.", (Throwable)databaseError.toException());
            }
        }));
        ImageView imageView1 = view.findViewById(R.id.image1);
        if (imageView1 != null) {
            imageView1.setOnClickListener(v -> {
                String url = "https://www.bottlesforchange.in/about-pet";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        ImageView imageView2 = view.findViewById(R.id.image2);
        if (imageView2 != null) {
            imageView2.setOnClickListener(v -> {
                String url = "https://www.rubicon.com/blog/paper-recycling-process/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        ImageView imageView3 = view.findViewById(R.id.image3);
        if (imageView3 != null) {
            imageView3.setOnClickListener(v -> {
                String url = "https://www.downtoearth.org.in/blog/waste/recycling-of-e-waste-in-india-and-its-potential-64034";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        ImageView imageView4 = view.findViewById(R.id.image4);
        if (imageView4 != null) {
            imageView4.setOnClickListener(v -> {
                String url ="https://greensutra.in/glass-recycling-process/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        ImageView imageView5 = view.findViewById(R.id.image5);
        if (imageView5 != null) {
            imageView5.setOnClickListener(v -> {
                String url = "https://www.recyclenow.com/how-to-recycle/can-recycling";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            });
        }
        this.hideFab();
    }

    private final void hideFab() {
        View var2 = this.requireActivity().findViewById(id.extendedFabHome);
        Intrinsics.checkNotNullExpressionValue(var2, "findViewById(...)");
        ExtendedFloatingActionButton extendedFabHome = (ExtendedFloatingActionButton)var2;
        extendedFabHome.setVisibility(View.GONE);
    }

    private final void showFab() {
        View var2 = this.requireActivity().findViewById(id.extendedFab);
        Intrinsics.checkNotNullExpressionValue(var2, "findViewById(...)");
        ExtendedFloatingActionButton extendedFab = (ExtendedFloatingActionButton)var2;
        extendedFab.setVisibility(View.VISIBLE);
        View var3 = this.requireActivity().findViewById(id.fab);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(...)");
        FloatingActionButton regularFab = (FloatingActionButton)var3;
        regularFab.setVisibility(View.VISIBLE);
        View var4 = this.requireActivity().findViewById(id.bottom_navigation);
        Intrinsics.checkNotNullExpressionValue(var4, "findViewById(...)");
        BottomNavigationView home = (BottomNavigationView)var4;
        home.setVisibility(View.VISIBLE);
        View var5 = this.requireActivity().findViewById(id.bottomAppBar);
        Intrinsics.checkNotNullExpressionValue(var5, "findViewById(...)");
        BottomAppBar appbarhome = (BottomAppBar)var5;
        appbarhome.setVisibility(View.VISIBLE);
    }

    public void onResume() {
        super.onResume();
        this.showFab();
    }

    private final void openLinkInChrome(String link) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(link));
        this.startActivity(intent);
    }

    private static final void onViewCreated$lambda$0(View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openLinkInChrome("https://www.bottlesforchange.in/about-pet");
    }

    private static final void onViewCreated$lambda$1(View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openLinkInChrome("https://www.rubicon.com/blog/paper-recycling-process/");
    }

    private static final void onViewCreated$lambda$2(View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openLinkInChrome("https://www.downtoearth.org.in/blog/waste/recycling-of-e-waste-in-india-and-its-potential-64034");
    }

    private static final void onViewCreated$lambda$3(View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openLinkInChrome("https://greensutra.in/glass-recycling-process/");
    }

    private static final void onViewCreated$lambda$4(View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openLinkInChrome("https://www.recyclenow.com/how-to-recycle/can-recycling");
    }

    // $FF: synthetic method
    public void onDestroyView() {
        super.onDestroyView();
    }
}
