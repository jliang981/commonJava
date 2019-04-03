package com.step.jliang.zfb;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 利息计算问题
 */
public class LiXi {

    public static void main(String[] args) {
//        benxi();
        benjin();
//        jingdu();
    }

    public static void benxi() {
        double invest = 1000000;   //贷款本金 一百万
        double yearRate = 0.049;   //年利率
        double monthRate = yearRate / 12;   //月利率

        System.out.println(monthRate);

//      int year = 15;     //还款年数
        int month = 360;  //还款月数

        System.out.println("本金-->" + invest + "   年利率--->" + yearRate * 100 + "%" + "  期限--->" + month + "个月");
        System.out.println("--------------------------------------------");

        // 每月本息金额  = (本金×月利率×(1＋月利率)＾还款月数)÷ ((1＋月利率)＾还款月数-1))
        double monthIncome = (invest * monthRate * Math.pow(1 + monthRate, month)) / (Math.pow(1 + monthRate, month) - 1);
        System.out.println("每月本息金额 : " + monthIncome);
        System.out.println("---------------------------------------------------");

        // 每月本金 = 本金×月利率×(1+月利率)^(还款月序号-1)÷((1+月利率)^还款月数-1))
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        double monthCapital = 0;
        for (int i = 1; i <= month; i++) {
            monthCapital = (invest * monthRate * (Math.pow((1 + monthRate), i - 1))) / (Math.pow(1 + monthRate, month) - 1);
            System.out.println("第" + i + "月本金： " + decimalFormat.format(monthCapital));
        }
        System.out.println("---------------------------------------------------");


        // 每月利息  = 剩余本金 x 贷款月利率
        double monthInterest = 0;
        double capital = invest;
        double tmpCapital = 0;
        double totalInterest = 0;
        for (int i = 1; i < month + 1; i++) {
            capital = capital - tmpCapital;
            monthInterest = capital * monthRate;
            tmpCapital = (invest * monthRate * (Math.pow((1 + monthRate), i - 1))) / (Math.pow(1 + monthRate, month) - 1);
            System.out.println("第" + i + "月利息： " + monthInterest);
            totalInterest = totalInterest + monthInterest;
        }

        System.out.println("-------------------------------------------------");
        System.out.println("利息：--->" + totalInterest);

        System.out.println("-------------------------------------------------");
        System.out.println("年利率：--->" + totalInterest / month * 12 / invest * 100 + "%");
    }

    public static void benjin() {
        double invest = 1000000;   //贷款本金 一百万
        double yearRate = 0.049;   //年利率
        double monthRate = yearRate / 12;   //月利率
        int month = 360;  //还款月数

        double monthBenjin = invest / month;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        System.out.println("等额本金，每月本金数： " + decimalFormat.format(monthBenjin));

        for (int i = 1; i <= month; i++) {
            double restInvest = invest - (i - 1) * monthBenjin;
            // decimalFormat.format(monthBenjin + restInvest * monthRate)

            BigDecimal restDecimal = new BigDecimal(restInvest * monthRate).setScale(2, RoundingMode.HALF_UP);

            BigDecimal bigDecimal = new BigDecimal(monthBenjin).setScale(2, RoundingMode.HALF_UP);

            System.out.println("第 " + i + " 个月，应还款金额：" + restDecimal.add(bigDecimal));
        }

    }

    public static void jingdu() {
//        DecimalFormat df = new DecimalFormat("#.0");
//        System.out.println(df.format(0.999999));
//
//        DecimalFormat df2 = new DecimalFormat("#.00");
//        System.out.println(df2.format(0.999999));

        double result = 1.8912;
        System.out.println("初始值：" + result);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_EVEN);
        System.out.println("HALF_EVEN：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        System.out.println("HALF_UP：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);
        System.out.println("HALF_DOWN：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.FLOOR);
        System.out.println("FLOOR：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        System.out.println("CEILING：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.UP);
        System.out.println("UP：" + decimalFormat.format(result));
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        System.out.println("DOWN：" + decimalFormat.format(result));


        // 各种 pattern
        System.out.println("pattern 0: " + new DecimalFormat("0").format(result));
        //取一位整数和两位小数
        System.out.println("pattern 0.00: " + new DecimalFormat("0.00").format(result));
        //取两位整数和三位小数，整数不足部分以0填补
        System.out.println("pattern 00.000: " + new DecimalFormat("00.000").format(result));
        //取所有整数部分
        System.out.println("pattern #: " + new DecimalFormat("#").format(result));
        //以百分比方式计数，并取两位小数
        System.out.println("pattern #.##%: " + new DecimalFormat("#.##%").format(result));
        //显示为科学计数法，并取五位小数
        System.out.println("pattern #.#####E0: " + new DecimalFormat("#.#####E0").format(result));
        //显示为两位整数的科学计数法，并取四位小数
        System.out.println("pattern 00.####E0: " + new DecimalFormat("00.####E0").format(result));
        //每三位以逗号进行分隔。
        System.out.println("pattern ,### : " + new DecimalFormat(",###").format(result));
        //将格式嵌入文本
        System.out.println("pattern 所传入的格式化参数是：###大小。" + new DecimalFormat("所传入的格式化参数是：###大小。").format(result));

    }

}
