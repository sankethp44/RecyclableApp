package com.example.myapplication;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMyProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

public class MyProfileFragment extends Fragment {

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private FragmentMyProfileBinding binding;
    private Dialog dialog;
    private final int pickImageRequest = 1;
    private Uri selectedImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyProfileBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Initialize views and Firebase components
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Load user data
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            loadProfilePicture(uid);

            databaseReference.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String email = snapshot.child("email").getValue(String.class);
                        String city = snapshot.child("city").getValue(String.class);

                        binding.etName.setText(name);
                        binding.etEmailAccount.setText(email);
                        binding.etCity.setText(city);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle error
                    Toast.makeText(requireContext(), "Failed to load profile data", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Handle edit icon click
        binding.editIcon.setOnClickListener(v -> enableEditText());

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Firebase components
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        // Handle upload picture click
        binding.uploadPic.setOnClickListener(v -> openGallery());

        // Handle save button click
        binding.saveBtn.setOnClickListener(v -> {
            FirebaseUser currentUser = auth.getCurrentUser();
            if (currentUser != null) {
                showProgressBar();
                String uid = currentUser.getUid();
                String name = binding.etName.getText().toString();
                String email = binding.etEmailAccount.getText().toString();
                String city = binding.etCity.getText().toString();

                User user = new User(name, email, city);

                databaseReference.child(uid).setValue(user)
                        .addOnCompleteListener(task -> {
                            hideProgressBar();
                            if (task.isSuccessful()) {
                                Toast.makeText(requireContext(), "Profile successfully updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(requireContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(e -> {
                            hideProgressBar();
                            Toast.makeText(requireContext(), "Failed to update profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            } else {
                Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, pickImageRequest);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pickImageRequest && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            binding.profileImage.setImageURI(selectedImageUri);
            uploadProfilePic(selectedImageUri);
        }
    }

    private void uploadProfilePic(Uri imageUri) {
        showProgressBar();

        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference().child("Users/" + uid);

            UploadTask uploadTask = storageRef.putFile(imageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {
                hideProgressBar();
                Toast.makeText(requireContext(), "Profile picture uploaded successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(e -> {
                hideProgressBar();
                Toast.makeText(requireContext(), "Failed to upload profile picture: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        } else {
            hideProgressBar();
            Toast.makeText(requireContext(), "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }


    private void loadProfilePicture(String uid) {
        if (uid != null) {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference("Users/" + uid);

            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                Glide.with(requireContext())
                        .load(uri)
                        .placeholder(R.drawable.profilepic)
                        .error(R.drawable.profilepic)
                        .into(binding.profileImage);
            }).addOnFailureListener(e -> {
                // Handle failure
                Toast.makeText(requireContext(), "Failed to load profile picture: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void showProgressBar() {
        dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_wait);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void hideProgressBar() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void hideFab() {
        View extendedFab = requireActivity().findViewById(R.id.extendedFab);
        extendedFab.setVisibility(View.GONE);

        View regularFab = requireActivity().findViewById(R.id.fab);
        regularFab.setVisibility(View.GONE);

        View bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        bottomNav.setVisibility(View.GONE);

        View bottomAppBar = requireActivity().findViewById(R.id.bottomAppBar);
        bottomAppBar.setVisibility(View.GONE);
    }

    private void enableEditText() {
        binding.etName.setEnabled(true);
        binding.etName.requestFocus();

        binding.etEmailAccount.setEnabled(true);
        binding.etEmailAccount.requestFocus();

        binding.etCity.setEnabled(true);
        binding.etCity.requestFocus();
    }
}
