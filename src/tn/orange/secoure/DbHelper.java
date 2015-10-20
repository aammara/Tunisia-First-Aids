package tn.orange.secoure;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DbHelper extends SQLiteOpenHelper {
final static String dbName="dsc";
final String numCOLUMN="number";
final String deptTable="numbers";
final String PHSTable="pers_health_status";
final String INFOTable="INFOS";

private SQLiteDatabase bdd = getReadableDatabase();
public DbHelper(Context context) {
super(context, dbName, null,1); 
}

@Override
public void onCreate(SQLiteDatabase db) 
{
// TODO Auto-generated method stub   	  
db.execSQL("CREATE TABLE IF NOT EXISTS "+deptTable+" (number TEXT PRIMARY KEY , name TEXT)");
db.execSQL("CREATE TABLE IF NOT EXISTS "+PHSTable+" (DBT TEXT,  ALRG TEXT)");
db.execSQL("CREATE TABLE IF NOT EXISTS "+INFOTable+" (label TEXT, value TEXT)");
db.execSQL("INSERT INTO "+PHSTable+" VALUES ('false','false')");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Nom','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('PrŽnom','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Date de naissance','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Sex','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Adresse','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Pays','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Group sanguin','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('Donneur d''organe','');");
db.execSQL("INSERT INTO "+INFOTable+" VALUES ('NumŽro de sŽcuritŽ social','');");
}
 
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
{
// TODO Auto-generated method stub   	  
db.execSQL("DROP TABLE IF EXISTS "+deptTable);
db.execSQL("DROP TABLE IF EXISTS "+PHSTable);
db.execSQL("DROP TABLE IF EXISTS "+INFOTable);
onCreate(db);
}
    
public void insertNUM(String num, String name)
{
ContentValues numToAdd=new ContentValues();
numToAdd.put("number", num);
numToAdd.put("name", name);
bdd.insert(deptTable, null, numToAdd);
}

boolean exist (String num)
{
	Cursor cur=bdd.rawQuery("SELECT * FROM numbers WHERE number='"+num+"'",new String [] {});
	cur.moveToFirst();
	if(cur.getCount()>0)
		return true;
return false;
}

public void remove(String num)
{
bdd.execSQL("DELETE FROM numbers WHERE number = '"+num+"';");
}

public Cursor getNUMBERS() {
	// TODO Auto-generated method stub
	Cursor cur=bdd.rawQuery("SELECT * FROM numbers",new String [] {});
	cur.moveToFirst();
	return cur;
}

public boolean getDBT() {
	// TODO Auto-generated method stub
	Cursor cur=bdd.rawQuery("SELECT * from "+PHSTable,new String [] {});
	cur.moveToFirst();
		return cur.getString(cur.getColumnIndex("DBT")).equals("false");
}

public boolean getALRG() {
	// TODO Auto-generated method stub
	Cursor cur=bdd.rawQuery("SELECT * from "+PHSTable,new String [] {});
	cur.moveToFirst();
		return cur.getString(cur.getColumnIndex("ALRG")).equals("false");
}

public void setDBT(String val) {
	// TODO Auto-generated method stub
	bdd.execSQL("UPDATE "+PHSTable+" SET DBT='"+val+"';");
}

public void setALRG(String val) {
	// TODO Auto-generated method stub
	bdd.execSQL("UPDATE "+PHSTable+" SET ALRG='"+val+"';");
}

public Cursor getINFOS() {
	// TODO Auto-generated method stub
	Cursor cur=bdd.rawQuery("SELECT * FROM INFOS",new String [] {});
	cur.moveToFirst();
	return cur;
}

public void setINFOS(String label, String value) {
	// TODO Auto-generated method stub
	bdd.execSQL("UPDATE "+INFOTable+" SET value='"+value+"' WHERE label='"+label+"';");
}
}