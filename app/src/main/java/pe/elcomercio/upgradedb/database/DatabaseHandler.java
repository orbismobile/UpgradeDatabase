package pe.elcomercio.upgradedb.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import pe.elcomercio.upgradedb.model.PersonEntity;


/**
 * Created by Ricardo Bravo on 4/02/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler mInstance = null;

    //upgrade to version 2
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "DEMODB";
    private static final String TABLE_PERSON = "PERSON";

    private static final String KEY_PERSONID = "ID";
    private static final String KEY_PERSONNAME = "NAME";
    private static final String KEY_PERSONLASTNAME = "LASTNAME";
    //new field
    //private static final String KEY_PERSONEMAIL = "EMAIL";

    private Context context;

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DatabaseHandler getInstance(Context ctx){
        if (mInstance == null) {
            mInstance = new DatabaseHandler(ctx.getApplicationContext());
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String CREATE_PERSON_TABLE= "CREATE TABLE " + TABLE_PERSON + "("
                + KEY_PERSONID +" INTEGER PRIMARY KEY, " + KEY_PERSONNAME + " TEXT , "
                + KEY_PERSONLASTNAME+ " TEXT)";

        db.execSQL(CREATE_PERSON_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        //for add new colum to table

        /*
        if (newVersion>oldVersion){
            String upgradeQuery = "ALTER TABLE "+TABLE_PERSON+" ADD COLUMN "+KEY_PERSONEMAIL+" TEXT DEFAULT ''";
            db.execSQL(upgradeQuery);
        }
        */

    }

    public void addPerson(PersonEntity person){

        String sql = "INSERT INTO "+ TABLE_PERSON +"("+KEY_PERSONNAME+", "+KEY_PERSONLASTNAME
                +") VALUES (?,?)";
                //+", "+KEY_PERSONEMAIL+") VALUES (?,?, ?)";

        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement(sql);
        db.beginTransaction();
        statement.clearBindings();
        statement.bindString(1, person.getName());
        statement.bindString(2, person.getLastname());
        //statement.bindString(3, person.getEmail());
        statement.execute();
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public List<PersonEntity> getPerson(){

        List<PersonEntity> personList = new ArrayList<>();

        String selectQuery = "SELECT "+KEY_PERSONID+", "+KEY_PERSONNAME+", "+KEY_PERSONLASTNAME
                //+", "+KEY_PERSONEMAIL+" FROM "+TABLE_PERSON;
                +" FROM "+TABLE_PERSON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        try{
            if (cursor.moveToFirst()) {
                do {
                    PersonEntity person = new PersonEntity();
                    person.setId(cursor.getString(0));
                    person.setName(cursor.getString(1));
                    person.setLastname(cursor.getString(2));
                    //person.setEmail(cursor.getString(3));
                    personList.add(person);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return personList;

    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

}
