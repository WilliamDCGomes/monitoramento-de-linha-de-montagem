package functions;
import connectionbd.ConnectionModule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
public class InsertManyTime {
    Connection connection = null;
    PreparedStatement pst = null;
    GetDate getDate = new GetDate();
     public InsertManyTime(){
        ConnectionModule connect = new ConnectionModule();
        connection = connect.getConnectionMySQL();
    }
    public void add(int quantity){
        String sql ="insert into manyTime (dats, quantity) values (?,?)";
        try {
            pst=connection.prepareStatement(sql);
            pst.setString(1, getDate.informDate());
            pst.setInt(2, quantity);
            pst.executeQuery();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void update(int quantity){
        String sql = "update manyTime set quantity=? where dats=?";
        try {
            pst=connection.prepareStatement(sql);
            pst.setInt(1, quantity);
            pst.setString(2, getDate.informDate());
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
}
