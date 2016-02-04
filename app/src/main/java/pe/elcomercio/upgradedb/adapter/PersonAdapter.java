package pe.elcomercio.upgradedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pe.elcomercio.upgradedb.R;
import pe.elcomercio.upgradedb.model.PersonEntity;

/**
 * Created by Ricardo Bravo on 4/02/16.
 */

public class PersonAdapter extends BaseAdapter {

    private List<PersonEntity> personList ;
    private Context context ;

    public PersonAdapter (Context ctx, List<PersonEntity> list) {
        super();
        this.context = ctx ;
        this.personList = list ;
    }

    @Override
    public int getCount() {
        return this.personList.size();
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item, viewGroup, false);
        TextView txt = (TextView) v.findViewById(R.id.lbl);
        PersonEntity user = personList.get(i);
        String email = user.getEmail();
        if(user.getEmail()==null){
            email="";
        }
        txt.setText(user.getName() + " " + user.getLastname()+ " "+email);
        return v;
    }
}
