package io.github.sumukhshiv.mdbsocials;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class UserArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

//        Button logoutButton = (Button) findViewById(R.id.buttonLogout);
//        Button buttonNewSocial = (Button) findViewById(R.id.buttonNewSocial);
//        Button buttonUpdateProfile = (Button) findViewById(R.id.buttonUpdateProfile);
//
//        logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent logoutIntent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(logoutIntent);
//            }
//        });
//
//        buttonNewSocial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent newSocialIntent = new Intent(getApplicationContext(), NewSocialActivity.class);
//                startActivity(newSocialIntent);
//            }
//        });
//
//        buttonUpdateProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent updateProfileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
//                startActivity(updateProfileIntent);
//            }
//        });

        final ListView mDrawerList;
        final ArrayAdapter<String> mAdapter;
        String[] optionsArray = {"Update Profile", "Create New Social", "Logout"};
        final Intent grabIntent = getIntent();

        mDrawerList = (ListView) findViewById(R.id.navList);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionsArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray clickedItemPositions = mDrawerList.getCheckedItemPositions();
                mAdapter.notifyDataSetChanged();

                for(int j = 0;j < clickedItemPositions.size(); j ++){
                    boolean checked = clickedItemPositions.valueAt(j);

                    if(checked){
                        int key = clickedItemPositions.keyAt(j);
                        String item = (String) mDrawerList.getItemAtPosition(key);

                        if(item.equalsIgnoreCase("Update Profile")){
                            Intent updateProfileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
                            updateProfileIntent.putExtra("email", grabIntent.getStringExtra("email"));
                            startActivity(updateProfileIntent);
                        }

                        if(item.equalsIgnoreCase("Create New Social")){
                            Intent newSocialIntent = new Intent(getApplicationContext(), NewSocialActivity.class);
                            newSocialIntent.putExtra("email", grabIntent.getStringExtra("email"));
                            startActivity(newSocialIntent);
                        }

                        if(item.equalsIgnoreCase("Logout")){
                            FirebaseAuth.getInstance().signOut();
                            Intent logoutIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(logoutIntent);
                        }
                    }
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.android_action_bar_spinner_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.spinner);
//        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
//
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(adapter);
//
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                String selectedItem = parent.getItemAtPosition(position).toString();
//                if(selectedItem.equals("Update Profile"))
//                {
//                    Intent updateProfileIntent = new Intent(getApplicationContext(), ProfileActivity.class);
//                    startActivity(updateProfileIntent);
//                }
//            } // to close the onItemSelected
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//
//            }
//        });
//
//        return true;
//    }
}
