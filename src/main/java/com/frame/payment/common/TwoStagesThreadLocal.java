package com.frame.payment.common;

import java.util.Set;

/**
 * two stages ThreadLocal
 *
 * @author anylots
 * @version $Id: TwoStagesThreadLocal.java, v 0.1 2020年10月17日 17:26 anylots Exp $
 */
public class TwoStagesThreadLocal {

    /**
     * two stages context
     */
    private static ThreadLocal<Set<TwoStageCompleter>> TWO_STAGES_CONTEXT = new ThreadLocal<>();

    /**
     * * Sets the current thread's copy of this thread-local variable
     *
     * @param stageCompletes
     */
    public static void set(Set<TwoStageCompleter> stageCompletes) {
        TWO_STAGES_CONTEXT.set(stageCompletes);
    }

    public static Set<TwoStageCompleter> get() {
        return TWO_STAGES_CONTEXT.get();
    }

    public static void remove() {
        TWO_STAGES_CONTEXT.remove();
    }

}