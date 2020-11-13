package functions;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connectionbd.ConnectionModule;
public class DesconectStation {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    GetDate getDate = new GetDate();
    public DesconectStation(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public void disconect (String login, int status){
        String sql = "insert into disconnectStation(dats,station,statusStation) values(?,?,?)";
        try {
            pst2 = connection.prepareStatement(sql);
            pst2.setString(1,getDate.informDate());
            pst2.setString(2, login);
            pst2.setInt(3, status);
            pst2.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void removeDisconect (String login){
        String sql = "delete from disconnectStation where station = ?";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, login);
                pst.executeUpdate();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public int isWorking(String login){
        String sql ="select statusStation from disconnectStation where station = ? and dats = ?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, getDate.informDate());
            rs= pst.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)==1){
                    return 1;
                }
                else if(rs.getInt(1)==2){
                    return 2;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
}
