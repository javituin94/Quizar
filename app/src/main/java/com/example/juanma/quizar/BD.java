package com.example.juanma.quizar;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juanma on 21/04/2015.
 */
public class BD extends SQLiteOpenHelper {
    //Ruta por defecto de las bases de datos en el sistema Android
    private static String DB_PATH = "/data/data/com.example.juanma.quizar/databases/";

    private static String DB_NAME = "preguntas.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;
    private List resultados;

    /**
     * Constructor
     * Toma referencia hacia el contexto de la aplicación que lo invoca para poder acceder a los 'assets' y 'resources' de la aplicación.
     * Crea un objeto DBOpenHelper que nos permitirá controlar la apertura de la base de datos.
     * @param context
     */
    public BD(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;

    }

    /**
     * Crea una base de datos vacía en el sistema y la reescribe con nuestro fichero de base de datos.
     * */
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist){
//la base de datos existe y no hacemos nada.
        }else{
//Llamando a este método se crea la base de datos vacía en la ruta por defecto del sistema
//de nuestra aplicación por lo que podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {
                throw new Error("Error copiando Base de Datos");
            }
        }

    }

    /**
     * Comprueba si la base de datos existe para evitar copiar siempre el fichero cada vez que se abra la aplicación.
     * @return true si existe, false si no existe
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{

            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

//si llegamos aqui es porque la base de datos no existe todavía.

        }
        if(checkDB != null){

            checkDB.close();

        }
        return checkDB != null ? true : false;
    }

    /**
     * Copia nuestra base de datos desde la carpeta assets a la recién creada
     * base de datos en la carpeta de sistema, desde dónde podremos acceder a ella.
     * Esto se hace con bytestream.
     * */
    private void copyDataBase() throws IOException{

//Abrimos el fichero de base de datos como entrada
        InputStream myInput = myContext.getAssets().open(DB_NAME);

//Ruta a la base de datos vacía recién creada
        String outFileName = DB_PATH + DB_NAME;

//Abrimos la base de datos vacía como salida
        OutputStream myOutput = new FileOutputStream(outFileName);

//Transferimos los bytes desde el fichero de entrada al de salida
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

//Liberamos los streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void open() throws SQLException {

//Abre la base de datos
        try {
            createDataBase();
        } catch (IOException e) {
            throw new Error("Ha sido imposible crear la Base de Datos");
        }

        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
/**
 * A continuación se crearán los métodos de lectura, inserción, actualización
 * y borrado de la base de datos.
 * */


    public FactoriaQuestions extraerPregunta(int categoria,ArrayList id_preguntas){
        FactoriaQuestions p=null;
        switch (categoria){
            case 1:
                p = new FactoriaQuestionsCine();
                break;
            case 2:
                p = new FactoriaQuestionsMusica();
                break;
            case 3:
                p = new FactoriaQuestionsHistoria();
                break;
            case 4:
                p = new FactoriaQuestionsJuegos();
                break;
            case 5:
                p = new FactoriaQuestionsSeries();
                break;
            case 6:
                p = new FactoriaQuestionsDeportes();
                break;
        }

        if(p!=null) {
            Cursor fila;
            do{
                fila = myDataBase.rawQuery("SELECT pregunta,resp1,resp2,resp3,resp4,correcta,imagen,audio,id_pregunta FROM " +
                        "preguntas,respuestas where preguntas.id_respuesta=respuestas.id_resp AND preguntas.id_tipo=" + categoria + " ORDER BY random() LIMIT 1", null);
                fila.moveToFirst();
            }while(id_preguntas.contains(fila.getInt(8)));

            p.añadirPreguntas(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getInt(5), fila.getString(6), fila.getString(7),fila.getInt(8));
        }

        return p;
    }

    public FactoriaQuestions extraerPregunta(int categoria){
        FactoriaQuestions p=null;
        switch (categoria){
            case 1:
                p = new FactoriaQuestionsCine();
            break;
            case 2:
                p = new FactoriaQuestionsMusica();
            break;
            case 3:
                p = new FactoriaQuestionsHistoria();
            break;
            case 4:
                p = new FactoriaQuestionsJuegos();
            break;
            case 5:
                p = new FactoriaQuestionsSeries();
            break;
            case 6:
                p = new FactoriaQuestionsDeportes();
            break;
        }

        if(p!=null) {
            Cursor fila = myDataBase.rawQuery("SELECT pregunta,resp1,resp2,resp3,resp4,correcta,imagen,audio,id_pregunta FROM " +
                    "preguntas,respuestas where preguntas.id_respuesta=respuestas.id_resp AND preguntas.id_tipo=" + categoria + " ORDER BY random() LIMIT 1", null);
            fila.moveToFirst();

            p.añadirPreguntas(fila.getString(0), fila.getString(1), fila.getString(2), fila.getString(3), fila.getString(4), fila.getInt(5), fila.getString(6), fila.getString(7),fila.getInt(8));
        }

        return p;
    }

    public void introducirResultados(int puntos) {
        Cursor fila = myDataBase.rawQuery("SELECT count() FROM resultados", null);
        fila.moveToFirst();
        System.out.println("Count: "+fila.getInt(0));
        if(fila.getInt(0)<10) {

            myDataBase.execSQL("INSERT INTO resultados (puntos) VALUES(" + puntos + ")");
            System.out.println("Introducido");
        }
        else {
            fila = myDataBase.rawQuery("SELECT id FROM resultados WHERE puntos<=" + puntos + " ORDER BY id DESC LIMIT 1", null);
            fila.moveToFirst();
            myDataBase.execSQL("UPDATE resultados SET puntos = "+puntos+" WHERE id="+fila.getInt(0));
            System.out.println("Actualizado");
        }
    }

    public ArrayList getResultados() {
        ArrayList<Resultados> lista = new ArrayList<Resultados>();
        Cursor fila = myDataBase.rawQuery("SELECT * FROM resultados ORDER BY puntos DESC",null);
        fila.moveToFirst();
        do {
           System.out.println(fila.getInt(0));
            lista.add(new Resultados(fila.getInt(0),fila.getInt(1)));
        }while(fila.moveToNext());
        return lista;
    }
}
