package com.alibaba.java8;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * @author sier.pys 9/15/18
 */
public interface ActionEvent extends ActionListener {
    String actionCommend();

    static void main(String[] args) {
        JButton jButton = new JButton();
        jButton.addActionListener(java.awt.event.ActionEvent::getActionCommand);
    }
}
