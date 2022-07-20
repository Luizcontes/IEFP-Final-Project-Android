package contes.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity
{
    DatabaseHelper myDB;
    EditText etNumero, etNome, etPhone, etIdade, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        Log.v("MyLOG", "InsertActivity onCreate");

        myDB = new DatabaseHelper(this);

        etNumero = findViewById(R.id.etNumero);
        etNome = findViewById(R.id.etNome);
        etPhone = findViewById(R.id.etPhone);
        etIdade = findViewById(R.id.etIdade);
        etEmail = findViewById(R.id.etEmail);
    }

    public void InsertContactButtonClicked(View view)
    {
        Log.v("MyLOG", "InsertContactButtonClicked");

        String Numero = etNumero.getText().toString();
        String Nome = etNome.getText().toString();
        String Phone = etPhone.getText().toString();
        String Idade = etIdade.getText().toString();
        String Email = etEmail.getText().toString();

        if(Numero.length() == 0)
        {
            Toast.makeText(this, "Tem que preencher o Numero!", Toast.LENGTH_LONG).show();
            return;
        }

        if(Nome.length() == 0)
        {
            Toast.makeText(this, "Tem que preencher o Nome!", Toast.LENGTH_LONG).show();
            return;
        }

        if(Phone.length() == 0)
        {
            Toast.makeText(this, "Tem que preencher o Número de Telefone!", Toast.LENGTH_LONG).show();
            return;
        }

        if(Idade.length() == 0)
        {
            Toast.makeText(this, "Tem que preencher a Idade!", Toast.LENGTH_LONG).show();
            return;
        }

        if(Email.length() == 0)
        {
            Toast.makeText(this, "Tem que preencher o E-mail!", Toast.LENGTH_LONG).show();
            return;
        }

        if (myDB.InserirFormando(Numero, Nome, Phone, Idade, Email) == true)
        {
            etNumero.setText("");
            etNome.setText("");
            etPhone.setText("");
            etIdade.setText("");
            etEmail.setText("");

            Toast.makeText(this, "Contacto armazenado com SUCESSO!", Toast.LENGTH_SHORT).show();

            finish();
        }
        else
        {
            Toast.makeText(this, "ERRO ao armazenar o Contacto", Toast.LENGTH_SHORT).show();
        }
    }

    public void VoltarButtonClicked(View view)
    {
        finish();
    }
}