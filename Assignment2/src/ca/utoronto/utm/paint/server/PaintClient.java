package ca.utoronto.utm.paint.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintClient {

	public static void main(String[] args) {
		try(
				Socket paintSocket = new Socket("localhost",27854);
				ObjectOutputStream out = new ObjectOutputStream(paintSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(paintSocket.getInputStream());
				){
				
				System.out.println(((ArrayList)(in.readObject())).size());
				out.flush();
				while(!paintSocket.isClosed()){
					try{
						Thread.sleep(100);
						Shape shape = (Shape) in.readObject();
						out.flush();
						if(shape != null)
							System.out.println(shape.toString());
					}catch(EOFException e){} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
		
	

}
