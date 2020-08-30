package functions;
import conexaobd.ModuloConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
public class InputDelay {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    PreparedStatement pst2 = null;
    ResultSet rs2 = null;
    public InputDelay(){
        conexao = ModuloConexao.conector();
    }
    GetBeginOfDelay getBeginOfDelay = new GetBeginOfDelay();
    private int shot;
    private String dats;
    private String station;
    private String end;
    public void makeInput(String reasonDelay,String typeDelay){
        String sql = "insert into delay(id,shot,dats,reasonDelay,typeDelay,localeOfDelay,beginningDelay,endingDelay)values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,getId());
            pst.setInt(2,shot);
            pst.setString(3,dats);
            pst.setString(4,reasonDelay);
            pst.setString(5,typeDelay);
            pst.setInt(6,Integer.parseInt(station));
            pst.setString(7,getBeginOfDelay.getBegin(shot));
            pst.setString(8,end);
            pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private int getId(){
        String sql = "select id,shot,dats,station,ending from workfinish order by id desc limit 1";
        try {
            pst2=conexao.prepareStatement(sql);
            rs2= pst2.executeQuery();
            if(rs2.next()){
                shot=rs2.getInt(2);
                dats=rs2.getString(3);
                station=rs2.getString(4);
                end=rs2.getString(5);
                return rs2.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return -1;
    }
}
