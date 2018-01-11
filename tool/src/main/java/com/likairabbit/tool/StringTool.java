package com.likairabbit.tool;

import android.annotation.SuppressLint;

/**
 * Created by lochy on 15/5/12.
 */
@SuppressLint("DefaultLocale")
public class StringTool {


    public static String byteHexToSting(byte[] data) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int aR_data : data) {
            //            stringBuffer.append(Integer.toHexString(aR_data & 0x00ff));
            stringBuffer.append(String.format("%02x", aR_data & 0x00ff));
        }
        return stringBuffer.toString();
    }


    /**
     * byte[]转变为16进制String字符, 每个字节2位, 不足补0
     *
     * @param bytes
     *            需要转换的byte[]数组
     * @return 转换后的String队形
     */
    @SuppressLint("DefaultLocale")
	public static String getStringByBytes(byte[] bytes) {
        String result = null;
        String hex = null;
        if (bytes != null && bytes.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(bytes.length);
            for (byte byteChar : bytes) {
                hex = Integer.toHexString(byteChar & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                stringBuilder.append(hex.toUpperCase());
            }
            result = stringBuilder.toString();
        }
        return result;
    }


    /**
     * 16进制String字符转换成byte数组
     *
     * @param hexString
     *            需要转换的字符串
     * @return 转换后的byte[]数组
     */
    @SuppressLint("DefaultLocale")
	public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }



    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }



    /**
     * 金额字符串转换成int类型，如果没有小数点则后面补00，有小数点小数点后移两位
     *
     * @param str
     *            需要转换的字符串
     * @return 转换后的int值
     */
	public static int CutString(String str){
		if (str.equals(""))
			return -1;
		if (str.contains(".")) {
			int a = 0;
			for (int i = str.length() - 1; i >= 0; i--) {

				char c = str.charAt(i);
				if ('.' == c) {
					if (a == 0) {
						str = str.substring(0, i) + "00";
					} else if (a == 1) {
						str = str.substring(0, i) + str.substring(i + 1) + "0";
					} else if (a == 2) {
						str = str.substring(0, i) + str.substring(i + 1);
					}
				}
				a++;
			}
		} else {
			str = str + "00";
		}	
		return Integer.parseInt(str);
	}

    /**
     * 将byte[]数组转化为String类型
     * @param arg
     *            需要转换的byte[]数组
     * @param length
     *            需要转换的数组长度
     * @return 转换后的String队形
     */
    public static String toHexString(byte[] arg, int length) {
        String result = new String();
        if (arg != null) {
            for (int i = 0; i < length; i++) {
                result = result
                        + (Integer.toHexString(
                        arg[i] < 0 ? arg[i] + 256 : arg[i]).length() == 1 ? "0"
                        + Integer.toHexString(arg[i] < 0 ? arg[i] + 256
                        : arg[i])
                        : Integer.toHexString(arg[i] < 0 ? arg[i] + 256
                        : arg[i])) + " ";
            }
            return result;
        }
        return "";
    }


    /**
     * 将int类型余额转换成M1卡专用格式---00000000FFFFFFFF0000000014EB14EB
     * @param yues
     *            需要转换的余额

     * @return 转换后的String队形
     */
    public static String intToLongHexString(int yues){
        byte[] bytes = intToBytes(yues);
        byte[] bytes1  =new byte[bytes.length];
        for(int i = 0;i<bytes.length;i++){
            bytes1[i]=(byte)~bytes[i];
            }
        return byteHexToSting(bytes)+byteHexToSting(bytes1)+byteHexToSting(bytes)+"14eb14eb";
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
     * @param value
     *            要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytes( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用
     */
    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }


}
