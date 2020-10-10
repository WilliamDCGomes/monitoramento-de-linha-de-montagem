package functions;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author willi
 */
public class InputJustNumbers extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        InputJustNumbersAux inputJustNumbersAux = new InputJustNumbersAux();
        str=inputJustNumbersAux.makeUp(str);
        if (str == null){ 
            return;
        } 
        super.insertString(offset, str , attr);
    }
}
