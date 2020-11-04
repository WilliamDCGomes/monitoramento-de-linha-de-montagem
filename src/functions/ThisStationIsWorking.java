package functions;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
public class ThisStationIsWorking {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public ThisStationIsWorking(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public boolean isWorking(String login){
        String sql ="select working from stations where login = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, login);
            rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)==1){
                    return true;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
}
