package ch.bbbaden.m326.mvc;

import javax.swing.JOptionPane;


public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void askNumbers() {
        int answerInt;
        String answer;
        do {
            answer = JOptionPane.showInputDialog(null,
                    "Please enter a number.",
                    "Enter a number",
                    JOptionPane.QUESTION_MESSAGE
            );

            try {
                answerInt = Integer.parseInt(answer);
                model.addNumber(answerInt);
            } catch (NumberFormatException ignored) {
            }

        } while (answer != null);
    }

}
