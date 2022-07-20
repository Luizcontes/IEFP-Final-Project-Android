package contes.projetofinal;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity
{
    private EditText editTextUsername, editTextPassword, editTextEmail;
    private DatabaseHelper appDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.username);
        editTextPassword = findViewById(R.id.password);
        editTextEmail = findViewById(R.id.email);

        appDB = new DatabaseHelper(this);
    }

    public void ButtonPressed(View view)
    {
        if (view.getId() == R.id.do_register)
        {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            String email = editTextEmail.getText().toString();

            if (appDB.InserirUser(username, password, email) == true)
            {
                Toast.makeText(this, R.string.UserInserted, Toast.LENGTH_SHORT).show();
                finish();
            }
            else
            {
                Toast.makeText(this, R.string.UserInsertedError, Toast.LENGTH_SHORT).show();
            }
        }
        if (view.getId() == R.id.do_cancel)
        {
            finish();
        }
    }
}