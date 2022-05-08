package ch.bbbaden.m326.mvc;

import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) o;
        System.out.println("New Numbers:");
        System.out.printf("Sum: %d Count: %d Average: %f Min: %d Max: %d%n",
                model.getSumNumbers(), model.getCountNumbers(), model.getAverageNumbers(), model.getMinNumber(), model.getMaxNumber());
    }

}
