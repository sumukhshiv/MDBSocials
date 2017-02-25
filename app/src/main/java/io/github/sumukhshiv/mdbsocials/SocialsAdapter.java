package io.github.sumukhshiv.mdbsocials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sumukhshivakumar on 2/24/17.
 */

public class SocialsAdapter extends RecyclerView.Adapter<SocialsAdapter.CustomViewHolder> {

    Context context;
    ArrayList<Social> socials;

    public SocialsAdapter(Context context, ArrayList<Social> socials) {
        this.context = context;
        this.socials = socials;
    }

    @Override
    public SocialsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SocialsAdapter.CustomViewHolder holder, int position) {
        final Social social = socials.get(position);
        //In the onBindViewHolder, you want to set each of the parameters of ComputerCompanies very similiar
        //to what you did to the layout manager.

        //holder.imageViewEventIamge.setImageResource(social.drawable);
        holder.textViewEventName.setText(social.nameOfEvent);
        holder.textViewHostEmail.setText(social.emailOfHost);
        holder.textViewNumberInterested.setText(Integer.toString(social.numberIntersted));


        holder.cardViewFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailScreenActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SOCIAL", social);
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return socials.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        //Set up the variables of Computer Companies here
        ImageView imageViewEventIamge;
        TextView textViewEventName;
        TextView textViewHostEmail;
        TextView textViewNumberInterested;
        CardView cardViewFeed;

        public CustomViewHolder(View view) {
            super(view);

            imageViewEventIamge = (ImageView) view.findViewById(R.id.imageViewSocialImage);
            textViewEventName = (TextView) view.findViewById(R.id.textViewEventName);
            textViewHostEmail = (TextView) view.findViewById(R.id.textViewHostEmail);
            textViewNumberInterested = (TextView) view.findViewById(R.id.textViewNumberInterested);
            cardViewFeed = (CardView) view.findViewById(R.id.cardViewFeed);

        }
    }




}
