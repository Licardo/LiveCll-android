package cll.pf.com.livecll.router;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.DexFile;

public class AnnotationControl {

    public static List<Class<?>> getClasses(Context mContext, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try {
            String packageCodePath = mContext.getPackageCodePath();
            DexFile df = new DexFile(packageCodePath);
            String regExp = "^" + packageName + ".\\w+$";
            for (Enumeration iter = df.entries(); iter.hasMoreElements(); ) {
                String className = (String) iter.nextElement();
                if (className.matches(regExp)) {
                    Class<?> c = Class.forName(className);
                    classes.add(c);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static void validAnnotation(List<Class<?>> clsList){
        if (clsList != null && clsList.size() > 0) {
            for (Class<?> cls : clsList) {
                //获取类中的所有的方法

                if (cls.isAnnotationPresent(CllRouter.class)) {
                    CllRouter router = cls.getAnnotation(CllRouter.class);
                    RouterPath.getInstance().register(router.value(), cls);
                }
            }
        }
    }
}
