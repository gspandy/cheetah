package org.cheetah.commons.utils;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Max
 */
public final class PinYinTranslator {

    private static final Map<String, String> PIN_YIN = Maps.newConcurrentMap();
    private static final Map<String, String> INITIAL = Maps.newConcurrentMap();
    private static final String SEPARATOR = "";

    public static String pinyin(String chinese) {
        if (StringUtils.isBlank(chinese)) {
            return "";
        }
        if (PIN_YIN.containsKey(chinese)) {
            return PIN_YIN.get(chinese);
        }
        String[] letter = chinese.split(SEPARATOR);
        StringBuilder pinyin = new StringBuilder();
        for (String it : letter) {
            if (!ChineseHelper.isChinese(it.charAt(0))) {
                pinyin.append(it.toLowerCase());
                break;
            }
            if (!PIN_YIN.containsKey(it)) {
                PIN_YIN.put(
                        it, PinyinHelper.convertToPinyinString(it, SEPARATOR, PinyinFormat.WITHOUT_TONE)
                );
            }
            pinyin.append(PIN_YIN.get(it));
        }
        PIN_YIN.put(chinese, pinyin.toString());
        return pinyin.toString();
    }

    public static String initial(String chinese) {
        if (StringUtils.isBlank(chinese)) {
            return "";
        }
        if (INITIAL.containsKey(chinese)) {
            return INITIAL.get(chinese);
        }
        String[] letter = chinese.split(SEPARATOR);
        StringBuilder initial = new StringBuilder();
        for (String it : letter) {
            if (!INITIAL.containsKey(it)) {
                if (!ChineseHelper.isChinese(it.charAt(0))) {
                    initial.append(it.toLowerCase());
                    break;
                }
                if (!INITIAL.containsKey(it)) {
                    INITIAL.put(
                            it, PinyinHelper.getShortPinyin(it)
                    );
                }
            }
            initial.append(INITIAL.get(it));
        }
        INITIAL.put(chinese, initial.toString());
        return initial.toString();
    }

    private PinYinTranslator() {
    }
}
