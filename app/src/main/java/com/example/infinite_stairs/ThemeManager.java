package com.example.infinite_stairs;

import androidx.annotation.DrawableRes;

public class ThemeManager {
    private static ThemeManager instance;

    @DrawableRes
    private int catImageResource;
    @DrawableRes
    private int blockImageResource;

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
    }

    public void setChristmasTheme() {
        catImageResource = R.drawable.cat_christmas;
        blockImageResource = R.drawable.block_image_christmas;
    }

    @DrawableRes
    public int getCatImageResource() {
        return catImageResource;
    }

    @DrawableRes
    public int getBlockImageResource() {
        return blockImageResource;
    }
}

