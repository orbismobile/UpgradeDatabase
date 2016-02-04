package pe.elcomercio.upgradedb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.upgradedb.R;
import pe.elcomercio.upgradedb.adapter.PersonAdapter;
import pe.elcomercio.upgradedb.database.DatabaseHandler;
import pe.elcomercio.upgradedb.model.PersonEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private PersonAdapter adapter;
    List<PersonEntity> personList ;
    private DatabaseHandler database;
    private ListView list;
    private int VALUE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        database = DatabaseHandler.getInstance(this);
        list = (ListView) findViewById(R.id.list);

        fab.setOnClickListener(this);

        loadData();

    }

    private void loadData(){
        personList = new ArrayList<>();
        List<PersonEntity> personEntity = database.getPerson();
        adapter = new PersonAdapter(this, personEntity);
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab:
                startActivityForResult(new Intent(MainActivity.this, Register.class), VALUE);
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == VALUE) {
            loadData();
        }
    }

}
