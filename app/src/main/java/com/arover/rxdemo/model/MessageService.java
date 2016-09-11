package com.arover.rxdemo.model;

import java.util.Random;

/**
 * @author arover
 *         created at 9/12/16 00:29
 */
public class MessageService {

    public String getMessage() {
        return "message id " + new Random().nextInt(1000);
    }
}
