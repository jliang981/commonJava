package com.step.jliang.zfb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author haoliang
 * @Date 2019-03-28
 **/
public class ZFLB {

    private static final Logger logger = LoggerFactory.getLogger(ZFLB.class);
    private static final int core = Runtime.getRuntime().availableProcessors();
    private static ExecutorService executorService = new ThreadPoolExecutor(core * 2, core * 4, 0, TimeUnit.MINUTES,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());

    // 支付方式的枚举
//    private static List<String> payTypes = Arrays.asList("packet", "balance", "coupon", "daiJinCoupon");

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> availableTypes = new ConcurrentLinkedQueue<>();
        ConsultResultPaymentRemoteSerivceImpl consultResultPaymentRemoteSerivce = new ConsultResultPaymentRemoteSerivceImpl();
        Semaphore semaphore = new Semaphore(0);
        for(PayTypeEnum  payTypeEnum : PayTypeEnum.values()) {
            executorService.execute(() -> {
                try {
                    ConsultResult enabled = consultResultPaymentRemoteSerivce.isEnabled(payTypeEnum.getType());
                    semaphore.release(1);
                    if (enabled.isEnable()) {
                        availableTypes.add(payTypeEnum.getType());
                    } else {
                        logger.error("当前支付方式不可用.type:{}, rst:{}", payTypeEnum.getType(), enabled);
                    }
                } catch (Exception e) {
                    semaphore.release(1);
                    logger.error("获取可用的服务异常.type:{},e:{}", payTypeEnum.getType(), e);
                }
            });
        }
        try {
            // 最多等待三秒钟
            semaphore.tryAcquire(PayTypeEnum.values().length, 3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(availableTypes);
    }

    /**
     * 支付方式枚举
     */
    private  enum PayTypeEnum {
        PACKET("packet"),
        BALANCE("balance"),
        COUPON("coupon"),
        DAIJINCOUPON("daiJinCoupon");

        String type;
        PayTypeEnum (String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
