/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/*** This app displays an order form to order coffee.*/
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*** This method is called when the order button is clicked.*/


    public void submitOrder(View view) {
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCream.isChecked();

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean addChocolate = chocolate.isChecked();

        EditText customerName = (EditText) findViewById(R.id.customerName);
        Editable addCustomerName = customerName.getText();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, hasWhippedCream, addChocolate, addCustomerName);
        displayMessage(priceMessage);
    }
    /***
     * Create summary of the order
     * @param price of the order
     * @param addWhippedCream is true or false the user wants whipped cream topping
     *@param addCustomerName @return text summary
     */
    public String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, Editable addCustomerName){
        String priceMessage = "Name: " + addCustomerName;
        priceMessage +="\nAdd whipped cream? " + addWhippedCream;
        priceMessage +="\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity " + quantity;
        priceMessage += "\nTotal= $" + price + "\nThank  you!";
        return priceMessage;
    }
    /**
     * Calculates the price of the order.
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }


    /*** This method is called when the plus button is clicked.*/
    public void increment (View view) {
            quantity = quantity + 1;
        displayQuantity(quantity);
        }
    /*** This method is called when the minus button is clicked.*/
        public void decrement (View view){
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    /*** This method displays the given quantity value on the screen */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}