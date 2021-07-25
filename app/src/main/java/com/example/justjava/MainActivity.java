/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream= whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        EditText nameOfTheUser = (EditText) findViewById(R.id.name_field);
        String name = nameOfTheUser.getText().toString();
        int price=calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage=createOrderSummary(price,hasWhippedCream,hasChocolate,name);
       // displayMessage(priceMessage);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order For "+name);
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }



    }

    //This method is called when plus button is clicked
    public void increment(View view) {
        quantity++;
        if(quantity>=100)
        {
            quantity=100;
            Toast.makeText(this,"You cannot have more than 100 cups of coffes",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }
    //This method is called when minus button is clicked
    public void decrement(View view) {
        quantity--;
        if(quantity<1)
        {
            quantity=1;
            Toast.makeText(this,"You cannot have less than 1cups of coffes",Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private int calculatePrice(boolean hasWhippedCream,boolean hasChocolate)
    {  int value=5;
    if(hasWhippedCream==true)
    {
        value+=1;
    }
     if(hasChocolate==true)
    {
        value+=2;
    }

        int price=quantity*value;
        return price;
    }
    /**
     * Create summary of the order.
     *
     * @param hasWhippedCream is whether or not the user wants whipped cream topping
     * @param hasChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price,boolean hasWhippedCream,boolean hasChocolate,String name)
    {
        String priceMessage=getString(R.string.order_name, name);
        priceMessage +="\n Add Whipped Cream? "+hasWhippedCream;
        priceMessage +="\n Add Chocolate? "+hasChocolate;
        priceMessage +="\n Quantity :" +quantity;
         priceMessage +="\nPrice : Rupees "+(price);
        priceMessage +="\n " +getString(R.string.thank_you);
   return priceMessage;
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
  //  private void displayPrice(int number) {
    //    TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
     //   priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
   // }
    /**
     * This method displays the given text on the screen.
     */
   // private void displayMessage(String message)
  //  {
   //     TextView orderSummaryTextView =(TextView) findViewById(R.id.order_summary_text_view);
  //      orderSummaryTextView.setText(message);
 //   }
}

