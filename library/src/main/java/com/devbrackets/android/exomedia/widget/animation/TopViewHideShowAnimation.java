package com.devbrackets.android.exomedia.widget.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

public class TopViewHideShowAnimation extends AnimationSet {

    private View animationView;
    private boolean toVisible;

    public TopViewHideShowAnimation(View view, boolean toVisible, long duration) {
        super(false);
        this.toVisible = toVisible;
        this.animationView = view;

        //Creates the Alpha animation for the transition
        float startAlpha = toVisible ? 0 : 1;
        float endAlpha = toVisible ? 1 : 0;

        AlphaAnimation alphaAnimation = new AlphaAnimation(startAlpha, endAlpha);
        alphaAnimation.setDuration(duration);


        //Creates the Translate animation for the transition
        int startY = toVisible ? -view.getHeight() : 0;
        int endY = toVisible ? 0 : -view.getHeight();
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, startY, endY);
        translateAnimation.setDuration(duration);


        //Adds the animations to the set
        addAnimation(alphaAnimation);
        addAnimation(translateAnimation);

        setAnimationListener(new Listener());
    }

    private class Listener implements AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            animationView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            animationView.setVisibility(toVisible ? View.VISIBLE : View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            //Purposefully left blank
        }
    }
}