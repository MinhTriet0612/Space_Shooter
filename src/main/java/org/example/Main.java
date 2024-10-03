package org.example;

import org.example.app.App;
import org.example.app.CasualPlayScene;

public class Main {
    public static void main(String[] args) {
        try {
            App app = new App(new CasualPlayScene());
            app.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}