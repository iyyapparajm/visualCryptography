package gui;
 import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.SecureRandom;

import javax.imageio.ImageIO;

public class ImageEncryptAndDecrypt {
	
	
	public void encrypt_split()
	{
		ImageEncryptAndDecrypt ed=new ImageEncryptAndDecrypt();
		
		for(int i=0;i<8;i++)
		{
			
	    String fileToEncrypt = "imgs/img" + i + ".jpg";  
	    String encryptedFile = "encrypt1/img" + i + ".jpg";  
	    String password="1234";
	    System.out.println("Starting Encryption...");  
	    ed.encrypt_decrypt(fileToEncrypt, encryptedFile,password);
	    System.out.println("Encryption completed...");  
	    
		}
		for(int j=8;j<16;j++)
		{
			
		
	    String fileToEncrypt = "imgs/img" + j + ".jpg";  
	    String encryptedFile = "encrypt2/img" + j + ".jpg";  
	    String password="1234";
	   
	      
	    System.out.println("Starting Encryption...");  
	    ed.encrypt_decrypt(fileToEncrypt, encryptedFile,password); 
	    System.out.println("Encryption completed...");  
	    
		}
	}
	public void encrypt_decrypt(String source,String destination,String pass)
	{
		
		
		String sPath= source,dPath=destination,password=pass;
	    StringBuilder sb=new StringBuilder();
	    char st; 
	    int value;
	    try{
	        for (int i = 0; i < password.length(); i++) {
	             st=password.charAt(i);
	             value=(int)st;
	             sb.append(value);
	        }
	    SecureRandom sr= SecureRandom.getInstance("SHA1PRNG");
	    sr.setSeed(sb.toString().getBytes());
	    BufferedImage FSImg=ImageIO.read(new File(sPath));  
	        for (int w = 0; w < FSImg.getWidth(); w++) {
	            for (int h = 0; h < FSImg.getHeight(); h++) {
	                Color color=new Color(FSImg.getRGB(w, h));
	                Color newColor=new Color(color.getRed()^sr.nextInt(255), color.getGreen()^sr.nextInt(255), color.getBlue()^sr.nextInt(255));
	                FSImg.setRGB(w, h, newColor.getRGB());
	            }
	        }
	    System.out.println("Process Completed!!..");
	    ImageIO.write(FSImg, "bmp", new File(dPath));
	    System.out.println("Wrote to "+dPath);
	    }catch(Exception e){
	    e.printStackTrace();}
	    }
	
	
    public static void main(String s[]){
    	
    	
    	
    	
        
    	
    	//Imageconcat im=new Imageconcat();
    }
	}
