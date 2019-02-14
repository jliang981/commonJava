package com.step.jliang.ref;

import com.step.jliang.beanDO.User;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * @author jliang
 */
public class TIntrospector {

    public static void setPropertyByIntrospector(User user, String userName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(userName)) {
                    Method methodSetUserName = propDesc.getWriteMethod();
                    methodSetUserName.invoke(user, "alan");
                    System.out.println("set userName:" + user.getUserName());
                    break;
                }
            }
        }
    }

    public static void getPropertyByIntrospector(User User, String userName) throws Exception {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(userName)) {
                    Method methodGetUserName = propDesc.getReadMethod();
                    Object objUserName = methodGetUserName.invoke(User);
                    System.out.println("get userName:" + objUserName.toString());
                    break;
                }
            }
        }
    }

    public static void setProperty(User userInfo, String userName) throws Exception {
        PropertyDescriptor propDesc = new PropertyDescriptor(userName, User.class);
        Method methodSetUserName = propDesc.getWriteMethod();
        methodSetUserName.invoke(userInfo, "wong");
        System.out.println("set userName:" + userInfo.getUserName());
    }

    public static void getProperty(User userInfo, String userName) throws Exception {
        PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, User.class);
        Method methodGetUserName = proDescriptor.getReadMethod();
        Object objUserName = methodGetUserName.invoke(userInfo);
        System.out.println("get userName:" + objUserName.toString());
    }

    public static void main(String[] args) throws Exception {
        User user = new User();
        setProperty(user, "userName");
        System.out.println(user);
        getProperty(user, "userName");
        setPropertyByIntrospector(user, "userName");
        getPropertyByIntrospector(user, "userName");
    }
}
