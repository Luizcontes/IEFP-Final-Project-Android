package contes.projetofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity
{
    private EditText editTextUsername, editTextPassword;
    private DatabaseHelper appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);

        appDB = new DatabaseHelper(this);
    }

    public void ButtonPressed(View view)
    {
        if (view.getId() == R.id.do_login)
        {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();

            // check credentials here

            if (appDB.CheckLogin(username, password) == true)
            {
                Intent intent = new Intent(this, ListActivity.class);

                startActivity(intent);

                Toast.makeText(this, R.string.correct_credentials, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, R.string.wrong_credentials, Toast.LENGTH_SHORT).show();
            }
        }
        else if (view.getId() == R.id.goto_register)
        {
            Intent intent = new Intent(this, RegisterActivity.class);

            startActivity(intent);
        }
    }
}