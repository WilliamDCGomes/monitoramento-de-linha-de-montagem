package functions;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
public class ShotAfterPlanning {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    GetDate getDate = new GetDate();
    public String beginNoMoreshots="";
    public ShotAfterPlanning(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public void insertShotAfterPlanning(int shot, String station, String begin){
        String sql = "insert into saveShotAfterPlanning(dats,shot,station,begin) values(?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,getDate.informDate());
            pst.setInt(2,shot);
            pst.setString(3,station);
            pst.setString(4,begin);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public int getShotAfterPlanning(String station){
        String sql ="select max(shot)from saveShotAfterPlanning where dats = ? and station=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setString(2,station);
            rs= pst.executeQuery();
            if(rs.next()){
                int shot = rs.getInt(1);
                if(shot==0){
                    return getShot();
                }
                getBeginAfterPlanning(station, shot);
                return shot;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return getShot();
    }
    private void getBeginAfterPlanning(String station, int shot){
        String sql ="select begin from saveShotAfterPlanning where dats = ? and station=? and shot=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, getDate.informDate());
            pst2.setString(2,station);
            pst2.setInt(3,shot);
            rs2= pst2.executeQuery();
            if(rs2.next()){
                beginNoMoreshots = rs2.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int getShot(){
        String sql ="select max(shot) from presentShotting where dats = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            rs= pst.executeQuery();
            if(rs.next()){
                int shot = rs.getInt(1);
                getBeginGetShot(shot);
                return shot;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return -1;
    }
    private void getBeginGetShot(int shot){
        String sql ="select beginning from presentShotting where dats = ? and shot=?";
        try {
            pst2=connection.prepareStatement(sql);
            pst2.setString(1, getDate.informDate());
            pst2.setInt(2,shot);
            rs2= pst2.executeQuery();
            if(rs2.next()){
                beginNoMoreshots = rs2.getString(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
