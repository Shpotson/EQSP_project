package pne;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class CreditCardInformationSearchService {
    private static final int mastercardFirstTwoDigitsMin = 50;
    private static final int mastercardFirstTwoDigitsMax = 55;
    private static final int visaFirstOneDigit = 4;
    private static final int discoverFirstFourDigits = 6011;
    private static final int[] jcb16FirstFourDigits = {
            3088,
            3096,
            3112,
            3158,
            3337,
            3528,
            3529
    };
    private static final int[] amexFirstTwoDigits = {
            37,
            34
    };
    private static final int[] enrouteFirstFourDigits = {
            2014,
            2149
    };
    private static final int[] jcb15FirstFourDigits = {
            2131,
            1800,
            3528,
            3529
    };

    private CreditCardInformationSearchService(){}

    static List<ArraySlice> Search(char text[]){
        List<ArraySlice> output = new ArrayList<ArraySlice>();

        List<Character> tempCardInfo = new ArrayList<Character>();
        List<Integer> tempExcludeInfo = new ArrayList<Integer>();

        for(int i = 0; i < text.length; i++){
            if (text[i] ==  ' ' ){
                if (tempCardInfo.size() > 0){
                    tempExcludeInfo.add(i);
                }
                continue;
            }
            if (Character.isDigit(text[i])){
                tempCardInfo.add(text[i]);
                if (tempCardInfo.size() == 15 && CheckIfLooksLikeA15Card(tempCardInfo)){
                    int begin = i - 14 - tempExcludeInfo.size();
                    int end = i + 1;

                    output.add(new ArraySlice(begin, end, tempExcludeInfo, tempCardInfo));
                }
                else if (tempCardInfo.size() == 16 && CheckIfLooksLikeA16Card(tempCardInfo)){
                    int begin = i - 15 - tempExcludeInfo.size();
                    int end = i + 1;

                    output.add(new ArraySlice(begin, end, tempExcludeInfo, tempCardInfo));
                }
                else if (tempCardInfo.size() == 17){
                    tempCardInfo = tempCardInfo.subList(1, tempCardInfo.size() - 1);

                    if (CheckIfLooksLikeA16Card(tempCardInfo)){
                        int begin = i - 15 - tempExcludeInfo.size();
                        int end = i + 1;

                        output.add(new ArraySlice(begin, end, tempExcludeInfo, tempCardInfo));
                    }
                    else if (CheckIfLooksLikeA15Card(tempCardInfo.subList(0, 14))){
                        int begin = i - 14 - tempExcludeInfo.size();
                        int end = i + 1;

                        output.add(new ArraySlice(begin, end, tempExcludeInfo, tempCardInfo));
                    }
                }
                continue;
            }
            tempCardInfo.clear();
        }

        return output;
    }

    static private boolean CheckIfLooksLikeA16Card(List<Character> cardInfoToCheck){
        if (IsMastercard16(cardInfoToCheck)
                | IsVisa16(cardInfoToCheck)
                | IsDiscover16(cardInfoToCheck)
                | IsJcb16(cardInfoToCheck))
            return true;
        return false;
    }
    static private boolean CheckIfLooksLikeA15Card(List<Character> cardInfoToCheck){
        if (IsAmex15(cardInfoToCheck)
                | IsEnroute15(cardInfoToCheck)
                | IsJcb15(cardInfoToCheck))
            return true;
        return false;
    }

    static private boolean IsMastercard16(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1),};
        int number = Integer.parseInt(new String(buffer));

        if (number >= mastercardFirstTwoDigitsMin && number <= mastercardFirstTwoDigitsMax){
            return true;
        }
        return false;
    }

    static private boolean IsVisa16(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0)};
        int number = Integer.parseInt(new String(buffer));

        if (number == visaFirstOneDigit){
            return true;
        }
        return false;
    }

    static private boolean IsDiscover16(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1),
                cardInfoToCheck.get(2),
                cardInfoToCheck.get(3)};
        int number = Integer.parseInt(new String(buffer));

        if (number == discoverFirstFourDigits){
            return true;
        }
        return false;
    }

    static private boolean IsJcb16(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1),
                cardInfoToCheck.get(2),
                cardInfoToCheck.get(3)};
        int number = Integer.parseInt(new String(buffer));

        if (IntStream.of(jcb16FirstFourDigits).anyMatch(x -> x == number)){
            return true;
        }
        return false;
    }

    static private boolean IsAmex15(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1)};
        int number = Integer.parseInt(new String(buffer));

        if (IntStream.of(amexFirstTwoDigits).anyMatch(x -> x == number)){
            return true;
        }
        return false;
    }

    static private boolean IsEnroute15(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1),
                cardInfoToCheck.get(2),
                cardInfoToCheck.get(3)};
        int number = Integer.parseInt(new String(buffer));

        if (IntStream.of(enrouteFirstFourDigits).anyMatch(x -> x == number)){
            return true;
        }
        return false;
    }

    static private boolean IsJcb15(List<Character> cardInfoToCheck){
        char buffer[] = {
                cardInfoToCheck.get(0),
                cardInfoToCheck.get(1),
                cardInfoToCheck.get(2),
                cardInfoToCheck.get(3)};
        int number = Integer.parseInt(new String(buffer));

        if (IntStream.of(jcb15FirstFourDigits).anyMatch(x -> x == number)){
            return true;
        }
        return false;
    }

}
