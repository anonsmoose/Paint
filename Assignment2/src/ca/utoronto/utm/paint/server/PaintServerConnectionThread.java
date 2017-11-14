package ca.utoronto.utm.paint.server;

import java.awt.Color;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Point;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintServerConnectionThread extends Thread{
	private Socket clientSocket = null;
	private PaintServer server;
	
	public PaintServerConnectionThread(PaintServer server, Socket socket){
		super("PaintServerConnectionThread");
		this.clientSocket = socket;
		this.server = server;
	}
	
	public void run(){
		try (
			ObjectOutputStream out = new ObjectOutputStream(this.clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(this.clientSocket.getInputStream());
			){
			this.server.sendCurrentShapes(out);
			this.server.addClient(out);
			while(!clientSocket.isClosed()){
				Thread.sleep(10);
				try{
					Shape shape = (Shape)(in.readObject());
					if(shape != null)
						this.server.addShape(shape);
				}catch(EOFException e){} 
				 catch (ClassNotFoundException e) {
					e.printStackTrace();
				}catch(SocketException e){
					this.server.removeClient(out);
				}
			}
			this.server.removeClient(out);
		}catch(SocketException e){
			//Socket closed do nothing
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
