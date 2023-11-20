package com.example.infinite_stairs;

import androidx.annotation.DrawableRes;

public class ThemeManager {
    private static ThemeManager instance;

    @DrawableRes
    private int catImageResource;
    @DrawableRes
    private int blockImageResource;
    @DrawableRes
    private int failImageResource;

    private ThemeManager() {
        setBasicTheme();        // 기본적으로 베이직 테마로 설정
    }

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public void setBasicTheme() {
        catImageResource = R.drawable.cat;
        blockImageResource = R.drawable.block_image;
        failImageResource = R.drawable.fail_cat;
    }

    public void setChristmasTheme() {
        catImageResource = R.drawable.cat_christmas;
        blockImageResource = R.drawable.block_image_christmas;
        failImageResource = R.drawable.fail_cat_christmas;
    }

    @DrawableRes
    public int getCatImageResource() {
        return catImageResource;
    }

    @DrawableRes
    public int getBlockImageResource() {
        return blockImageResource;
    }

    @DrawableRes
    public int getfailImageResource() {
        return failImageResource;
    }
}

