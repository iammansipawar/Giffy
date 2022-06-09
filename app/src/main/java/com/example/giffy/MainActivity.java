package com.example.giffy;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.giffy.adapters.CategoryListAdapter;
import com.example.giffy.model.CategoryModel;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CategoryListAdapter.CategoryListClickListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
        navigationView.setCheckedItem(R.id.nav_home);


        List<CategoryModel> categoryModelList =  getPtoductData();

        initRecyclerView(categoryModelList);


    }

    private void initRecyclerView(List<CategoryModel> categoryModelList ) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CategoryListAdapter adapter = new CategoryListAdapter(categoryModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<CategoryModel> getPtoductData() {
        InputStream is = getResources().openRawResource(R.raw.product);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        CategoryModel[] categoryModels =  gson.fromJson(jsonStr, CategoryModel[].class);
        List<CategoryModel> catList = Arrays.asList(categoryModels);

        return  catList;

    }

    @Override
    public void onItemClick(CategoryModel categoryModel) {
        Intent intent = new Intent(MainActivity.this, CategoryMenuActivity.class);
        intent.putExtra("CategoryModel", categoryModel);
        startActivity(intent);

    }

    public void myAccount(View view){
        startActivity(new Intent(MainActivity.this,Login_Form.class));
    }

    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.nav_share:
                Toast.makeText(this, "Share it", Toast.LENGTH_LONG).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}