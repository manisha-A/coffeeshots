package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
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
        int price = calculatePrice();
        boolean hasWhippedCream = ((CheckBox) findViewById(R.id.toppings)).isChecked();
        boolean hasChocolateTopping = ((CheckBox) findViewById(R.id.chocolate_topping)).isChecked();
        String priceMessage = createOrderSummary(price,hasWhippedCream, hasChocolateTopping);
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
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     *
     * @param orderPrice
     * @return
     */
    private String createOrderSummary(int orderPrice, boolean whippedCreamOrdered, boolean chocolateTopping) {
        String orderSummary = "Name: Manisha Awasthi\n" +
                "Add whipped cream? " + whippedCreamOrdered +
                "\nAdd chocolate? " + chocolateTopping +
                "\nQuantity: " + quantity + "\nTotal: $" + orderPrice + "\nThank you!";
        return orderSummary;
    }
}
