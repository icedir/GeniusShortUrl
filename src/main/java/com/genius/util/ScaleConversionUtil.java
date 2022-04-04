package com.genius.util;

/**
 * Hex conversion tool class
 *
 * @author icedir
 * @date 2022-04-04
 */
class ScaleConversionUtil {

    /**
     * decimal to sixty binary
     * @param num Must be a positive integer, otherwise it is treated as 0
     * @return sixty binary string
     */
    static String convert10To62(int num){
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
        // In the above loop process,
        // the next loop should be the calculated high-order character,
        // but it is placed at the end of the string, so the final result needs to be reversed
        return sb.reverse().toString();
    }
}
