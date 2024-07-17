package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R.id;
import com.example.myapplication.R.layout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
        mv = {1, 9, 0},
        k = 1,
        xi = 48,
        d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J&\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\u001a\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\u0010\u001a\u00020\u0004H\u0002¨\u0006\u0011"},
        d2 = {"Lcom/example/recycleapp/HowFragment;", "Landroidx/fragment/app/Fragment;", "()V", "hideFab", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "showFab", "app_debug"}
)
public final class HowFragment extends Fragment {
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(layout.fragment_how, container, false);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.hideFab();
        TextView textViewHow = (TextView)view.findViewById(id.TextView);
        ViewCompat.setTransitionName((View)textViewHow, "howText");
        String paragraphText = "Our app is designed to help users understand the recycling process and make eco-friendly choices in their everyday lives. Here's how you can use our app:\n\n1. Scan Objects: Use the scanner feature to determine whether an object is recyclable or not.\n\n2. Find Exchange Points: Locate nearby exchange points where you can drop off recyclable items.\n\n3. Earn Points: Earn points every time you recycle. These points can be redeemed for rewards in our app.\n\n4. Shop: Browse through our store and purchase eco-friendly products using your earned points.\n\n5. Get Involved: Spread awareness about recycling and environmental sustainability by sharing our app with friends and family.\n\nWe hope our app inspires you to adopt greener habits and contribute to a healthier planet. Thank you for joining us in the journey towards a more sustainable future!";
        textViewHow.setText((CharSequence)paragraphText);
    }

    private final void hideFab() {
        View var2 = this.requireActivity().findViewById(id.extendedFabHome);
        Intrinsics.checkNotNullExpressionValue(var2, "findViewById(...)");
        ExtendedFloatingActionButton extendedFabHome = (ExtendedFloatingActionButton)var2;
        extendedFabHome.setVisibility(8);
    }

    private final void showFab() {
        View var2 = this.requireActivity().findViewById(id.extendedFab);
        Intrinsics.checkNotNullExpressionValue(var2, "findViewById(...)");
        ExtendedFloatingActionButton extendedFab = (ExtendedFloatingActionButton)var2;
        extendedFab.setVisibility(0);
        View var3 = this.requireActivity().findViewById(id.fab);
        Intrinsics.checkNotNullExpressionValue(var3, "findViewById(...)");
        FloatingActionButton regularFab = (FloatingActionButton)var3;
        regularFab.setVisibility(0);
        View var4 = this.requireActivity().findViewById(id.bottomAppBar);
        Intrinsics.checkNotNullExpressionValue(var4, "findViewById(...)");
        BottomAppBar appbarhome = (BottomAppBar)var4;
        appbarhome.setVisibility(0);
        View var5 = this.requireActivity().findViewById(id.bottom_navigation);
        Intrinsics.checkNotNullExpressionValue(var5, "findViewById(...)");
        BottomNavigationView home = (BottomNavigationView)var5;
        home.setVisibility(0);
    }

    public void onResume() {
        super.onResume();
        this.showFab();
    }

    // $FF: synthetic method
    public void onDestroyView() {
        super.onDestroyView();
    }
}
