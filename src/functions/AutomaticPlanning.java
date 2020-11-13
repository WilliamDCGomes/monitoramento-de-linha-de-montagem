package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author willi_bg
 */
public class AutomaticPlanning {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    GetDate getDate = new GetDate();
    public AutomaticPlanning(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    private String getLastProgramming(){
        String sql = "select dats from planning order by id desc limit 1";
        try {
            pst=connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return "01/01/1900";
    }
    public ArrayList<String> getLastPlanning(){
        String date = getLastProgramming();
        ArrayList<String> list = new ArrayList<String>();
        String sql ="select beginning, ending, shooting from planning where dats = ?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, date);
            rs2= pst2.executeQuery();
            while(rs2.next()){
                list.add(rs2.getString(1) + ";" + rs2.getString(2) + ";" + rs2.getInt(3));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        if(list.size()>0){
            return list;
        }
        return null;
    }
    public void add(String start, String end, int numberShotting){
        String sql = "insert into planning(dats,shooting,beginning,ending)values(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,getDate.informDate());
            pst.setInt(2,numberShotting);
            pst.setString(3,start);
            pst.setString(4,end);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
