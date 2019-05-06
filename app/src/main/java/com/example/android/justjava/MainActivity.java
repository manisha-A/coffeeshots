package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    public static final String YOU_CAN_T_ORDER_MORE_THAN_100_COFFEES = "You can't order more than 100 coffees";
    public static final String YOU_CAN_T_ORDER_LESS_THAN_1_COFFEE = "You can't order less than 1 coffee";
    int quantity = 1;


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
        sendEmail(userName, priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if(quantity == 100){
            showToast(YOU_CAN_T_ORDER_MORE_THAN_100_COFFEES);
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(quantity == 1){
            showToast(YOU_CAN_T_ORDER_LESS_THAN_1_COFFEE);
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Show Toast with message
     * @param toastMessage message to show on toast
     */
    private void showToast(String toastMessage) {
        Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT);
        toast.show();
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

    private void sendEmail(String userName, String body){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for " + userName );
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
