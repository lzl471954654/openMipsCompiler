public class NumberFormatUtil {
    public static int toInt(String s){
        int result = 0;
        if (s.charAt(0) == '1'){
            char[] t = s.toCharArray();
            t[0] = '0';
            result = Integer.parseInt(new String(t),2);
            result = result | 0b10000000000000000000000000000000;
        }else {
            result = Integer.parseInt(s,2);
        }
        return result;
    }
}
