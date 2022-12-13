import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.SQLException;

//SINGLETON PATTERN
public class ConnectionPool {
    private final String DB = "example";
    private final String URL = "jdbc:postgresql://localhost:5432/"+ DB;
    private final String USER = "postgres";
    private final String PASS = "admin";
    private final String DRIVER = "org.postgresql.Driver";
    private static ConnectionPool dataSource;
    private BasicDataSource basicDataSource = null;

    private ConnectionPool() {
        basicDataSource = new BasicDataSource();

        basicDataSource.setDriverClassName(DRIVER);
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
/*
        basicDataSource.setMinIdle(5); // Minimo de conexiones inactivas
        basicDataSource.setMaxIdle(20);// Maximo de conexiones inactivas
        basicDataSource.setMaxTotal(50);// Conexiones Activas
        basicDataSource.setMaxWaitMillis(-1); //Tiempo de espera

 */
    }
    public static ConnectionPool getInstance(){
        if(dataSource == null)
            dataSource = new ConnectionPool();
        return dataSource;
    }
    public Connection getConnection(){
        Connection connect = null;
        try {
            connect = this.basicDataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Error de conexion => "+ e.getMessage());
        }
        return connect;
    }
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }

    public void getSettings(){
        System.out.println("Minimo de conexiones inactivas: " + basicDataSource.getMinIdle());
        System.out.println("Minimo de conexiones inactivas: " + basicDataSource.getMaxIdle());
        System.out.println("Maximo de conexiones activas: " + basicDataSource.getMaxTotal());
        System.out.println("Tiempo de espera: " + basicDataSource.getMaxWaitMillis());
    }
}
