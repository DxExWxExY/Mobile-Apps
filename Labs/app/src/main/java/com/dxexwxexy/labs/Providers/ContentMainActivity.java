package com.dxexwxexy.labs.Providers;

//import android.database.Cursor;
//import android.content.CursorLoader;
//import android.database.Cursor;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.LoaderManager;
//import android.support.v4.content.CursorLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.dxexwxexy.labs.R;

import java.util.ArrayList;

public class ContentMainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        getPermissionToReadUserContacts();
        Button find = findViewById(R.id.find_button);
        EditText name = findViewById(R.id.name_field);
        ListView listView = findViewById(R.id.contact_list);
        ArrayList<String> contacts = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);
        find.setOnClickListener(e -> {
            getSupportLoaderManager().initLoader(1, null,
                    new LoaderManager.LoaderCallbacks<Cursor>() {
                        @NonNull
                        @Override
                        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
                            Uri allContacts = Uri.parse("content://contacts/people");
                            return new CursorLoader(ContentMainActivity.this, allContacts, null, null, null,null);
                        }

                        @Override
                        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
                            if (data.moveToFirst()) {
                                while (data.moveToNext()) {
                                    System.out.println(data.toString());
//                                    if (!name.getText().toString().equals("") &&
//                                            data.toString().matches("\\S+" + name.getText() + "\\S+")) {
                                        contacts.add(data.toString());
//                                    }
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onLoaderReset(@NonNull Loader<Cursor> loader) {

                        }
                    });
        });
    }

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    // Called when the user is performing an action which requires the app to read the
    // user's contacts
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToReadUserContacts() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_CONTACTS)) {
                // Show our own UI to explain to the user why we need to read the contacts
                // before actually requesting the permission and showing the default UI
            }

            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        }
    }

    // Callback with the request from calling requestPermissions(...)
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        // Make sure it's our original READ_CONTACTS request
        if (requestCode == READ_CONTACTS_PERMISSIONS_REQUEST) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Read Contacts permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Read Contacts permission denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
