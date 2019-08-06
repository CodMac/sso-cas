package server.plugins.shiro.matcher;

import java.util.Random;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * md5加密
 * @author mc
 *
 */
public class EncryptUtil {
	
	/**
	 * 获取6位由大小写字母和数字构成的字符串
	 * @return
	 */
	public static String getSalt(){
		StringBuffer salt = new StringBuffer();  
		
	    Random random = new Random();  
	    for (int i = 0; i < 6; i++) {  
	        boolean b = random.nextBoolean();  
	        if (b) { // 字符 
	            int choice = random.nextBoolean() ? 65 : 97; //取得65大写字母还是97小写字母  
	        	salt.append((char) (choice + random.nextInt(26)));
	         } else { // 数字  
	             salt.append(random.nextInt(10));
	         }
	     }
	    return salt.toString();  
	}

	//md5 32位加密
	public static String encrypt32(String pwd, String salt){
		SimpleHash hash = new SimpleHash("MD5", pwd, salt, 2);
		return hash.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt32("mac","mac"));
	}
}
