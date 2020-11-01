package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class GetTimeOfShot {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDate getDate = new GetDate();
    TimeDifference timeDifference = new TimeDifference();
    HourToMinute hourToMinute = new HourToMinute();
    public GetTimeOfShot(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public int getTime(int shot){
        String sql ="select beginning, ending from planning where dats = ? and shooting = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setInt(2, shot);
            rs= pst.executeQuery();
            if(rs.next()){
                return hourToMinute.getMinute( timeDifference.getDifference( rs.getString(1), rs.getString(2) ) );
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}
