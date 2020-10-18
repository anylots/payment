package com.frame.balance.service;

import com.frame.tcctransaction.common.CommonInfo;

/**
 * @author anylots
 * @version $Id: TwoStageCommonService.java, v 0.1 2020年10月17日 14:08 anylots Exp $
 */
public abstract class TwoStageCommonService {

    /**
     * 现金扣减两阶段方法
     *
     * @param commonInfo
     */
    public void process(CommonInfo commonInfo) {

        switch (commonInfo.getStage()) {
            case "prepare":
                prepare(commonInfo);
                break;

            case "commit":
                commit(commonInfo);
                break;

            case "cancel":
                cancel(commonInfo);
                break;

            default:
                break;
        }

    }

    public abstract void prepare(CommonInfo commonInfo);

    public abstract void commit(CommonInfo commonInfo);

    public abstract void cancel(CommonInfo commonInfo);
}