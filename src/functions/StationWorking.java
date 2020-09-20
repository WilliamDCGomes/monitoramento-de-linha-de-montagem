package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class StationWorking {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public StationWorking(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public boolean hasStation(){
        String sql ="select * from stations where working = 1";
        try {
            pst=connection.prepareStatement(sql);
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
