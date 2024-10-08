package fileutility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class Csvwriter {

	public static void main(String[] args) throws Throwable {
	
		CSVWriter writer = new CSVWriter(new FileWriter("src\\test\\resources\\csv data\\test.csv"));
		String[] heading= {"userName","password"};
		String[] data1= {"saran@gmail.com","saran@31"};
		
		String[] data2= {"abc@gmail.com","abc@123"};
		String[] data3= {"saranaveen@gmail.com","sara@123"};
		String[] data4= {"saranaveen1997@gmail.com","Saranya@1997"};
		List<String[]> list=new ArrayList<>();
		list.add(heading);
		list.add(data1);
		list.add(data2);
		list.add(data3);
		list.add(data4);
		writer.writeAll(list);
		writer.flush();
		
		
	}

}
