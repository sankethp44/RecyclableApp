package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FeedbackFragment extends Fragment {

    private EditText editTextName;
    private EditText editTextFeedback;
    private Button buttonSubmit;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Feedback");

        editTextName = view.findViewById(R.id.editTextName);
        editTextFeedback = view.findViewById(R.id.editTextFeedback);
        buttonSubmit = view.findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(v -> submitFeedback());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Implement hiding logic for UI elements like FABs, AppBars, etc. if needed
    }

    private void sendEmail(String name, String feedback, String userEmail) {
        // Note: Replace sensitive information with secure handling (e.g., using environment variables)
        new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("spalaksha@gmail.com", "rwqabsibzsoagpom");
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("spalaksha@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sankethp673@gmail.com"));
                message.setSubject("Thank you for your feedback " + name);
                message.setText("We value your feedback, and resolve any issue if mentioned in your feedback asap.\n\nContact us for further assistance.\n\nThank you!");

                Transport.send(message);

                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(), "Feedback submitted successfully", Toast.LENGTH_SHORT).show());
            } catch (MessagingException e) {
                e.printStackTrace();
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(requireContext(), "Failed to send feedback email", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }

    private void submitFeedback() {
        String name = editTextName.getText().toString().trim();
        String feedback = editTextFeedback.getText().toString().trim();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (!name.isEmpty() && !feedback.isEmpty()) {
            String userEmail = (currentUser != null) ? currentUser.getEmail() : "";

            String feedbackId = databaseReference.push().getKey();
            Feedback feedbackData = new Feedback(name, feedback, userEmail);

            databaseReference.child(feedbackId).setValue(feedbackData)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            sendEmail(name, feedback, userEmail);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(requireContext(), "Failed to submit feedback: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(requireContext(), "Name and Feedback cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    public static class Feedback {
        private String name;
        private String feedback;
        private String email;

        public Feedback(String name, String feedback, String email) {
            this.name = name;
            this.feedback = feedback;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getFeedback() {
            return feedback;
        }

        public String getEmail() {
            return email;
        }
    }
}
