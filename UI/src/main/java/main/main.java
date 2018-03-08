package main;

import canvaswindow.InteractrCanvas;

public class main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new InteractrCanvas("My Canvas Window").show();
        });
    }
}
