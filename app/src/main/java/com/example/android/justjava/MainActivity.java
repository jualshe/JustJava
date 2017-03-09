/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
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

import static com.example.android.justjava.R.id.customerName;

/*** This app displays an order form to order coffee.*/
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /***
     * This method is called when the order button is clicked.
     */


    public void submitOrder(View view) {
        // figure out if the user wants whipped cream
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCream.isChecked();
        //figure out if the user wants chocolate topping
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();
        //find the user's name
        EditText nameField = (EditText) findViewById(customerName);
        String name = nameField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, name + " here is your order!");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
    }

}

    /***
     * Create summary of the order
     * @param name of the customer
     * @param price of the order
     * @param addWhippedCream is true or false the user wants whipped cream topping
     *@param name @return text summary
     */
    public String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage +="\nAdd whipped cream? " + addWhippedCream;
        priceMessage +="\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity " + quantity;
        priceMessage += "\nTotal= $" + price + "\nThank  you!";
        return priceMessage;
    }
    /**
     * Calculates the price of the order.
     * @return total price
     * @param addChocolate is whether or not user wants chocolate topping
     * @param addWhippedCream is whether or not user wants whipped cream topping
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        //price of 1 cup of coffee
        int basePrice = 5;

        //add $1 if the user wants whipped cream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        //add $2 if the user wants chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }

        return quantity * basePrice;
    }


    /*** This method is called when the plus button is clicked.*/
    public void increment (View view) {
        if (quantity ==100 ){
            //show error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            //Exit this method early because there's nothing to do
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
        }
    /*** This method is called when the minus button is clicked.*/
        public void decrement (View view){
            if (quantity ==1 ){
                //show error message as a toast
                Toast.makeText(this, "You cannot have less than 1 coffees", Toast.LENGTH_SHORT).show();
                //Exit this method early because there's nothing to do
                return;
            }
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
}