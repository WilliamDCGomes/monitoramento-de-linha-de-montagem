package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author willi_bg
 */
public class BeginPresentShot {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDate getDate = new GetDate();
    public BeginPresentShot(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public String getBegin(int shot){
        String sql ="select beginning from presentshotting where dats = ? and shot = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setInt(2, shot);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
