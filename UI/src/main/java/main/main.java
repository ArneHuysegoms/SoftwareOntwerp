package main;

import canvas.InteractrCanvas;

public class main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new InteractrCanvas("My Canvas Window").show();
        });
    }
}
