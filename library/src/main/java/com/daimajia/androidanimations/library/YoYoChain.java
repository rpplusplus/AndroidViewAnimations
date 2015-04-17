package com.daimajia.androidanimations.library;

import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class YoYoChain implements Animator.AnimatorListener {

    private List<YoYo.YoYoString> mAnimations;
    private Set<Animator.AnimatorListener> mListeners;
    private int mCurrentAnimateIndex;

    public YoYoChain() {
        mAnimations = new ArrayList<YoYo.YoYoString>();
        mListeners = new HashSet<Animator.AnimatorListener>();
        mCurrentAnimateIndex = 0;
    }

    public void addYoYoString(YoYo.YoYoString yoyo)
    {
        if (yoyo instanceof YoYo.YoYoCallFuncString)
        {
            ((YoYo.YoYoCallFuncString) yoyo).setFakeListener(this);
        }
        else {
            yoyo.getAnimator().addAnimatorListener(this);
        }

        mAnimations.add(yoyo);
    }

    public void play()
    {
        mAnimations.get(mCurrentAnimateIndex).play();
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (mCurrentAnimateIndex == 0) {
            for (Animator.AnimatorListener m : mListeners) {
                m.onAnimationStart(animation);
            }
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        mCurrentAnimateIndex++;
        if (mCurrentAnimateIndex != mAnimations.size())
        {
            mAnimations.get(mCurrentAnimateIndex).play();
        }
        else
        {
            for (Animator.AnimatorListener m : mListeners)
            {
                m.onAnimationEnd(animation);
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        for (Animator.AnimatorListener m : mListeners)
        {
            m.onAnimationCancel(animation);
        }
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        for (Animator.AnimatorListener m : mListeners)
        {
            m.onAnimationRepeat(animation);
        }
    }
}
