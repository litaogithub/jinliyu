package com.xingyunyicai.core.util.timer;

import java.util.TimerTask;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.util.timer
 * 文件名：   BaseTimerTask
 * 创建者：   DoDo
 * 创建时间： 2017/9/3 3:57
 * 描述：     定时器任务 基类
 */

public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }

    }
}
