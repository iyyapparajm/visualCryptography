package gui;

import java.io.File;
import java.util.*;
import java.awt.image.*;
import javax.imageio.*;
import java.nio.*;
public class Imagemerge {
	       public static void main(String args[])
	       {
	        try
	        {
	        	int rows = 4;   //we assume the no. of rows and cols are known and each chunk has equal width and height  
	            int cols = 4;  
	            int chunks = rows * cols;  
	      
	            int chunkWidth, chunkHeight;  
	            int type;  
	        
	        //fetching image files  
	        File[] imgFiles = new File[chunks];
	        
	        for (int i = 0; i < chunks; i++) {  
	            imgFiles[i] = new File("img" + i + ".png");  
	        }  
	
	       //creating a bufferd image array from image files  
	        BufferedImage[] buffImages = new BufferedImage[chunks];  
	        for (int j = 0; j < chunks; j++) {  
	            buffImages[j] = ImageIO.read(imgFiles[j]);  
	        }  
	        type = buffImages[0].getType();  
	        chunkWidth = buffImages[0].getWidth();  
	        chunkHeight = buffImages[0].getHeight();  
	
	        //Initializing the final image  
	        BufferedImage finalImg = new BufferedImage(chunkWidth*cols, chunkHeight*rows, type);  
	
	        int num = 0;  
	        for (int k = 0; k < rows; k++) {  
	            for (int j = 0; j < cols; j++) {  
	                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * k, null);  
	                num++;  
	            }  
	        }  
	        System.out.println("Image concatenated.....");  
	        ImageIO.write(finalImg, "jpeg", new File("finalImg.png"));  
	        {
	        	
	        }
	        }
	        catch(Exception e)
        	{
        		
        	}
}
}
