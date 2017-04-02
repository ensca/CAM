package com.example.aline.cam;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;


public class DBManager extends SQLiteOpenHelper {

        public static final String AERONEF_KEY = "idAeronef";
        public static final String AERONEF_NOM = "nom";
        public static final String AERONEF_TYPE = "type";
        public static final String AERONEF_ACQUISITION = "acquisition";
        public static final String AERONEF_ENVERGURE = "envergure";
        public static final String AERONEF_POIDS= "poids";
        public static final String AERONEF_REMARQUES = "remarques";

        public static final String AERONEF_TABLE_NAME = "aeronef";
        public static final String AERONEF_TABLE_CREATE =
                "CREATE TABLE " + AERONEF_TABLE_NAME + " (" +
                        AERONEF_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        AERONEF_NOM + " TEXT, " +
                        AERONEF_TYPE + " TEXT, " +
                        AERONEF_ACQUISITION + " TEXT, " +
                        AERONEF_ENVERGURE+" INTEGER, "+
                        AERONEF_POIDS+" INTEGER, "+
                        AERONEF_REMARQUES+" TEXT "+" )";

    public static final String VOL_KEY = "idVol";
    public static final String VOL_DATE = "date";
    public static final String VOL_HEUREDEB = "heureDeb";
    public static final String VOL_HEUREFIN = "heureFin";
    public static final String VOL_LIEU = "lieu";
    public static final String VOL_REMARQUES= "remarques";
    public static final String VOL_IDAERONEF= "idAeronef";

    public static final String VOL_TABLE_NAME = "vol";
    public static final String VOL_TABLE_CREATE =
            "CREATE TABLE " + VOL_TABLE_NAME + " (" +
                    VOL_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    VOL_DATE + " TEXT, " +
                    VOL_HEUREDEB + " TEXT, " +
                    VOL_HEUREFIN + " TEXT, " +
                    VOL_LIEU+" TEXT, "+
                    VOL_REMARQUES+" TEXT, "+
                    VOL_IDAERONEF+" INTEGER );";


    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(AERONEF_TABLE_CREATE);
        db.execSQL(VOL_TABLE_CREATE);
    }

    public static final String AERONEF_TABLE_DROP = "DROP TABLE IF EXISTS " + AERONEF_TABLE_NAME + ";";
    public static final String VOL_TABLE_DROP = "DROP TABLE IF EXISTS " + VOL_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(AERONEF_TABLE_DROP);
        //db.execSQL(VOL_TABLE_DROP);
        //onCreate(db);
    }


}
