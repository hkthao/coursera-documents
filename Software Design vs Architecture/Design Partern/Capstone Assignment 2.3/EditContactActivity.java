package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Editing a pre-existing contact consists of deleting the old contact and adding a new contact with the old
 * contact's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditContactActivity extends AppCompatActivity {

    private ContactList contact_list = new ContactList();
    private Contact contact;
    private EditText email;
    private EditText username;
    private Context context;

    private String username_str;
    private String email_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        context = getApplicationContext();

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        contact_list_controller.addObserver(this);
        contact_list_controller.loadContacts(context);

        on_create_update = true;

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
    }

    public boolean validateInput() {
        email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return false;
        }

        if (!email_str.contains("@")){
            email.setError("Must be an email address!");
            return false;
        }

        username_str = username.getText().toString();

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!contact_list_controller.isUsernameAvailable(username_str) && !(contact_controller.getUsername().equals(username_str))) {
            username.setError("Username already taken!");
            return false;
        }
        return true;
    }

    public void saveContact(View view) {

        if (!validateInput()) {
            return;
        }

        String id = contact_controller.getId(); // Reuse the contact id
        Contact updated_contact = new Contact(username_str, email_str, id);

        // Edit contact
        boolean success = contact_list_controller.editContact(contact, updated_contact, context);
        if (!success){
            return;
        }

        // End EditContactActivity
        finish();
    }

    public void deleteContact(View view) {

        // Delete contact
        DeleteContactCommand delete_contact_command = new DeleteContactCommand(contact_list, contact, context);
        delete_contact_command.execute();

        boolean success = delete_contact_command.isExecuted();
        if (!success){
            return;
        }

        // End EditContactActivity
        finish();
    }
}
