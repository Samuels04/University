// Fig. 10.12: Invoice.java
// Invoice class implements Payable.

public class Invoice implements Payable {
   private String partNumber;
   private String partDescription;
   private int quantity;
   private double pricePerItem;

   public Invoice(String part, String description, int count, double price) {
      partNumber = part;
      partDescription = description;
      setQuantity(count); // validate and store quantity
      setPricePerItem(price); // validate and store price per item
   }

   public void setPartNumber(String part) {
      partNumber = part; // should validate
   }

   public String getPartNumber() {
      return partNumber;
   }

   public void setPartDescription(String description) {
      partDescription = description; // should validate
   }

   public String getPartDescription() {
      return partDescription;
   }

   public void setQuantity(int count) {
      quantity = (count < 0) ? 0 : count; // quantity cannot be negative
   }

   public int getQuantity() {
      return quantity;
   }

   public void setPricePerItem(double price) {
      pricePerItem = (price < 0.0) ? 0.0 : price; // validate price
   }

   public double getPricePerItem() {
      return pricePerItem;
   }

   @Override
   public String toString() {
      return String.format("%s: \n%s: %s (%s) \n%s: %d \n%s: $%,.2f","invoice", "part number", getPartNumber(), getPartDescription(),"quantity", getQuantity(), "price per item", getPricePerItem());
   }

   @Override
   public double getPaymentAmount() {
      return getQuantity() * getPricePerItem(); // calculate total cost
   }
}