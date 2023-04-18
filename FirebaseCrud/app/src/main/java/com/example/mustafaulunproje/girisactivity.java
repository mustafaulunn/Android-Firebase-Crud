package com.example.mustafaulunproje;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class girisactivity extends AppCompatActivity {
    EditText editTextemail;
    EditText editTextpassword;
    Button log_button;
    FirebaseAuth mAuth;
    Button sifreunuttum;
    Button yenikullanici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girisactivity);
        mAuth = FirebaseAuth.getInstance();
        editTextemail = findViewById(R.id.edemail);
        editTextpassword = findViewById(R.id.edpassword);
        log_button = findViewById(R.id.log_button);
        sifreunuttum=findViewById(R.id.sifreunuttum);
        yenikullanici=findViewById(R.id.yenikullanici);

        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(girisactivity.this, "Lütfen e-posta adresinizi girin.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(girisactivity.this, "Lütfen şifrenizi girin.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(girisactivity.this, "Başarıyla giriş yapıldı.", Toast.LENGTH_SHORT).show();

                                    String kullanici = editTextemail.getText().toString();
                                    Intent profil = new Intent(girisactivity.this,profile.class);
                                    profil.putExtra("name",kullanici);
                                    startActivity(profil);
                                } else {
                                    String errorText = "Giriş yapılamadı: " + task.getException().getMessage();
                                    Toast.makeText(girisactivity.this, errorText, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        sifreunuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(girisactivity.this, sifreunuttum.class);
                startActivity(intent);
                finish();
            }
        });
        yenikullanici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(girisactivity.this, yenikullanici.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
