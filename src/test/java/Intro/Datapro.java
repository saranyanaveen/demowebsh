package Intro;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Datapro {
	@Test
	@DataProvider(name = "userData")
	public Object[][] userdata() {
		return new Object[][] {
				{ "saraswathimariviji1997@gmail.com", "Saranya@31", "Fastrack Limitless Fs2 Pro Smartwatch DeepBlack" },
		};
	}
@Test
@DataProvider(name = "user2")
				public Object[][] user2data(){
					
				return new Object[][]{
					
				
					{ "juliuspreethi@gmail.com", "loveranred", "Silver Bangle for Women" },

		};
	}
    @Test
	@DataProvider(name = "userWishlistData")
	public Object[][] userWishlistData() {
		return new Object[][] {
			{ "suresh14octo@gmail.com", "Pavithra@2321", "Comfortable Sandals for Women in black" },
		

		};
	}
}
