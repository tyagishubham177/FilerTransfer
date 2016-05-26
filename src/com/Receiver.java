package com;
/**
 *
 * @author Vidit
 */
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Receiver {


	public static Scanner scanner;


	public static void main (String [] args ) throws IOException {


		String fileLocation,ipAddress;
		int portNo;
		scanner=new Scanner(System.in);
		ipAddress = JOptionPane.showInputDialog("Enter the IP Address of the sender: ");
		
		String blah2=JOptionPane.showInputDialog("Enter the port number  :");
		portNo=Integer.parseInt(blah2);
		fileLocation=JOptionPane.showInputDialog("Please enter file location with file name to save : ");	
		Receiver.receiveFile(ipAddress, portNo, fileLocation);
	}
        
	public static void receiveFile(String ipAddress,int portNo,String fileLocation) throws IOException
	{

		int bytesRead=0;
		int current = 0;
		FileOutputStream fileOutputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		Socket socket = null;
		try {

			
			socket = new Socket(ipAddress,portNo);
			System.out.println("connected.");
			
			
			byte [] byteArray  = new byte [6022386];					
			System.out.println("Please wait downloading file");
			
			
			InputStream inputStream = socket.getInputStream();
			fileOutputStream = new FileOutputStream(fileLocation);
			bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bytesRead = inputStream.read(byteArray,0,byteArray.length);					

			current = bytesRead;
			do {
				bytesRead =inputStream.read(byteArray, current, (byteArray.length-current));
				if(bytesRead >= 0) current += bytesRead;
			} while(bytesRead > -1);
			bufferedOutputStream.write(byteArray, 0 , current);							
			bufferedOutputStream.flush();												
			
			System.out.println("File " + fileLocation  + " downloaded ( size: " + current + " bytes)");
                        JOptionPane.showMessageDialog(null, "File Location :" +fileLocation+ " \n\ndownloaded ( size: " + current + " bytes read)");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if (fileOutputStream != null) fileOutputStream.close();
			if (bufferedOutputStream != null) bufferedOutputStream.close();
			if (socket != null) socket.close();
		}
	}
}
