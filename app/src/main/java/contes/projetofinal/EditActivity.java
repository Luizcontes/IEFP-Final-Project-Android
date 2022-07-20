package contes.projetofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity
{
    DatabaseHelper myDB;

    EditText etNumero, etNome, etPhone, etIdade, etEmail;
    int DatabaseTableItemId;

    int dbItemId;
    String numero;
    String nome;
    String phone;
    String idade;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNumero = findViewById(R.id.etNumero);
        etNome = findViewById(R.id.etNome);
        etPhone = findViewById(R.id.etPhone);
        etIdade = findViewById(R.id.etIdade);
        etEmail = findViewById(R.id.etEmail);

        myDB = new DatabaseHelper(this);

        Intent intent = getIntent();

        DatabaseTableItemId = Integer.parseInt(intent.getStringExtra("ContactsSelectedId"));
        this.dbItemId = DatabaseTableItemId;

        Contact contact = myDB.GetContactById(DatabaseTableItemId);

        etNumero.setText(contact.getNumero());
        this.numero = contact.getNumero();
        etNome.setText(contact.getNome());
        this.nome = contact.getNome();
        etPhone.setText(contact.getPhone());
        this.phone =  contact.getPhone();
        etIdade.setText(contact.getIdade());
        this.idade = contact.getIdade();
        etEmail.setText(contact.getEmail());
        this.email =  contact.getEmail();
    }

    public void BotaoPressionado(View view)
    {
        if (view.getId() == R.id.btUpdate)
        {
            String Numero = etNumero.getText().toString();
            String Nome = etNome.getText().toString();
            String Phone = etPhone.getText().toString();
            String Idade = etIdade.getText().toString();
            String Email = etEmail.getText().toString();

            boolean isUpdated = myDB.UpdateContact(DatabaseTableItemId, Numero, Nome, Phone, Idade, Email);

            if (isUpdated == true)
                Toast.makeText(this, "Formando Actualizado com SUCESSO", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "ERRO na Actualização do Formando", Toast.LENGTH_SHORT).show();

            finish();
        }
        else if (view.getId() == R.id.btDelete)
        {
            Integer DeletedRows = myDB.DeleteContact(DatabaseTableItemId);

            if (DeletedRows > 0)
                Toast.makeText(this, "Formando Eliminado com SUCESSO", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "ERRO na Eliminação do Formando", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (view.getId() == R.id.btVoltar)
        {
            finish();
        }

        else if (view.getId() == R.id.btVer)
        {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Id:      "   + this.dbItemId + "\n");
            buffer.append("Numero:  "   + this.numero + "\n");
            buffer.append("Nome:    "   + this.nome + "\n");
            buffer.append("Telefone: "  + this.phone + "\n");
            buffer.append("Idade: "     + this.idade + "\n");
            buffer.append("E-mail: "    + this.email + "\n\n");

            ShowAlertMessage("STATUS", buffer.toString());
        }
    }

    private void ShowAlertMessage(String Title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.setCancelable(true);
        builder.show();
    }
}