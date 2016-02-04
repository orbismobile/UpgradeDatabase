package pe.elcomercio.upgradedb.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import pe.elcomercio.upgradedb.R;
import pe.elcomercio.upgradedb.database.DatabaseHandler;
import pe.elcomercio.upgradedb.model.PersonEntity;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private EditText txtName, txtLastname, txtEmail;
    private Button btn;
    private LinearLayout linGeneral;
    private int VALUE = 1;
    private DatabaseHandler database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(VALUE);
                finish();
            }
        });

        database = DatabaseHandler.getInstance(this);
        txtName = (EditText) findViewById(R.id.txtName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        linGeneral = (LinearLayout) findViewById(R.id.linGeneral);
        txtLastname = (EditText) findViewById(R.id.txtLastname);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        //for email
        /*
        txtEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_GO) {
                    btn.performClick();
                }
                return false;
            }

        });*/

        txtLastname.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_GO) {
                    btn.performClick();
                }
                return false;
            }

        });



    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn:
                String name = txtName.getText().toString();
                String lastname = txtLastname.getText().toString();
                String email = "";
                //for email
                //email = txtEmail.getText().toString();

                if(name.length()>0){
                    if(lastname.length()>0){
                        //for register email
                        /*
                        if(email.length()>0){
                            txtName.setText("");
                            txtName.requestFocus();
                            txtEmail.setText("");
                            database.addPerson(new PersonEntity(name, lastname, email));
                            Snackbar.make(linGeneral, getString(R.string.msg_register), Snackbar.LENGTH_SHORT).show();
                        }else{
                            txtEmail.requestFocus();
                            Snackbar.make(linGeneral, getString(R.string.msg_enter_email), Snackbar.LENGTH_SHORT).show();
                        }
                        */

                        txtName.setText("");
                        txtName.requestFocus();
                        txtLastname.setText("");
                        database.addPerson(new PersonEntity(name, lastname, email));
                        Snackbar.make(linGeneral, getString(R.string.msg_register), Snackbar.LENGTH_SHORT).show();
                    }else{
                        txtLastname.requestFocus();
                        Snackbar.make(linGeneral, getString(R.string.msg_enter_lastname), Snackbar.LENGTH_SHORT).show();
                    }
                }else{
                    txtName.requestFocus();
                    Snackbar.make(linGeneral, getString(R.string.msg_enter_name), Snackbar.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(VALUE);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
