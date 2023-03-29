package pne;

class LunaService {
    private LunaService(){}
    static boolean isValidLuhn(char[] value) {
        int sum = Character.getNumericValue(value[value.length - 1]);
        int parity = value.length % 2;
        for (int i = value.length - 2; i >= 0; i--) {
            int summand = Character.getNumericValue(value[i]);
            if (i % 2 == parity) {
                int product = summand * 2;
                summand = (product > 9) ? (product - 9) : product;
            }
            sum += summand;
        }
        return (sum % 10) == 0;
    }
}
