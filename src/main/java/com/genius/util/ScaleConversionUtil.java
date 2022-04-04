package com.genius.util;

/**
 * @author icedir
 * @date 2022-04-04
 */
public class ScaleConversionUtil {

    public static String convert10To62(int num){
        if(num <= 0){
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        long remainder;
        while (num > 0){
            remainder = num % 62;
            //0-9
            if(remainder < 10){
                sb.append((char)('0' + remainder));
            }
            //A-Z
            else if(remainder < 36){
                sb.append((char)('A' + remainder - 10));
            }
            //a-z
            else{
                sb.append((char)('a' + remainder - 36));
            }

            num = num / 62;
        }
        //在上面的循环过程中，后一次循环本应是计算出来的高位字符，但是却被我们放在字符串的最后面，因此最终结果需要反转
        return sb.reverse().toString();
    }
}
