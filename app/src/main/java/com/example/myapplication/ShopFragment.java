package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import java.util.List;
import java.util.ArrayList;
import kotlin.Triple;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ShopFragment extends Fragment {
    private double totalPrice;
    private LinearLayout productContainer;
    private Button checkoutButton;
    private FirebaseDatabase database;
    private DatabaseReference userPointsRef;
    private List<Triple<String, Double, Integer>> selectedProducts = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        productContainer = view.findViewById(R.id.productContainer);
        checkoutButton = view.findViewById(R.id.checkoutButton);

        database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            userPointsRef = database.getReference("Users").child(userId).child("points");

            displayProducts();
            checkoutButton.setOnClickListener(v -> showCheckoutDialog());

            return view;
        } else {
            throw new IllegalStateException("No authenticated user found");
        }
    }

    private void showCheckoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Checkout");
        builder.setMessage("Total Price: Rs" + totalPrice);

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_checkout, null);
        builder.setView(dialogView);

        builder.setPositiveButton("Confirm", (dialog, which) -> {
            EditText addressInput = dialogView.findViewById(R.id.addressEditText);
            String address = addressInput.getText().toString();
            deductTotalAmountFromBalance();
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void displayProducts() {
        List<Triple<String, Double, Integer>> products = new ArrayList<>();
        products.add(new Triple<>("Doms Single Line Note Book", 10.0, R.drawable.product1));
        products.add(new Triple<>("Apsara Scholars School Kit", 15.0, R.drawable.product2));
        products.add(new Triple<>("Jumbo Wax Crayons 12 Shades", 20.0, R.drawable.product3));
        products.add(new Triple<>("Classmate Octane Ball Blister Blue", 20.0, R.drawable.product4));
        products.add(new Triple<>("Badminton Racket", 20.0, R.drawable.product5));
        products.add(new Triple<>("Universal Mobile Phone Holder", 20.0, R.drawable.product6));
        products.add(new Triple<>("Dearjoy Giraffe Soft Toy", 20.0, R.drawable.product7));
        products.add(new Triple<>("Uno Playing Card Game", 20.0, R.drawable.product8));
        products.add(new Triple<>("Kwality Wall's Magnum Truffle Combo", 20.0, R.drawable.product9));

        selectedProducts.addAll(products);

        for (Triple<String, Double, Integer> product : products) {
            View productView = createProductView(product);
            productContainer.addView(productView);
        }
    }

    private View createProductView(Triple<String, Double, Integer> product) {
        View view = getLayoutInflater().inflate(R.layout.product_item, null);

        TextView productName = view.findViewById(R.id.productNameTextView);
        TextView productPrice = view.findViewById(R.id.productPriceTextView);
        ImageView productImage = view.findViewById(R.id.productImageView);
        CheckBox productCheckbox = view.findViewById(R.id.productCheckbox);

        productName.setText(product.getFirst());
        productPrice.setText("Rs " + product.getSecond());
        productImage.setImageResource(product.getThird());

        productCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> totalPrice = calculateTotalPrice());

        return view;
    }

    private double calculateTotalPrice() {
        double total = 0.0;

        for (Triple<String, Double, Integer> product : selectedProducts) {
            View productView = productContainer.getChildAt(selectedProducts.indexOf(product));
            CheckBox productCheckbox = productView.findViewById(R.id.productCheckbox);

            if (productCheckbox.isChecked()) {
                total += product.getSecond();
            }
        }

        return total;
    }

    private void deductTotalAmountFromBalance() {
        if (userPointsRef != null) {
            userPointsRef.runTransaction(new Transaction.Handler() {
                @NotNull
                @Override
                public Transaction.Result doTransaction(@NotNull MutableData currentData) {
                    Integer currentPoints = currentData.getValue(Integer.class);
                    if (currentPoints == null) {
                        currentPoints = 0;
                    }

                    int updatedPoints = currentPoints - (int) totalPrice;
                    if (updatedPoints < 0) {
                        return Transaction.abort();
                    } else {
                        currentData.setValue(updatedPoints);
                        return Transaction.success(currentData);
                    }
                }

                @Override
                public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
                    if (committed) {
                        showToast("Points deducted successfully.");
                    } else {
                        showToast("No sufficient points.");
                    }
                }
            });
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
