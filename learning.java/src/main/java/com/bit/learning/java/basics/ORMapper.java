package com.bit.learning.java.basics;

import java.io.InputStream;
import java.util.List;

/**
 * Created by yuzt on 16-3-1.
 */
public interface ORMapper<T> {
    /**
     * 将输入流中的数据转换为List<T>
     * @param in
     * @return
     */
    public List<T> map(InputStream in);
}
