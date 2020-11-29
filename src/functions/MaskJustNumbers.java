package functions;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author willi
 */
public class MaskJustNumbers extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        MaskJustNumbersAux maskJustNumbersAux = new MaskJustNumbersAux();
        if (str == null){ 
            return;
        } 
        if(str.length()>1){
            for(int i = str.length() - 1; i >= 0; i--){
                String auxStr = maskJustNumbersAux.getString( str.substring(i, i + 1) );
                super.insertString(offset, auxStr , attr);
            }
            return;
        }
        str=maskJustNumbersAux.getString(str);
        super.insertString(offset, str , attr);
    }
}
