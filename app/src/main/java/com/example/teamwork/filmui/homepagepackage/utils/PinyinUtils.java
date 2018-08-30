package com.example.teamwork.filmui.homepagepackage.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音转换工具 * * @描述 TODO * @项目名称 App_imooc * @包名 com.android.imooc.quickIndex * @类名 PinyinUtils * @author chenlin * @date 2016年5月24日 下午9:27:03 * @version 1.0
 */
public class PinyinUtils {
    /**
     * 根据传入的字符串(包含汉字),得到拼音 * * @param str * 字符串 * @return
     */
    public static String getPinyin(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        StringBuilder sb = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (c >= -127 && c < 128) {
                sb.append(c);
            } else {
                String s = "";
                try {
                    s = PinyinHelper.toHanyuPinyinStringArray(c, format)[0];
                    sb.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                    sb.append(s);
                }
            }
        }
        return sb.toString();
    }
}
