package com.example.coordinatelayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by romain on 15/10/15.
 */
public class SnackbarActivity extends AppCompatActivity implements View.OnClickListener {

    private Snackbar mSnackbar;

    public static void start(Context context) {
        Intent intent = new Intent(context, SnackbarActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        View fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                showSnackbar(v, "Click on FAB");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSnackbar(View view, String message) {
        if (mSnackbar != null && mSnackbar.isShown()) {
            mSnackbar.dismiss();
            mSnackbar = null;
        } else {
            mSnackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            mSnackbar.show();
        }
    }
}
