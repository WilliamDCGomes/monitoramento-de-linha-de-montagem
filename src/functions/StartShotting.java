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
    public void startProduction(){
        String begin = null;
        String end = null;
        String[]beginDefault;
        String date = getLastProgramming();
        if(hasAProgramming(date)){
            String sql = "select beginning, ending from planning where dats = ? and shooting=? order by id asc limit 1";
            try {
                pst=connection.prepareStatement(sql);
                pst.setString(1, date);
                pst.setInt(2, 1);
                rs= pst.executeQuery();
                if(rs.next()){
                    begin = rs.getString(1);
                    end = rs.getString(2);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            insertShot(begin, 1);
        }
        else{
            beginDefault = hourDefault.getHour(1).split("/");
            insertShot(beginDefault[0], 1);
            begin = beginDefault[0];
            end = beginDefault[1];
        }
        ManyTime manyTime = new ManyTime();
        if(manyTime.check()==-1){
            InsertManyTime insertManyTime = new InsertManyTime();
            TimeDifference timedifference = new TimeDifference();
            HourToMinute hourToMinute = new HourToMinute();
            insertManyTime.add(hourToMinute.getMinute(timedifference.getDifference(begin, end)));
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
