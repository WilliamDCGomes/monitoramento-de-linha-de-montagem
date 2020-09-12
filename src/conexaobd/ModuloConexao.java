package conexaobd;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ModuloConexao {
    public static Connection conector(){
        java.sql.Connection conexao = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost/linha_de_montagem?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String password="";
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERRO AO SE CONECTAR COM O BANCO DE DADOS\nERRO:" + e);
            return null;
        }
    }
}
