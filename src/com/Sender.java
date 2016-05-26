package com;
/**
 *
 * @author Vidit
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;
public class Sender {
	public static Scanner scanner;
	
	public static void main(String[] args) throws IOException
	{
		String fileLocation;
		int portNo;
		scanner=new Scanner(System.in);
		String blah=JOptionPane.showInputDialog("Enter the port Number: ");
		portNo=Integer.parseInt(blah);
		fileLocation=JOptionPane.showInputDialog("Enter location of the file to be sent : ");
		
		 Sender.send(portNo,fileLocation);
	}
	
	public static  void send(int portNo,String fileLocation) throws IOException
	{

		FileInputStream fileInputStream = null;
		BufferedInputStream bufferedInputStream = null;

		OutputStream outputStream = null;
		ServerSocket serverSocket = null;
		Socket socket = null;

		
		try {
			serverSocket = new ServerSocket(portNo);
			JOptionPane.showMessageDialog(null,"Waiting for the receiver...!!");
                        System.out.println("Waiting for receiver!!");
				try {
						socket = serverSocket.accept();
						JOptionPane.showMessageDialog(null,"Accepted connection : " + socket);
                                                System.out.println("Accepted Connection :" + socket);
						
	
						
						File file = new File (fileLocation);
						byte [] byteArray  = new byte [(int)file.length()];
						fileInputStream = new FileInputStream(file);
						bufferedInputStream = new BufferedInputStream(fileInputStream);
						bufferedInputStream.read(byteArray,0,byteArray.length);
	
						
						outputStream = socket.getOutputStream();
						System.out.println("Sending " + fileLocation + "( size: " + byteArray.length + " bytes)");
						outputStream.write(byteArray,0,byteArray.length);			
						outputStream.flush();										
						System.out.println("Done.");								
					}
					finally {
						if (bufferedInputStream != null) bufferedInputStream.close();
						if (outputStream != null) bufferedInputStream.close();
						if (socket!=null) socket.close();
					}		
			} catch (IOException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (serverSocket != null) serverSocket.close();
			}
	}
}

