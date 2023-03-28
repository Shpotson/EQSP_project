package pne;
import java.util.List;
import org.cactoos.*;

public final class Ccsrch {

    private Ccsrch(){}

    public static String HideCreditCardInformationInText(String text){
        char[] charArray = text.toCharArray();

        List<ArraySlice> indexPairs =  CreditCardInformationSearchService.Search(charArray);

        for (ArraySlice indexPair : indexPairs){
            if (LunaService.isValidLuhn(indexPair.GetSlice())){
                for (int i = indexPair.GetBegin() + 2; i < indexPair.GetEnd() - 2; i++){
                    if (indexPair.IsExcluded(i)){
                        charArray[i] = '*';
                    }
                }
            }
        }

        return new String(charArray);
    }
}
