package sampleutil;

import org.testng.annotations.DataProvider;

public class Dataprovider {
    
    @DataProvider(name = "userAdddata")
    public  Object[][] userdata() {
        return new Object[][] {
            {"saranaveen1997@gmail.com", "Saranya@1997", "jelwery","Black & White Diamond Heart"},//user 1 adding to cart
           // {"pavithrapavi123@gmail.com", "pavithra@123","Shoes"," Blue and green Sneaker"},//user2 adding to wish list
            {"lavan123@gmail.com", "lavan@123", "giftcards", "$25 Virtual Gift Card"}, // User 3
           // {"sugan123@gmail.com", "sugan@123", "HealthBooks", "Health Book"}, // User 4
            {"ahan123@gmail.com", "ahan@123", "Apparel", "Men's Wrinkle Free Long Sleeve"} // User 5
        	
        };
    }
    
    @DataProvider(name = "userWishlistData")
    public Object[][] userWishlistData() {
        return new Object[][] {
            {"pavithrapavi123@gmail.com", "pavithra@123", "Shoes", " Blue and green Sneaker"}, // User 2
            {"sugan123@gmail.com", "sugan@123", "HealthBooks", "Health Book"} // User 4 (also for wishlist)
        };
    }
}


