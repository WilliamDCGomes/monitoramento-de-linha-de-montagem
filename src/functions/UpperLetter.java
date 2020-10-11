package functions;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author willi
 */
public class UpperLetter extends PlainDocument{
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        UpperLetterAux upperLetter = new UpperLetterAux();
        if (str == null){ 
            return;
        } 
        str=upperLetter.makeUp(str);
        super.insertString(offset, str , attr);
    }
}
