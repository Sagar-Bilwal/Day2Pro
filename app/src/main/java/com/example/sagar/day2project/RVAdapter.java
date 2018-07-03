package com.example.sagar.day2project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PdfViewHolder>
{
    private ArrayList<PdfObject> pdfObjects;
    private Context context;
    public RVAdapter(ArrayList<PdfObject> pdfObjects,Context context)
    {
        this.pdfObjects=pdfObjects;
        this.context=context;
    }
    @NonNull
    @Override
    public PdfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        View view=layoutInflater.inflate(R.layout.row_layout,parent,false);
        PdfViewHolder viewHolder=new PdfViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewHolder holder, int position)
    {
        PdfObject pdfObject =pdfObjects.get(position);
        holder.pdfName.setText(pdfObject.getPdfNam());
    }

    @Override
    public int getItemCount() {
        return pdfObjects.size();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder
    {
        View itemView;
        TextView pdfName;
        public PdfViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            pdfName=itemView.findViewById(R.id.pdfName);
        }
    }
}
