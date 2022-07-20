package contes.projetofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyCustomAdapter extends BaseAdapter
{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Contact> contactsArrayList;

    public MyCustomAdapter(Context context, ArrayList<Contact> contactsArrayList)
    {
        this.context = context;
        this.contactsArrayList = contactsArrayList;

        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return this.contactsArrayList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return contactsArrayList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        Contact contact = contactsArrayList.get(position);

        if (view == null)
        {
            view = this.layoutInflater.inflate(R.layout.contactslistview, viewGroup, false);
        }

        TextView textViewId = view.findViewById(R.id.textViewId);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewPhone = view.findViewById(R.id.textViewPhone);

        textViewId.setText(String.valueOf(contact.getId()));
        textViewName.setText(contact.getNome());
        textViewPhone.setText(contact.getPhone());

        return view;
    }
}
