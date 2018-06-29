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

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        int a = 0b11000000000000000000000000000000;
        System.out.println(Integer.parseInt(Integer.toString(a*-1,2),2));
        System.out.println(Integer.toString(a*-1,2));
        System.out.println(Integer.toBinaryString(a));
        //a = a | 0b11111111111111111111111111111111;
        System.out.println(a);
        short b = (short)0b1100000000000000;
        System.out.println(Integer.toBinaryString(Short.toUnsignedInt(b)));
        System.out.println(Integer.toBinaryString(-1));
    }

    public static int toUnsigedInt(short a){
        return Short.toUnsignedInt(a);
    }
}
