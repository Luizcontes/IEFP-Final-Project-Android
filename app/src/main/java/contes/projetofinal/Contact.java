package contes.projetofinal;

public class Contact
{
    private int Id;
    private String Numero;
    private String Nome;
    private String Phone;
    private String Idade;
    private String Email;

    public Contact()
    {
    }

    public Contact(int Id, String Numero, String Nome, String Phone, String Idade, String Email)
    {
        this.Id = Id;
        this.Numero = Numero;
        this.Nome = Nome;
        this.Phone = Phone;
        this.Idade = Idade;
        this.Email = Email;

    }

    public int getId()
    {
        return this.Id;
    }

    public String getNumero()
    {
        return this.Numero;
    }

    public String getNome()
    {
        return this.Nome;
    }

    public String getPhone()
    {
        return this.Phone;
    }

    public String getIdade()
    {
        return this.Idade;
    }

    public String getEmail()
    {
        return this.Email;
    }

    public void setId(int Id)
    {
        this.Id = Id;
    }

    public void setNumero(String Numero)
    {
        this.Numero = Numero;
    }

    public void setNome(String Nome)
    {
        this.Nome = Nome;
    }

    public void setPhone(String Phone)
    {
        this.Phone = Phone;
    }

    public void setIdade(String Idade)
    {
        this.Idade = Idade;
    }

    public void setEmail(String Email)
    {
        this.Email = Email;
    }
}