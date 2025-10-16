package org.example;

import Controller.EstufaController;
import View.EstufaView;

public class Main {
    public static void main(String[] args) {
        EstufaView view = new EstufaView();
        EstufaController controller = new EstufaController(view);
        controller.iniciar();
    }
}