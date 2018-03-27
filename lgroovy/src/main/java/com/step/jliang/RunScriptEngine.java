package com.step.jliang;

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;

import javax.script.Bindings;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jliang
 */
public class RunScriptEngine {

    public static void evalScript() {
        ScriptEngineManager factory = new ScriptEngineManager();

        //每次生成一个engine实例
        GroovyScriptEngineImpl engine = (GroovyScriptEngineImpl) factory.getEngineByName("groovy");
        System.out.println(engine.toString());
        assert engine != null;
        Bindings binding = engine.createBindings();

        String script = "class Example {\n" +
                "\n" +
                "    def static Map<String, Object> transform(Map<String, Object> realValueMap) {\n" +
                "        Map<String, Object> retMap = new HashMap<>()\n" +
                "        retMap.putAll(realValueMap)\n" +
                "        retMap.put(\"result\", true)\n" +
                "        return retMap\n" +
                "    }\n" +
                "}";
        try {
            Object eval = engine.eval(script);
            System.out.println(eval);
            Map<String, Object> realMap = new HashMap<>();
            realMap.put("sourceParamter", 1223);
            Map<String, Object> result = (Map<String, Object>) engine.invokeMethod(eval, "transform", realMap);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        evalScript();
    }

//    private static final Logger logger = LoggerFactory.getLogger(ThresholdChecker.class);
//
//    private static String itemScript = "";
//    private static String shopScript = "";
//
//    private static GroovyScriptEngineImpl itemEngine = null;
//    private static Object itemScriptClass = null;
//
//
//    private static GroovyScriptEngineImpl shopEngine = null;
//    private static Object shopScriptClass = null;
//
//
//    @SuppressWarnings("unchecked")
//    public static void transform(Map<String, Object> realValueMap, Integer type, Map<String, Object> contextData, Set<String> requireFieldName) {
//        if (realValueMap == null) {
//            return;
//        }
//        refreshEngine();
//
//        GroovyScriptEngineImpl engine;
//        Object scriptClass;
//
//        if (ObjectTypeEnum.ITEM.getCode().equals(type.toString())) {
//            engine = itemEngine;
//            scriptClass = itemScriptClass;
//
//        } else if (ObjectTypeEnum.SHOP.getCode().equals(type.toString())) {
//            engine = shopEngine;
//            scriptClass = shopScriptClass;
//
//        } else {
//            return;
//        }
//
//        try {
//            Map<String, Object> result = (Map<String, Object>) engine.invokeMethod(scriptClass, "transform", realValueMap, contextData, requireFieldName);
//            realValueMap.putAll(result);
//        } catch (Exception e) {
//            logger.error("groovy error", e);
//        }
//    }
//
//    private static void refreshEngine() {
//        try {
//            if (!MetaInfoConfig.item.equals(itemScript)) {
//                String scriptContent = MetaInfoConfig.item;
//                ScriptEngineManager factory = new ScriptEngineManager();
//                itemEngine = (GroovyScriptEngineImpl) factory.getEngineByName("groovy");
//                itemScriptClass = itemEngine.eval(scriptContent);
//                itemScript = scriptContent;
//            }
//            if (!MetaInfoConfig.shop.equals(shopScript)) {
//                String scriptContent = MetaInfoConfig.shop;
//                ScriptEngineManager factory = new ScriptEngineManager();
//                shopEngine = (GroovyScriptEngineImpl) factory.getEngineByName("groovy");
//                shopScriptClass = shopEngine.eval(scriptContent);
//                shopScript = scriptContent;
//            }
//        } catch (Exception e) {
//            logger.error("refresh engine error!", e);
//        }
//    }
}