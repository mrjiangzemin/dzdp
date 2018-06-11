package webcollector;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import webcollector.ImageStore; 
import com.imooc.bean.*;
import com.imooc.dao.MessageDao;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
public class WebCrawl1 {
	public static void main(String[] args) {
		String URLString = "http://nc.meituan.com/meishi/";
		System.out.println("URL:" + URLString);
		HashSet<Message> allShops = searchShops(URLString);
		Iterator<Message> iterator = allShops.iterator();  
        while(iterator.hasNext())  
        {  
            Message temp = iterator.next();
            MessageDao messageDao=new MessageDao();
            messageDao.insert(temp);
            System.out.println("URL:" + temp.getJpgURL()
            	+ "\tName:" + temp.getName()
            	+ "\tAvgScore:" + temp.getAvgScore()
            	+ "\tAllCommentNum:" + temp.getAllCommentNum() 
            	+ "\tAddress:" + temp.getAddress()
            	+ "\tAvgPrice:" + temp.getAvgPrice()
            );
            
            try {
				ImageStore.downloadImage(temp.getJpgURL(),temp.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }  
	}
	
	
	public static HashSet<Message> searchShops(String currentURL){
		HashSet<Message> st = new HashSet<Message>();
		
		try {
			//打开URL流从网页读取信息
			URL url = new URL(currentURL);
			Scanner input = new Scanner(url.openStream());
			
			int temp = 0;	
			int end = 0;
			//检索信息并封装到一个临时对象shop中，再装入对象容器Allshops中
			while(input.hasNext()) {
				String line = input.nextLine();
				
				temp = line.indexOf("\"frontImg\":\"",temp); 
				while(temp > 0) {
					Message curShop = new Message();
					
					//1.jpg
					end = line.indexOf(".jpg\",", temp);
					curShop.setJpgURL(line.substring(temp+12, end+4));
					
					//2.name
					temp = line.indexOf("title\":\"",end);
					end = line.indexOf("\",", temp);
					curShop.setName(line.substring(temp+8, end));
					
					//3.avgScore
					temp = line.indexOf("avgScore\":",end);
					end = line.indexOf(",",temp);
					curShop.setAvgScore(line.substring(temp+10, end));
					
					//4.allCommentNum
					temp = line.indexOf("allCommentNum\":",end); 	
					end = line.indexOf(",",temp);
					curShop.setAllCommentNum(line.substring(temp+15, end));
					
					//5.address
					temp = line.indexOf("\"address\":\"",end); 	
					end = line.indexOf("\",",temp);
					curShop.setAddress(line.substring(temp+11, end));
					
					//6.avgPrice
					temp = line.indexOf("\"avgPrice\":",end); 	
					end = line.indexOf(",\"",temp);
					curShop.setAvgPrice(line.substring(temp+11, end));
									
					//装入容器
					st.add(curShop);
					//检索下一个的起始位置
					temp = line.indexOf("\"frontImg\":\"",end);					
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		//返回对象
		return st;
	}
	
	
}


