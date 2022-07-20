package contes.projetofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "ufcd5423.db";
    public static final int    DATABASE_VERSION = 1;

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "Id";
    public static final String USERS_COLUMN_USERNAME = "Username";
    public static final String USERS_COLUMN_PASSWORD = "Password";
    public static final String USERS_COLUMN_EMAIL = "Email";

    public static final String STUDENTS_TABLE_NAME = "Contacts";
    public static final String STUDENTS_COLUMN_ID = "Id";
    public static final String STUDENTS_COLUMN_NUMBER = "Numero";
    public static final String STUDENTS_COLUMN_NAME = "Nome";
    public static final String STUDENTS_COLUMN_TELEFONE = "Telefone";
    public static final String STUDENTS_COLUMN_IDADE = "Idade";
    public static final String STUDENTS_COLUMN_EMAIL = "Email";

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String queryCreateUsers = "create table if not exists " + USERS_TABLE_NAME + " (" +
                USERS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERS_COLUMN_USERNAME + " TEXT, " +
                USERS_COLUMN_PASSWORD + " TEXT, " +
                USERS_COLUMN_EMAIL + " TEXT)";
        sqLiteDatabase.execSQL(queryCreateUsers);

        String queryCreateStudents = "create table if not exists " + STUDENTS_TABLE_NAME + " (" +
                STUDENTS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENTS_COLUMN_NUMBER + " INTEGER, " +
                STUDENTS_COLUMN_NAME + " TEXT, " +
                STUDENTS_COLUMN_TELEFONE + " TEXT, " +
                STUDENTS_COLUMN_IDADE + " TEXT, " +
                STUDENTS_COLUMN_EMAIL + " TEXT)";
        sqLiteDatabase.execSQL(queryCreateStudents);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int _old, int _new)
    {
        onCreate(sqLiteDatabase);
    }

    public boolean InserirUser (String Username, String Password, String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(USERS_COLUMN_USERNAME, Username);
        contentValues.put(USERS_COLUMN_PASSWORD, Password);
        contentValues.put(USERS_COLUMN_EMAIL, Email);

        long result = db.insert(USERS_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean CheckLogin (String _Username, String _Password)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        long NumEntries = DatabaseUtils.queryNumEntries(db, USERS_TABLE_NAME,
                USERS_COLUMN_USERNAME + " = ? AND " +
                        USERS_COLUMN_PASSWORD + " = ?",
                new String[] {_Username, _Password});

        if (NumEntries == 0)
            return false;
        else
            return true;
    }

    public boolean InserirFormando (String Numero, String Nome, String Telefone, String Idade, String Email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENTS_COLUMN_NUMBER, Numero);
        contentValues.put(STUDENTS_COLUMN_NAME, Nome);
        contentValues.put(STUDENTS_COLUMN_TELEFONE, Telefone);
        contentValues.put(STUDENTS_COLUMN_IDADE, Idade);
        contentValues.put(STUDENTS_COLUMN_EMAIL, Email);

        long result = db.insert(STUDENTS_TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor LerFormandos ()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "select * from " + STUDENTS_TABLE_NAME;

        Cursor result = db.rawQuery(query, null);

        return result;
    }

    public Integer DeleteFormando (int Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(STUDENTS_TABLE_NAME, STUDENTS_COLUMN_ID + " = ?", new String[] {Integer.toString(Id)});
    }

    public Contact GetContactById(int _Id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Contact contact = null;

        String query = "select * from Contacts where Id = " + _Id;

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() == 1)
        {
            cursor.moveToFirst();

            String Numero = cursor.getString(1);
            String Nome = cursor.getString(2);
            String Phone = cursor.getString(3);
            String Idade = cursor.getString(4);
            String Email = cursor.getString(5);

            contact = new Contact(_Id, Numero, Nome, Phone, Idade, Email);
        }

        return contact;
    }

    public boolean UpdateContact (int id, String numero, String nome, String phone, String idade, String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(STUDENTS_COLUMN_NUMBER, numero);
        contentValues.put(STUDENTS_COLUMN_NAME, nome);
        contentValues.put(STUDENTS_COLUMN_TELEFONE, phone);
        contentValues.put(STUDENTS_COLUMN_IDADE, idade);
        contentValues.put(STUDENTS_COLUMN_EMAIL, email);

        long result = db.update("Contacts", contentValues, "Id = ?", new String[] {Integer.toString(id)});

        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public Integer DeleteContact (int Id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete("Contacts", "Id = ?", new String[] {Integer.toString(Id)});
    }

    public ArrayList<Contact> GetAllContacts()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Contact> contactArrayList = new ArrayList<>();

        String query = "select * from Contacts";
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            int Id = cursor.getInt(0);
            String Numero = cursor.getString(1);
            String Nome = cursor.getString(2);
            String Phone = cursor.getString(3);
            String Idade = cursor.getString(4);
            String Email = cursor.getString(5);

            Contact contact = new Contact(Id, Numero, Nome, Phone, Idade, Email);

            contactArrayList.add(contact);
        }

        return contactArrayList;
    }
}
