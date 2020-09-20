package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class StartShotting {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDate getDate = new GetDate();
    GetYesterdayDate getYesterdayDate = new GetYesterdayDate();
    GetHour getHour = new GetHour();
    HourDefault hourDefault = new HourDefault();
    
    public StartShotting(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public void keepProduction(int shot, String begin){
        insertShot(begin, shot);
    }
    public void startProduction(){
        String begin = null;
        if(hasAProgramming(getDate.informDate())){
            String sql = "select beginning from planning where dats = ? and shooting=? order by id asc limit 1";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, getDate.informDate());
                pst.setInt(2, 1);
                rs= pst.executeQuery();
                if(rs.next()){
                    begin = rs.getString(1);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            insertShot(begin, 1);
        }
        else if(hasAProgramming(getYesterdayDate.informDate())){
            String sql = "select beginning from planning where dats = ? and shooting=? order by id asc limit 1";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, getYesterdayDate.informDate());
                pst.setInt(2, 1);
                rs= pst.executeQuery();
                if(rs.next()){
                    begin = rs.getString(1);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            insertShot(begin, 1);
        }
        else{
            String[]beginDefault = hourDefault.getHour(1).split("/");
            insertShot(beginDefault[0], 1);
        }
    }
    private void insertShot(String begin, int shot){
        String sql = "insert into presentShotting(dats,shot,beginning)values(?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,getDate.informDate());
            pst.setInt(2,shot);
            pst.setString(3,begin);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public boolean hasAProgramming(String date){
        String sql ="select * from planning where dats = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, date);
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
