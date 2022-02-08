/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */

package zad1;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Calc {
    Map<String , String> map = new HashMap<>();
    static BigDecimal bd1;
    static BigDecimal bd2;
    public String doCalc(String s) {
        bd1 = new BigDecimal(s.substring(0, s.indexOf(" ")));
        bd2 = new BigDecimal(s.substring(s.lastIndexOf(" ") + 1));
        map.put("+", Calc.add(bd1, bd2));
        map.put("-", Calc.minus(bd1,bd2));
        map.put("*", Calc.multiply(bd1,bd2));
        map.put("/", Calc.divide(bd1,bd2));
        try {
            String tmp = s.replaceAll("\\d","");
            tmp = tmp.replaceAll("\\s","");
            return map.get(tmp);
        }catch (Exception e) {
            return "Invalid command to calc";
        }
    }
    public static String add(BigDecimal bd1, BigDecimal bd2){
        return bd1.add(bd2).toString();
    }
    public static String minus(BigDecimal bd1, BigDecimal bd2){
        return bd1.subtract(bd2).toString();
    }

    public static String multiply(BigDecimal bd1, BigDecimal bd2){
        return bd1.multiply(bd2).toString();
    }
    public static String divide(BigDecimal bd1, BigDecimal bd2){
        return bd1.divide(bd2, 7, RoundingMode.HALF_UP).toString();
    }

}  
