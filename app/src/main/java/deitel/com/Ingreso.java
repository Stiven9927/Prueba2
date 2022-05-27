package deitel.com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Ingreso extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextApellido;
    EditText et1,et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellido = findViewById(R.id.editTextApellido);
    }

    public void onClicInsertar(View view)
    {
        IngresoDB ingresoDB = new IngresoDB(this, "CLIENTES_DB", null, 1);

        // abrir la BD en modo lectura (SELECT)
        //SQLiteDataBase sql = clientsDataBase.getReadableDatabase()

        // abrir la BD en modo escritura (SELECT, DML, DDL)
        SQLiteDatabase sql = ingresoDB.getWritableDatabase();

        // obtener los datos ingresados en los controles
        String nombre = editTextNombre.getText().toString();
        String apellido = editTextApellido.getText().toString();

        // problemas de seguridad: SQL INJECTION
        //sql.execSQL("INSERT INTO Clientes(Codigo, Nombre, Apellido, Saldo) " +
        //        " VALUES('"+codigo+"', '"+nombre+"', "+saldo+")");

        ContentValues values = new ContentValues();
        //values.put("Codigo", codigo);
        values.put("Nombre", nombre);
        values.put("Apellido", apellido);

        // enviar a la base de datos
        sql.insert("Clientes",null, values);

        //cerrar la Base de Datos
        sql.close();


        Toast.makeText(this, "Se inserto el cliente", Toast.LENGTH_SHORT).show();

    }

    public void InicioSesion(View view){
        IngresoDB clientsDataBase = new IngresoDB()this, "CLIENTES_DB", null, 1);
        SQLiteDatabase sql = clientsDataBase.getReadableDatabase();
        String codigo = editTextCodigo.getText().toString();
        String SELECT = "SELECT Nombre, Apellido, Saldo " +
                "FROM Clientes WHERE Codigo=" + codigo;
        "FROM Clientes WHERE Codigo=" + codigo;Cursor cursor = sql.rawQuery(SELECT, null);

        Cursor cursor = sql.rawQuery(SELECT, null);

        if (cursor.moveToFirst())
        {
            editTextNombre.setText(cursor.getString(0));
            editTextApellido.setText(cursor.getString(1));
            editTextSaldo.setText(cursor.getString(2));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "No se encontro el cliente", Toast.LENGTH_SHORT).show();
            limpiarControles();
        }

    }
}