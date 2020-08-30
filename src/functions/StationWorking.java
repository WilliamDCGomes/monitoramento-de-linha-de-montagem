package functions;
import conexaobd.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class StationWorking {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public StationWorking(){
        conexao = ModuloConexao.conector();
    }
    public boolean hasStation(){
        String sql ="select * from station where working = 1";
        try {
            pst=conexao.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
}
