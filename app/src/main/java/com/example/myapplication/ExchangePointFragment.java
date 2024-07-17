package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.myapplication.R.array;
import com.example.myapplication.R.id;
import com.example.myapplication.R.layout;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ExchangePointFragment extends Fragment {
    @NotNull
    private final Map imageTextMap;
    @NotNull
    private final Map cityImagesMap;
    @NotNull
    private final Map customLinksMap;
    private Spinner spinner;
    private LinearLayout imageContainer;

    public ExchangePointFragment() {
        Pair[] var1 = new Pair[]{TuplesKt.to("https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no", "Aluminum Cans: Aluminum cans typically range from ₹18 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹19 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹10 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹5 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹2 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹6 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled."), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020", "Aluminum Cans: Aluminum cans typically range from ₹18 to ₹36 per kilogram.\n\nPlastic Bottles: Depending on the type of plastic and local demand, plastic bottles can be worth anywhere from ₹8 to ₹20 per kilogram.\n\nGlass Bottles: Clear glass bottles usually fetch around ₹4 to ₹8 per kilogram, while colored glass may have slightly lower rates.\n\nNewspapers: Newspapers are generally valued at around ₹1 to ₹2 per kilogram.\n\nCardboard: Cardboard prices vary but usually fall between ₹1 to ₹5 per kilogram, depending on its condition and demand.\n\nSteel Cans: Steel cans typically bring in around ₹4 to ₹8 per kilogram.\n\nElectronics (E-Waste): Electronics recycling rates can vary widely based on the type and condition of the electronics. Some items, like old cell phones or computer parts, may have recycling incentives or trade-in programs.\n\nCar Batteries: Car batteries are usually worth around ₹350 to ₹700 each when recycled.")};
        this.imageTextMap = MapsKt.mapOf(var1);
        var1 = new Pair[6];
        String[] var2 = new String[]{"https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no", "https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020"};
        var1[0] = TuplesKt.to("Bengaluru", CollectionsKt.listOf(var2));
        var2 = new String[]{"https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020", "https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175", "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111"};
        var1[1] = TuplesKt.to("Mumbai", CollectionsKt.listOf(var2));
        var2 = new String[]{"https://lh3.googleusercontent.com/p/AF1QipONbUso0kSxswE65xYj20Bhui-ggLzp4OpsOOGI=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020","https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020"};
        var1[2] = TuplesKt.to("Delhi", CollectionsKt.listOf(var2));
        var2 = new String[]{"https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no", "https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020"};
        var1[3] = TuplesKt.to("Chennai", CollectionsKt.listOf(var2));
        var2 = new String[]{"https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020"};
        var1[4] = TuplesKt.to("Kolkata", CollectionsKt.listOf(var2));
        var2 = new String[]{"https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020"};
        var1[5] = TuplesKt.to("Hyderabad", CollectionsKt.listOf(var2));
        this.cityImagesMap = MapsKt.mapOf(var1);
        var1 = new Pair[]{TuplesKt.to("https://lh5.googleusercontent.com/p/AF1QipNCjCLXT4YpqGJx5niw9zTFqjDJQROoblMUyjvw=w260-h175-n-k-no", "https://www.google.com/maps/dir/12.9883,77.5847/15%2F4,+6th+cross+Azad+Nagar,+near+Government+Library,+Chamrajpet,+Bengaluru,+Karnataka+560018/@12.9883,77.5847,12z/data=!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae152ab381c5c1:0x570bef534b3e8883!2m2!1d77.5571269!2d12.9565324?entry=ttu"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOZhNTyx7J9lX7hE7IiMbCQWrrcjzoaj01CSVyQ=s1360-w1360-h1020", "https://www.google.com/maps/dir//Bangalore+Ewaste+Recyle+center(scrap+pickup),+154,+4th+A+Cross+Rd,+Seethappa+Layout,+Bangalore,+Bengaluru,+Karnataka+560032/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3bae171ba50c7bd3:0x4fc20a7682a35418?sa=X&ved=1t:57443&ictx=111"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipN1DEeXjyHhfx2YKAaELpOZ1dzLMm-uruCTyka6=s1360-w1360-h1020", "https://www.google.com/maps/dir/12.9883,77.5847/No+23,+E-Waste+Collection+Centre+in+Bangalore,+23+rd+A,+Marenahalli+Rd,+2nd+Phase,+J.+P.+Nagar,+Bengaluru,+Karnataka+540040/@12.9521286,77.542019,13z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bae1531285e6067:0xa6ab6630e3fbeb5!2m2!1d77.5863373!2d12.9147826?entry=ttu"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOGtJ8S9e1LFaFxaRsrZziCRltcXsI12tKRR4WV=s1360-w1360-h1020", "https://www.google.com/maps/dir//1st+Floor,+Unit+No+31-32,+Ecostar+Recycling+-+E+Waste+Recycling+Mumbai,+Recycling+Centre,+Khuraiya+Estate,+CST+Road,+opposite+Ashok+Apartments,+Kalina,+Santacruz+East,+Mumbai,+Maharashtra+400098/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7c929f585420f:0x24c9426bb0e18797?sa=X&ved=1t:57443&ictx=111"), TuplesKt.to("https://streetviewpixels-pa.googleapis.com/v1/thumbnail?panoid=XzSEQL57lR6Zs-1PneSlCw&cb_client=search.gws-prod.gps&yaw=285.33093&pitch=0&thumbfov=100&w=260&h=175", "https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111"), TuplesKt.to("https://www.google.com/maps/dir//Spas+Recycling+Pvt+Ltd,+Unit+No.7,Hema+Industrial+Estate,Sarvodaya+Nagar,,+MHB+Colony+Rd,+Meghwadi,+Indira+Nagar,+Jogeshwari+East,+Mumbai,+Maharashtra+400060/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b7d791f6d6d5:0x3bbed3a9524c3625?sa=X&ved=1t:57443&ictx=111", "https://www.google.com/maps/dir//BEST+RECYCLING+COMPANY+IN+MUMBAI,E-WASTE+SCRAP+BUYERS+IN+MUMBAI,COMPUTER+SCRAP+BUYERS+MUMBAI,E-WASTE+RECYCLING+COMPANY+MUMBAI,+A-103+SHIV+SHAKTI,CHS+1ST+FLOOR,ANDHERI+RTO,+Anna+Nagar,+Andheri+West,+Mumbai,+Maharashtra+400053/data=!4m6!4m5!1m1!4e2!1m2!1m1!1s0x3be7b765e3e43df3:0x5e91db5562b8ecce?sa=X&ved=1t:57443&ictx=111"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipONbUso0kSxswE65xYj20Bhui-ggLzp4OpsOOGI=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChJyZWN5Y2xlIHNob3AgZGVsaGlIgoD3rNSVgIAIWiQQABABGAAYAiIScmVjeWNsZSBzaG9wIGRlbGhpKgYIAxAAEAGSARh3YXN0ZV9tYW5hZ2VtZW50X3NlcnZpY2WqAWEKCC9tLzA5ZjA3CggvbS8wajZ2NxABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbUhW4QG11ojOA6sGQBSq1g4LZysAS3j7iwK-YMhYQAiIScmVjeWNsZSBzaG9wIGRlbGhp&phdesc=g4gzbWvGJJo&vet=12ahUKEwi3pvfd6buFAxUr2jgGHWWmABEQ8UF6BAgFEFg..i&lei=1LYYZrfsBau04-EP5cyCiAE&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KZV6en7IBA05MVcmL2_aqVMX&daddr=Block+RZ,+169,+Raghu+Nagar,+Dabri,+New+Delhi,+Delhi+110045"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipPPpms0TufS-tcQPVt6hvSqB032hQlwJuTl-lIH=s1360-w1360-h1020", "https://www.google.com/maps/dir//D442,+07,+Ramphal+Chowk+Rd,+near+k+c+restaurant,+Sector+6+Dwarka,+New+Delhi,+Delhi+110075/@28.5891566,76.9893745,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x390d1bc35c97894f:0x1bc960dd431bcf84!2m2!1d77.0717759!2d28.5891815?entry=ttu"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020", "https://lh3.googleusercontent.com/p/AF1QipMT0nNEhxy_2wgLkPqRiXhvnoENZ-M-5NGYpHnp=s1360-w1360-h1020"), TuplesKt.to("https://lh5.googleusercontent.com/p/AF1QipPhceWJwntLSIXZOr-IyYM8dUqi2xLxiyQBgyx3=w260-h175-n-k-no", "https://www.google.com/maps/dir//No+30,+2nd+Floor,+Mylai+Ranganathan+St,+T.+Nagar,+Chennai,+Tamil+Nadu+600017/@13.0370887,80.1555524,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x3a526704ba076e31:0xb20e3901f20a6d66!2m2!1d80.2379543!2d13.0371017?entry=ttu"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipMnxFNmprt6v1rq8rBm6TpxHGyRbvx63uFuXRoQ=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097"), TuplesKt.to("https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3AgY2hlbm5haUi3yKXqzriAgAhaLBAAEAEYABgBGAIiFHJlY3ljbGUgc2hvcCBjaGVubmFpKgQIFBABKgQIAxAAkgESc2NyYXBfbWV0YWxfZGVhbGVyqgFPEAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsOiK0N1WGnaTafkJGqxpuCCr09IOdp2S2ZtnwyGBACIhRyZWN5Y2xlIHNob3AgY2hlbm5haQ&phdesc=mPqSuG78CJY&vet=12ahUKEwj_uP3o6ruFAxV8T2wGHfzWATYQ8UF6BAgFEFg..i&lei=97cYZr_hKvyeseMP_K2HsAM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KT9yxyQQXVI6Mb-1at6Xfb0B&daddr=No-9,+Omr+road,+Sri+Sowdeswari+Nagar,+Thoraipakkam,+Chennai,+Tamil+Nadu+600097", "https://www.google.com/maps/dir//5,+Triplicane+High+Rd,+Ellis+Puram,+Padupakkam,+Triplicane,+Chennai,+Tamil+Nadu+600005/@13.0665409,80.1911581,12z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x3a526774419c8057:0xd762ef341de0db72!2m2!1d80.27356!2d13.0665539?entry=ttu"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipPH1aEaDYzEjBlgF0qfcFy7K2SOHC5gS2yYp3E=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3Aga29sa2F0YUi13MGkobiAgAhaKhAAEAEYABgCIhRyZWN5Y2xlIHNob3Aga29sa2F0YSoECAMQACoECBQQAXoHS29sa2F0YZIBGHdhc3RlX21hbmFnZW1lbnRfc2VydmljZZoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOcGEwdFFTREJuUlJBQqoBYwoIL20vMGN2dzkKCC9tLzBqNnY3EAEqECIMcmVjeWNsZSBzaG9wKAAyHxABIhsN3hEw_azmNxvbLmfQvtJ67h5n8m3mZ4BhSnoyGBACIhRyZWN5Y2xlIHNob3Aga29sa2F0YQ&phdesc=BkkM8XdFmCI&vet=12ahUKEwiurYq367uFAxWtyTgGHdjUBjoQ8UF6BAgFEFg..i&lei=m7gYZu7zHa2T4-EP2Kmb0AM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Kf__6zUEdwI6MV2Ih1Az4NLO&daddr=9A,+Marquis+St,+Esplanade,+Park+Street+area,+Kolkata,+West+Bengal+700016"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipO-yHA-oWquOAdvmAlyIVaG30zOKHj1yGq0Xd5Y=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChRyZWN5Y2xlIHNob3Aga29sa2F0YUie4-b9jK-AgAhaKhAAEAEYARgCIhRyZWN5Y2xlIHNob3Aga29sa2F0YSoECAMQACoECBQQAZIBG3VzZWRfb2ZmaWNlX2Z1cm5pdHVyZV9zdG9yZaoBTxABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbDd4RMP2s5jcb2y5n0L7Seu4eZ_Jt5meAYUp6MhgQAiIUcmVjeWNsZSBzaG9wIGtvbGthdGE&phdesc=iXD-GdyDxqU&vet=12ahUKEwiurYq367uFAxWtyTgGHdjUBjoQ8UF6BAgFEFg..i&lei=m7gYZu7zHa2T4-EP2Kmb0AM&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Kf-abAPMdwI6MXRw6azcme0T&daddr=33H,+B,+1,+Mirza+Ghalib+St,+Kolkata,+West+Bengal+700016"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipOPryyhIr66FIcffj9TyMSHGK4nzKQiA5C3Rj4d=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChZyZWN5Y2xlIHNob3AgaHlkZXJhYmFkSNm055q6rYCACFogEAAQARgAGAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWSSAQ5zY3JhcF9tZXJjaGFudJoBJENoZERTVWhOTUc5blMwVkpRMEZuU1VOVGVUa3RlWEIzUlJBQqoBURABKhAiDHJlY3ljbGUgc2hvcCgAMh8QASIbUWpDus879qGL6i1tNmogqNxKNZN0U8CMccJMMhoQAiIWcmVjeWNsZSBzaG9wIGh5ZGVyYWJhZA&vet=12ahUKEwjdjoyT7LuFAxWy3TgGHSDKDHUQ8UF6BAgZEFg..i&lei=XLkYZt3wG7K74-EPoJSzqAc&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=Ke0j5RhImcs7MfwGPb-hnZvi&daddr=16-9-749/95,+Race+Course+Rd,+Papaiah+Basthi,+Old+Malakpet,+Hyderabad,+Telangana+500036"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipM6Cf9xLPRmGWz8MuA_vnppz2C_9zQDSW9NzCUD=s1360-w1360-h1020", "https://www.google.com/maps?sca_esv=8e47e8b36fbcdee8&lqi=ChZyZWN5Y2xlIHNob3AgaHlkZXJhYmFkSPT01e6muYCACFogEAAQARgAGAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWR6CUh5ZGVyYWJhZJIBGHdhc3RlX21hbmFnZW1lbnRfc2VydmljZZoBI0NoWkRTVWhOTUc5blMwVkpRMEZuU1VOV2FFazJVVkZCRUFFqgFlCggvbS8wOWM2dwoIL20vMGo2djcQASoQIgxyZWN5Y2xlIHNob3AoADIfEAEiG1FqQ7rPO_ahi-otbTZqIKjcSjWTdFPAjHHCTDIaEAIiFnJlY3ljbGUgc2hvcCBoeWRlcmFiYWQ&vet=12ahUKEwjdjoyT7LuFAxWy3TgGHSDKDHUQ8UF6BAgZEFg..i&lei=XLkYZt3wG7K74-EPoJSzqAc&cs=1&um=1&ie=UTF-8&fb=1&gl=in&sa=X&geocode=KefwqsR1l8s7Ma6FJDEIj_nS&daddr=8-2-601/G/292,+Gouri+Shankar+Nagar+Colony,+Banjara+Hills,+Hyderabad,+Telangana+500034"), TuplesKt.to("https://lh3.googleusercontent.com/p/AF1QipP_OHP8Rs8p_4dXn_jC3E9vomlWokKhfypMXKLu=s1360-w1360-h1020", "https://www.google.com/maps/dir/12.9634,77.5855/RECYCLE+ONE,+16-9-406%2FA%2F54%2F1,+old+malakpet,+near+zaqriya+mosque,+Wahed+Nagar,+Amberpet,+Hyderabad,+Telangana+500036/@15.1615434,75.3605467,7z/data=!3m1!4b1!4m9!4m8!1m1!4e1!1m5!1m1!1s0x3bcb9941566b1a07:0xaca59e61776fadad!2m2!1d78.5036849!2d17.3849115?entry=ttu")};
        this.customLinksMap = MapsKt.mapOf(var1);
    }

    @SuppressLint("ResourceType")
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View view = inflater.inflate(layout.fragment_exchange_point, container, false);
        View var5 = view.findViewById(id.spinner);
        Intrinsics.checkNotNullExpressionValue(var5, "findViewById(...)");
        this.spinner = (Spinner)var5;
        var5 = view.findViewById(id.imageContainer);
        Intrinsics.checkNotNullExpressionValue(var5, "findViewById(...)");
        this.imageContainer = (LinearLayout)var5;
        ArrayAdapter var7 = ArrayAdapter.createFromResource(this.requireContext(), array.metropolitan_cities, 17367048);
        ArrayAdapter $this$onCreateView_u24lambda_u240 = var7;
        boolean var9 = false;
        $this$onCreateView_u24lambda_u240.setDropDownViewResource(17367049);
        Intrinsics.checkNotNullExpressionValue(var7, "apply(...)");
        ArrayAdapter citiesAdapter = var7;
        Spinner var6 = this.spinner;
        Spinner var10000;
        if (var6 != null) {
            var10000 = var6;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            var10000 = null;
        }

        var10000.setAdapter((SpinnerAdapter)citiesAdapter);
        var6 = this.spinner;
        if (var6 != null) {
            var10000 = var6;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("spinner");
            var10000 = null;
        }

        var10000.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                String selectedCity = String.valueOf(parent != null ? parent.getItemAtPosition(position) : null);
                ExchangePointFragment.this.displayImages(selectedCity);
            }

            public void onNothingSelected(AdapterView parent) {
            }
        }));
        return view;
    }

    private final void displayImages(String selectedCity) {
        List var3 = (List)this.cityImagesMap.get(selectedCity);
        List images = var3 != null ? var3 : CollectionsKt.emptyList();
        LinearLayout var12 = this.imageContainer;
        LinearLayout var10000;
        if (var12 != null) {
            var10000 = var12;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("imageContainer");
            var10000 = null;
        }

        var10000.removeAllViews();

        View spacer;
        for(Iterator var13 = images.iterator(); var13.hasNext(); var10000.addView(spacer)) {
            String imageUrl = (String)var13.next();
            ImageView image = new ImageView(this.requireContext());
            Glide.with((Fragment)this).load(imageUrl).into(image);
            image.setOnClickListener(this$0 -> displayImages$lambda$1(this$0));
            String var7 = (String)this.imageTextMap.get(imageUrl);
            String imageText = var7 != null ? var7 : "";
            TextView textView = new TextView(this.requireContext());
            textView.setText((CharSequence)imageText);
            LinearLayout container = new LinearLayout(this.requireContext());
            container.setOrientation(LinearLayout.VERTICAL);
            container.addView((View)image);
            container.addView((View)textView);
            LinearLayout var9 = this.imageContainer;
            if (var9 != null) {
                var10000 = var9;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("imageContainer");
                var10000 = null;
            }

            var10000.addView((View)container);
            spacer = new View(this.requireContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, 35);
            spacer.setLayoutParams((ViewGroup.LayoutParams)params);
            LinearLayout var11 = this.imageContainer;
            if (var11 != null) {
                var10000 = var11;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("imageContainer");
                var10000 = null;
            }
        }

    }

    private void displayImages$lambda$1(View this$0) {
    }

    private void openLink(String link) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(link));
        this.startActivity(intent);
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
    }

    private static final void displayImages$lambda$1(ExchangePointFragment this$0, String $imageUrl, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter($imageUrl, "$imageUrl");
        String var3 = (String)this$0.customLinksMap.get($imageUrl);
        this$0.openLink(var3 != null ? var3 : $imageUrl);
    }

    // $FF: synthetic method
    public void onDestroyView() {
        super.onDestroyView();

    }
}
