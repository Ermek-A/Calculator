package com.example.javaprojecttest;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Controller class for a basic JavaFX calculator.
 * Handles number input, operations, and mathematical functions.
 */
public class Controller {
    @FXML
    private TextField display; // Text field for displaying numbers and results

    private String operator = ""; // Stores the selected operator
    private double num1 = 0; // Stores the first number in the calculation
    private boolean start = true; // Indicates if the user is starting a new input

    /**
     * Handles number button clicks.
     * Appends the clicked number to the display field.
     */
    @FXML
    private void onNumberClick(javafx.event.ActionEvent event) {
        if (start) {
            display.setText("");
            start = false;
        }
        String value = ((Button) event.getSource()).getText();
        display.setText(display.getText() + value);
    }

    /**
     * Handles decimal point button click.
     * Ensures only one decimal point is added per number.
     */
    @FXML
    private void onDecimalClick() {
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
            start = false;
        }
    }

    /**
     * Handles operator button clicks (+, -, ×, /, ^).
     * Stores the first number and the operator.
     * If "=" is clicked, calculates the result.
     */
    @FXML
    private void onOperatorClick(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty()) return; // Prevents overwriting an existing operation
            operator = value;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        } else {
            if (operator.isEmpty()) return;
            double num2 = Double.parseDouble(display.getText());
            double result = switch (operator) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "×" -> num1 * num2;
                case "/" -> num2 != 0 ? num1 / num2 : 0; // Prevent division by zero
                case "^" -> Math.pow(num1, num2);
                default -> 0;
            };
            display.setText(String.valueOf(result));
            operator = "";
            start = true;
        }
    }

    /**
     * Handles function buttons (√, |x|, ≈).
     * Applies the selected function to the current number.
     */
    @FXML
    private void onFunctionClick(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        double num = Double.parseDouble(display.getText());
        double result = switch (value) {
            case "√" -> Math.sqrt(num);
            case "|x|" -> Math.abs(num);
            case "≈" -> (double) Math.round(num);
            default -> num;
        };
        display.setText(String.valueOf(result));
        start = true;
    }

    /**
     * Clears the calculator display and resets values.
     */
    @FXML
    private void onClearClick() {
        display.setText("");
        operator = "";
        num1 = 0;
        start = true;
    }
}
