package cll.pf.com.livecll.utils;

import android.support.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Leo
 * on 2017/8/4.
 */

public class PhoneFormatCheckUtils {

    /**
     * 验证电话号码是否合法（暂时只验证手机号码）
     */
    public static boolean isPhoneLegal(String phone) {
        if (phone == null || phone.length() == 0) {
            return false;
        }
        return checkCellphone(phone) || checkTelephone(phone);
    }

    /**
     * 验证手机号码<p>
     * 13(老)号段：130、131、132、133、134、135、136、137、138、139<p>
     * 14(新)号段：145、147<p>
     * 15(新)号段：150、151、152、153、154、155、156、157、158、159<p>
     * 16(新)号段：166<p>
     * 17(新)号段：170、171、173、175、176、177、178<p>
     * 18(3G)号段：180、181、182、183、184、185、186、187、188、189<p>
     * 19(新)号段：198、199<p>
     *
     * @param cellphone 待验证手机号码
     * @return 是否为合法手机号码
     */
    public static boolean checkCellphone(@NonNull String cellphone) {
        String regex = "^((13[0-9])|(14[5|7])|(15[0-9])|(166)|(17[0-8])|(18[0-9])|(19[8|9]))\\d{8}$";
        return check(cellphone, regex);
    }

    /**
     * 验证固话号码
     *
     * @param telephone 待验证固话号码
     * @return 是否为合法固话号码
     */
    public static boolean checkTelephone(@NonNull String telephone) {
        String regex = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
        return check(telephone, regex);
    }

    private static boolean check(String phone, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
