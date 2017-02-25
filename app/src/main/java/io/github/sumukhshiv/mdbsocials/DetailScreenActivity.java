package io.github.sumukhshiv.mdbsocials;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailScreenActivity extends AppCompatActivity {

    private Social social;
    TextView textViewEventNameDetail;
    ImageView imageViewDetail;
    TextView emailDetail;
    Button buttonInterestedDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        textViewEventNameDetail = (TextView) findViewById(R.id.textViewEventNameDetail);
        imageViewDetail = (ImageView) findViewById(R.id.imageViewDetail);
        emailDetail = (TextView) findViewById(R.id.textViewEmailDetail);
        buttonInterestedDetail = (Button) findViewById(R.id.buttonInterstedDetail);



    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        social = (Social) bundle.getSerializable("SOCIAL");
        textViewEventNameDetail.setText(social.nameOfEvent);
        emailDetail.setText(social.emailOfHost);
        buttonInterestedDetail.setText(Integer.toString(social.numberIntersted));
//        Glide.with(this)
//                .load("http://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pokemon.number + ".png")
//                .into(image);
    }

}
