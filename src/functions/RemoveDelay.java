package functions;
import conexaobd.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class RemoveDelay {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public RemoveDelay(){
        conexao = ModuloConexao.conector();
    }
    public void remove(int id){
        String sql = "delete from delay where id = ?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setInt(1, id);
            int apagado = pst.executeUpdate();
            if(apagado>0){
                JOptionPane.showMessageDialog(null,"SUA EXECUÇÃO FOI REINICIADA!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
