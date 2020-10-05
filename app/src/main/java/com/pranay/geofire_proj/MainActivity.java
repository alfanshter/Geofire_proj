package com.pranay.geofire_proj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.Bundle;
import android.util.Log;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    DatabaseReference databaseReference,reference;
    RecyclerView recyclerView;

    Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.my_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference= FirebaseDatabase.getInstance().getReference().child(("my_keys"));

        GeoFire fire=new GeoFire(databaseReference);
//
//        fire.setLocation("one", new GeoLocation(20.974889, 77.767806), new GeoFire.CompletionListener() {
//            @Override
//            public void onComplete(String key, DatabaseError error) {
//
//            }
//        });


        fire.queryAtLocation(new GeoLocation(20.992097, 77.737740),5).addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Log.i("key",key);
                getitems(key);
            }

            @Override
            public void onKeyExited(String key)
            {
                for (int i=0;i<arrayList.size();i++)
                {
                    if (key.equals(arrayList.get(i).getId()))
                    {
                            arrayList.remove(i);
                            myadapter.notifyItemRemoved(i);
                    }
                }

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {

            }

            @Override
            public void onGeoQueryReady() {

            }

            @Override
            public void onGeoQueryError(DatabaseError error) {

            }
        });





    }



    ArrayList<Mylist> arrayList=new ArrayList<>();
    private void getitems(String key)
    {
        reference=FirebaseDatabase.getInstance().getReference().child("my_items").child(key);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                Mylist mylist=new Mylist();
                mylist.setId(snapshot.child("id").getValue().toString());
                mylist.setName(snapshot.child("name").getValue().toString());
                arrayList.add(mylist);
                myadapter=new Myadapter(arrayList,MainActivity.this);
                recyclerView.setAdapter(myadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
