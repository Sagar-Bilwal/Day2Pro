package com.example.sagar.day2project;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UploadedPdfs extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    RVAdapter PdfRecyclerAdapter;
    ArrayList<PdfObject> pdfObjects=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_pdfs);
        fetchUploadedPdfs();
    }

    private void fetchUploadedPdfs()
    {
        final ProgressDialog progressDialog = new ProgressDialog(UploadedPdfs.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Uploaded PDF");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                pdfObjects.clear();
                if (snapshot != null) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        PdfObject pdf = postSnapshot.getValue(PdfObject.class);
                        if (pdf != null)
                            pdfObjects.add(pdf);
                        else
                            return;
                    }
                    if (pdfObjects != null) {
                        PdfRecyclerAdapter = new RVAdapter(pdfObjects, UploadedPdfs.this);
                        recyclerView = findViewById(R.id.uploadedPdfs);
                        recyclerView.setAdapter(PdfRecyclerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(UploadedPdfs.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.addItemDecoration(new DividerItemDecoration(UploadedPdfs.this, DividerItemDecoration.HORIZONTAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
