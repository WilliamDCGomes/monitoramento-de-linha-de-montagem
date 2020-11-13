package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class BeginProdution {
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    GetDate getDate = new GetDate();
    GetYesterdayDate getYesterdayDate = new GetYesterdayDate();
    GetHour getHour = new GetHour();
    HourDefault hourDefault = new HourDefault();
    public BeginProdution(){
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
    public String getProduction(int shot){
        String date = getLastProgramming();
        if(hasAProgramming(date)){
            String sql = "select beginning from planning where dats = ? and shooting=? order by id asc limit 1";
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
        }
        else{
            String[] vet = hourDefault.getHour(shot).split("/");
            return vet[0];
        }
        return null;
    }
    private boolean hasAProgramming(String date){
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
