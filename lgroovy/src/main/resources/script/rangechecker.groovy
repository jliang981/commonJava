import com.mogujie.hades.core.common.bean.GroovyCheckResult


def commaIntValRange(confedVal, defaultRange) {

    def splitIntegers = confedVal.toString().split(",");

    for (item in splitIntegers) {

        def rangeVals = defaultRange.toString().split(",");

        def lowVal = rangeVals[0];
        def highVal = rangeVals[1];

        if (!lowVal.isInteger() || !highVal.isInteger()) {
            //result.setIsSuccess(false);
            //result.setErrorMessage("区间值格式范围不正确");
            return GroovyCheckResult.failReturn("区间值格式范围不正确");
        }

        if (!item.toString().isInteger()) {
            //result.setIsSuccess(false);
            //result.setErrorMessage("");
            return GroovyCheckResult.failReturn("配置值需要为合法数字类型");
        }

        int itemInt = item.toString().toInteger();
        boolean isUnitPass = itemInt >= lowVal.toInteger() && itemInt <= highVal.toInteger();
        if (!isUnitPass) {
            return GroovyCheckResult.failReturn("值范围必须在【" + lowVal + "】到【" + highVal + "】之间");
        }
    }
    return GroovyCheckResult.successReturn();


}

/**
 *  值区间验证：值必须在XX范围内
 * @param confedVal
 * @param defaultRange
 * @return
 */
def intValRange(confedVal, defaultRange) { //运营配置的值是否是数字
    //ThresholdCheckResult result = new ThresholdCheckResult(true);

    def rangeVals = defaultRange.toString().split(",");

    def lowVal = rangeVals[0];
    def highVal = rangeVals[1];

    if (!lowVal.isInteger() || !highVal.isInteger()) {
        //result.setIsSuccess(false);
        //result.setErrorMessage("区间值格式范围不正确");
        return GroovyCheckResult.failReturn("区间值格式范围不正确");
    }

    if (!confedVal.toString().isInteger()) {
        //result.setIsSuccess(false);
        //result.setErrorMessage("");
        return GroovyCheckResult.failReturn("配置值需要为合法数字类型");
    }

    int confedInt = confedVal.toString().toInteger();
    boolean isPass = confedInt >= lowVal.toInteger() && confedInt <= highVal.toInteger();

    if (!isPass) {
        //result.setIsSuccess(false);
        //result.setErrorMessage();
        return GroovyCheckResult.failReturn("值范围必须在【" + lowVal + "】到【" + highVal + "】之间");
    }
    return GroovyCheckResult.successReturn();
}

/**
 *  值区间验证：值必须在XX范围内
 * @param confedVal
 * @param defaultRange
 * @return
 */
def floatValRange(confedVal, defaultRange) { //运营配置的值是否是数字
    //Result result = new Result(true);

    def rangeVals = defaultRange.toString().split(",");

    def lowVal = rangeVals[0];
    def highVal = rangeVals[1];

    if (!lowVal.isFloat() || !highVal.isFloat()) {
        //result.setIsSuccess(false);
        //result.setErrorMessage("区间值格式范围不正确");
        return GroovyCheckResult.failReturn("区间值格式范围不正确");
    }

    if (!confedVal.toString().isFloat()) {
        //result.setIsSuccess(false);
        //result.setErrorMessage("配置值需要为合法数字类型");
        return GroovyCheckResult.failReturn("配置值需要为合法数字类型");
    }

    float confedFloat = confedVal.toString().toFloat();
    boolean isPass = (confedFloat >= lowVal.toFloat() && confedFloat <= highVal.toFloat());

    if (!isPass) {
        //result.setIsSuccess(false);
        //result.setErrorMessage("值范围必须在【" + lowVal + "】到【" + highVal + "】之间");
        return GroovyCheckResult.failReturn("值范围必须在【" + lowVal + "】到【" + highVal + "】之间");
    }
    return GroovyCheckResult.successReturn();
}