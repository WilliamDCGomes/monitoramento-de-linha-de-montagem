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
public class GetDurationShot {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDate getDate = new GetDate();
    public GetDurationShot(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public String getDurationShot(int shot){
        TimeDifference td = new TimeDifference();
        String sql ="select beginning, ending from planning where dats = ? and shooting = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setInt(2, shot);
            rs= pst.executeQuery();
            if(rs.next()){
                return td.getDifference(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
}
