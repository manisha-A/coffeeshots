package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String userName = String.valueOf(((EditText) findViewById(R.id.name)).getText());
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.toppings)).isChecked();
        boolean hasChocolateTopping = ((CheckBox) findViewById(R.id.chocolate_topping)).isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolateTopping);

        String priceMessage = createOrderSummary(userName, price, hasWhippedCream, hasChocolateTopping);
        displayMessage(priceMessage);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given message  on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }


    /**
     * Calculates the price of the order.
     * @param whippedCream if whipped cream added
     * @param chocolate if chocolate added
     * @return
     */
    private int calculatePrice(boolean whippedCream, boolean chocolate) {
        int basePrice = 5;
        if(whippedCream){
            basePrice = basePrice + 1;
        }
        if(chocolate){
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }

    /**
     * @param orderPrice
     * @return
     */
    private String createOrderSummary(String name, int orderPrice, boolean whippedCreamOrdered, boolean chocolateTopping) {
        String orderSummary = "Name: " +
                name + "\n" +
                "Add whipped cream? " + whippedCreamOrdered +
                "\nAdd chocolate? " + chocolateTopping +
                "\nQuantity: " + quantity + "\nTotal: $" + orderPrice + "\nThank you!";
        return orderSummary;
    }
}
