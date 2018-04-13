package com.daily.util;

import com.google.zxing.LuminanceSource;

/**
 * Created by json on 2018/4/12.
 * Describe: 二维码读取
 */
public class ErWeiMaReadUtil extends LuminanceSource {

    // 私有化构造函数
    protected ErWeiMaReadUtil(int width, int height) {
        super(width, height);
    }

    @Override
    public byte[] getRow(int i, byte[] bytes) {
        return new byte[0];
    }

    @Override
    public byte[] getMatrix() {
        return new byte[0];
    }
}
