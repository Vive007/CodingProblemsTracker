package com.vivek.codingapp.ui;

public class Button {
    private final String label;
    private final Runnable onClickAction;

    public Button(String label, Runnable onClickAction) {
        this.label = label;
        this.onClickAction = onClickAction;
    }

    public void click() {
        System.out.println(label + " button clicked!");
        onClickAction.run();
    }
}
