package org.jcip;

import net.jcip.annotations.*;

/**
 * UnsafeSequence
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        //value++ 可看成 读value、将value加1、将结果写入value 三步操作 两个线程可能同时读取value  最后返回相同的值
        return value++;
    }
}
