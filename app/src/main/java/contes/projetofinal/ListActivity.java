package contes.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity
{
    DatabaseHelper myDB;

    ListView listViewContacts;

    MyCustomAdapter myAdapter;
    ArrayList<Contact> contactArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myDB = new DatabaseHelper(this);

        listViewContacts = findViewById(R.id.listViewContacts);

        listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(ListActivity.this, EditActivity.class);

                Contact contact = (Contact) listViewContacts.getItemAtPosition(position);

                intent.putExtra("ContactsSelectedId", String.valueOf(contact.getId()));

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        LoadListView();

        Log.v("MyLOG", "MainActivity onResume");
    }

    public void LoadListView()
    {
        contactArrayList = myDB.GetAllContacts();

        myAdapter = new MyCustomAdapter(this, contactArrayList);
        listViewContacts.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();
    }

    public void InsertContactButtonClicked(View view)
    {
        Intent intent = new Intent(this, InsertActivity.class);

        Log.v("MyLOG", "MainActivity InsertContactButtonClicked startActivity");

        startActivity(intent);
    }

    public void Logout(View view) {
        finish();
    }
}