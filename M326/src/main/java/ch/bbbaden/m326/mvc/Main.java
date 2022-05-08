package ch.bbbaden.m326.mvc;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        final Model model = new Model();
        final Controller controller = new Controller(model);

        final View view = new View();
        model.addObserver(view);

        controller.askNumbers();
    }

}
