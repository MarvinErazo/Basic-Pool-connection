import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        testConnection();
    }
    static void testConnection (){
        try {
            Connection conect = ConnectionPool.getInstance().getConnection();

            /*
            TRANSACCIONES...
            */

            System.out.println(conect != null ? "Conectado" : "No Conectado");
            //ConnectionPool.getInstance().getSettings();
            ConnectionPool.getInstance().closeConnection(conect);

        }catch (SQLException e){
            System.out.println("Error en la conexion => " + e.getMessage());
        }
    }
}