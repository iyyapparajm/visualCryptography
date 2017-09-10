package gui;

import java.io.*;
import java.awt.image.*;

import javax.imageio.ImageIO;
public class Imageconcat 
{
	public Imageconcat()
	{
		 try
		 {
			 ImageEncryptAndDecrypt ed=new ImageEncryptAndDecrypt();
			 for(int i=0;i<8;i++)
				{
					
			    String encryptedfile = "encrypt1/img" + i + ".jpg";  
			    String dencryptedFile = "decrypt/img" + i + ".jpg";  
			    String password="1234";
			    System.out.println("Starting DEncryption...");  
			    ed.encrypt_decrypt(encryptedfile, dencryptedFile,password);
			    System.out.println("DEncryption completed...");  
			    
				}
				for(int j=8;j<16;j++)
				{
					
				
			    String encryptedfile = "encrypt2/img" + j + ".jpg";  
			    String dencryptedFile = "decrypt/img" + j + ".jpg";  
			    String password="1234";
			   
			      
			    System.out.println("Starting DEncryption...");  
			    ed.encrypt_decrypt(encryptedfile, dencryptedFile,password); 
			    System.out.println("DEncryption completed...");  
			    
				}
			 
			 
	        int rows = 4;   //we assume the no. of rows and cols are known and each chunk has equal width and height
	        int cols = 4;
	        int chunks = rows * cols;
	       
	        int chunkWidth, chunkHeight;
	        int type;
	        //fetching image files
	        File[] imgFiles = new File[chunks];
	        
	       
	        for (int i = 0; i < chunks; i++) {
	            imgFiles[i] = new File("decrypt/img" + i + ".jpg");
	        }

	       //creating a bufferd image array from image files
	        BufferedImage[] buffImages = new BufferedImage[chunks];
	        for (int i = 0; i < chunks; i++) {
	            buffImages[i] = ImageIO.read(imgFiles[i]);
	        }
	        type = buffImages[0].getType();
	        chunkWidth = buffImages[0].getWidth();
	        chunkHeight = buffImages[0].getHeight();

	        //Initializing the final image
	        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);

	        int num = 0;
	        for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);
	                num++;
	            }
	        }
	        System.out.println("Image concatenated.....");
	        ImageIO.write(finalImg, "jpeg", new File("finalImg.jpg"));
		 }
		 catch(Exception E)
		 {
			 System.out.println(E);
		 }
	}
	
public static void main(String args[])
{

	
	
	Imageconcat ic=new Imageconcat();
 	
}
}
