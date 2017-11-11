package com.step.jliang.utils;

/**
 * Created by jliang on 17/5/4.
 */
public class BeanUtil2 {

    /*
    public static Map<String, Object> convertEntity2Map(Object o) {
        return convertEntity2Map(o, null, null);
    }


    private static Map<String, Object> convertEntity2Map(Object o, List<String> chooseFields, List<String> filterFields) {
        Map<String, Object> map = Maps.newHashMap();
        Class cla = o.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {

            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            if (null != chooseFields && !chooseFields.contains(field.getName())) {
                continue;
            }

            if (null != filterFields && filterFields.contains(field.getName())) {
                continue;
            }

            String firstLetter = field.getName().substring(0, 1).toUpperCase(); //获得字段第一个字母大写
            String getMethodName = "get" + firstLetter + field.getName().substring(1); //转换成字段的get方法
            try {
                Method getMethod = cla.getMethod(getMethodName, new Class[]{});
                Object value = getMethod.invoke(o, new Object[]{}); //这个对象字段get方法的值
                if (null == value) {
                    continue;
                }
                map.put(field.getName(), value);
            } catch (SecurityException e) {
            } catch (NoSuchMethodException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
        }
        return map;
    }

    private static Map<?, ?> bean2Map(Object obj) {
        if(obj == null)
            return null;

        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static <T> Map<String, Object> beanToMap2(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }


    public static void main(String[] args) {
        ApplyToolConfigDO configDO = new ApplyToolConfigDO();
        configDO.setId(502L);
        configDO.setName("拼团业务");
        configDO.setActType(1);
        configDO.setPlatform("8");
        configDO.setIntro("拼团业务");
        configDO.setConflictToolIds("503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,522,523,524,525");
        configDO.setToolConf("{\"applyPriceType\":2,\"applyStockType\":0,\"storageRequired\":0,\"storageSpecifier\":1,\"goodsType\":1,\"deliverDuration\":1,\"dealChannel\":100,\"dealPreCondition\":0,\"dealPreRuleConstitute\":1,\"virtualCoinDeduct\":0,\"deductRuleConstitute\":1}");
        configDO.setUpdated(1492488584L);
        configDO.setCreated(1492488584L);
        configDO.setAuditFlowTypes("1,2,3,4,5");

        Map<String, Object> map1 =  convertEntity2Map(configDO);
        Map<?, ?> map4 = bean2Map(configDO);
        Map<String, Object> map6 = beanToMap2(configDO);


        System.out.println(System.currentTimeMillis());
        for(int i=0;i<10000;i++){
            convertEntity2Map(configDO);
        }
        System.out.println(System.currentTimeMillis());

        for(int i=0;i<10000;i++){
            bean2Map(configDO);
        }
        System.out.println(System.currentTimeMillis());

        for(int i=0;i<10000;i++){
            beanToMap2(configDO);
        }
        System.out.println(System.currentTimeMillis());
    }

    */
}
