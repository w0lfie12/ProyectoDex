package com.example.pruebas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reto.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    SQLiteDatabase db;
    private static final String DATABASE_PATH = "/data/data/com.example.pruebas/databases/";
    private final String USER_TABLE = "user";
    private final String TABLA_FICHAR= "fichar";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
       // createDb();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(username text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDb(){
        boolean dbExist = checkDbExist();

        if(!dbExist){
            this.getReadableDatabase();
            copyDatabase();
        }
    }

    private boolean checkDbExist(){
        SQLiteDatabase sqLiteDatabase = null;

        try{
            String path = DATABASE_PATH + DATABASE_NAME;
            sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception ex){
        }

        if(sqLiteDatabase != null){
            sqLiteDatabase.close();
            return true;
        }

        return false;
    }

    private void copyDatabase(){
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);

            String outFileName = DATABASE_PATH + DATABASE_NAME;

            OutputStream outputStream = new FileOutputStream(outFileName);

            byte[] b = new byte[1024];
            int length;

            while ((length = inputStream.read(b)) > 0){
                outputStream.write(b, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private SQLiteDatabase openDatabase(){
        String path = DATABASE_PATH + DATABASE_NAME;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        return db;
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }
    public String id(String username){
       //Cursor cursor= db.rawQuery("select iduser from user where username =?",null);
        Cursor cursor= db.rawQuery("select idUser from user where username= '"+ username+ "'",null);
String id= cursor.getString(cursor.getColumnIndexOrThrow("idUser"));
return id;
    }
    public Empleado nombre(String username){
        Empleado empleado = new Empleado();
        db = openDatabase();
        Cursor cursorNombre= db.rawQuery("select correo,nombre,apellido1,apellido2,dni,idempleado,username from empleado where username= '"+ username+ "'",null);
        if(cursorNombre != null && cursorNombre.getCount()>0){
            cursorNombre.moveToFirst();
            do{
                empleado.setNombre(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("nombre")));
                empleado.setIdempleado(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("idempleado")));
                empleado.setCorreo(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("correo")));
                empleado.setApellido1(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("apellido1")));
                empleado.setApellido2(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("apellido2")));
                empleado.setUsername(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("username")));
                empleado.setDni(cursorNombre.getString(cursorNombre.getColumnIndexOrThrow("dni")));
            }while(cursorNombre.moveToNext());
            cursorNombre.close();

        }
        return empleado;
    }
  /*  public empleado obteneraInformacionEmpleado(String username) {
        empleado empleado = new empleado();
Cursor cur= db.rawQuery("select idempleado,correo,nombre,apellido1,apellido2,dni from empleado where username=?", null);
if(cur != null && cur.getCount()>0){
    cur.moveToFirst();
    do{
        empleado.setIdempleado(cur.getString(cur.getColumnIndexOrThrow("idempleado")));
        empleado.setCorreo(cur.getString(cur.getColumnIndexOrThrow("correo")));
        empleado.setNombre(cur.getString(cur.getColumnIndexOrThrow("nombre")));
        empleado.setApellido1(cur.getString(cur.getColumnIndexOrThrow("apellido1")));
        empleado.setApellido2(cur.getString(cur.getColumnIndexOrThrow("apellido2")));
        empleado.setDni(cur.getString(cur.getColumnIndexOrThrow("dni")));

    }while(cur.moveToNext());
    cur.close();

}
return empleado;
}*/

    public User obtenerIdUsername(String username){
        String[] columns = {"username, idUser,email"};
        User user= new User();
        db = openDatabase();
//String a=

        Cursor cur=  db.rawQuery("select username,email,idUser from user where username= '"+ username+ "'",null);
       // Cursor cur= db.rawQuery("select username,email,idUser from user where username= '"+ username+ "'",null);
        //Cursor cur= db.rawQuery("select username,email,idUser from user where username =? ", null);
        if(cur!=null && cur.getCount()>0){
            cur.moveToFirst();
            do{

                user.setUsername(cur.getString(cur.getColumnIndexOrThrow("username")));
                user.setIdUser(cur.getString(cur.getColumnIndexOrThrow("idUser")));
                user.setEmail(cur.getString(cur.getColumnIndexOrThrow("email")));
            }while(cur.moveToNext());
            cur.close();

        }

        /*
        List<String> userInfo;
        userInfo = new ArrayList<String>();
        userInfo.add(user.getUsername());
        userInfo.add(user.getIdUser());
        userInfo.add(user.getEmail());

        Intent intent = new Intent(, MenuPrincipal.class);
        intent.putExtra("miLista", (Parcelable) userInfo);
        context.startActivity(intent);
*/
return user;
    }

    public  void  fichar(String resultado1,String dia,String fentrada, String fsalida, String IdProyecto){
        db = openDatabase();
        String cadena = "Insert into fichar(idusuario,idproyecto,fechaentrada,fechasalida,dia)values('"+ resultado1 +"','"+ IdProyecto +"','"+ fentrada +"','"+fsalida+"','"+ dia+"')";
       // String cadena="Insert into fichar(idusuario,idproyecto,fechaentrada,fechasalida)values("+ resultado1 +","+ IdProyecto +",'hola','hola')";
//db.execSQL("Insert into fichar(idusuario,idproyecto,fechaentrada,fechasalida)values("+ resultado1 +","+ IdProyecto +",hola,hola)");
        db.execSQL(cadena);
    }
    public void GastoMas(Integer IdDepartamento1, Integer IdProyecto1,String IdUsuario,String FInicio, String FFin,String Continente, String Pais,String Ciudad,Double Dietas,Double  Total){
        db = openDatabase();
        String cadena= "Insert into gastos(idproyecto,iddepartamento,idusuario,finicio,ffin,continente,pais,ciudad,dieta,total) values('"+ IdProyecto1 +"','"+ IdDepartamento1 +"','"+ IdUsuario +"','"+ FInicio +"','"+ FFin +"','"+ Continente +"','"+ Pais +"','"+Ciudad+"','"+Dietas+"','"+ Total+"')";
        db.execSQL(cadena);
    }
    public void GastoMenos(String IdDept, String IdProy, String IdUser,String Transporte,String Fecha, String Hora,Double PagoKm, Double Peaje,Double Parking,Double Dietas, String Precio){
    db=openDatabase();
    String cadena= "Insert into gastos(idproyecto,iddepartamento,idusuario,fecha,hora,transporte,kmtotales,peaje,parking,dieta,total) values('"+ IdProy +"','"+ IdDept +"','"+ IdUser +"','"+ Fecha +"','"+ Hora +"','"+ Transporte +"','"+ PagoKm +"','"+ Peaje +"','"+ Parking +"','"+ Dietas +"','"+ Precio +"')";
    db.execSQL(cadena);
    }

    public boolean checkUserExist(String username, String pass){
        String[] columns = {"username"};
        db = openDatabase();

        String selection = "username=? and password = ?";
        String[] selectionArgs = {username, pass};

        Cursor cursor = db.query(USER_TABLE, columns, selection, selectionArgs, null, null, null);
        // Cursor cursor = db.rawQuery("Select username, password from user where username= '" + username + "' and password='" + password + "'", null);
       // Cursor cursor= db.rawQuery("select count (*) from user where username= '"+ username+ "'", null);
        //Cursor cursor= db.rawQuery("select username, password from user where username='admin' and password='admin'", null);
        int count = cursor.getCount();

        cursor.close();
       close();

        if(count > 0){
            return true;
        } else {
            return false;
        }
    }
    public  String recuperarContraseña(String username){
        db= openDatabase();
        Cursor cursor= db.rawQuery("select password from user where username=?", null);
        String passwordBd= cursor.getString(cursor.getColumnIndexOrThrow("password"));
        return passwordBd;
    }
    public String AlmacenarDatos(String username){
        String[] columns = {"username"};
        db = openDatabase();
        String selection = "username=?";
        String[] selectionArgs = {username};
        Cursor cursorName= db.rawQuery("select * from user where username =? ", null);
       // Cursor cursorName= db.rawQuery("select nombre from user where username =? ", null);
        String nombre= String.valueOf(cursorName);
        return nombre;



    }


}
