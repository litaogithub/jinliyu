package com.xingyunyicai.qihoo.video.main.tuijian.anim;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.qihoo.video.main.tuijian
 * 文件名：   RotationAnimator
 * 创建者：   DoDo
 * 创建时间： 2017/9/28 15:08
 * 描述：     TODO
 */

public class RotationAnimator extends BaseViewAnimator {

    @Override
    protected void prepare(View target) {
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "rotation", 0, 360)
        );
    }
}
