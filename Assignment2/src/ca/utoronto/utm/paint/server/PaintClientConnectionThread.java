package ca.utoronto.utm.paint.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintClientConnectionThread extends Thread{
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket socket;
	private PaintModel model;
	private String address;
	private int port;
	
	public PaintClientConnectionThread(PaintModel model,String address, int port){
		super("PaintClientConnectionThread");
		this.address = address;
		this.port = port;
		this.model = model;
	}
	public void run(){
		
		try(Socket socket = new Socket(InetAddress.getByName(address),port);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			){
				this.socket = socket;
				this.out = out;
				System.out.println("connected");
				this.model.setServerStatus(true);
				while(!socket.isClosed()){
					try{
						Object inputObject = in.readObject();
						this.processInput(inputObject);
						Thread.sleep(10);
					}
					catch(EOFException e){
						
					} 
					catch(ClassNotFoundException e){
						e.printStackTrace();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					catch (SocketException e) {
						//The socket is closed do nothing and let the thread end
						socket.close();
						this.model.setServerStatus(false);
					}
				}
				this.model.setServerStatus(false);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public void processInput(Object o){
		if(o instanceof ArrayList){
			this.model.setShapes((ArrayList<Shape>) o);
			ArrayList<Shape> shape = (ArrayList<Shape>) o;
			System.out.println("A list" + shape.size());
		}
		
		if(o instanceof Shape){
			this.model.addShapeFromServer((Shape) o);
			System.out.println("shape");
		}
	}
	
	public void sendObjectToServer(Object o){
		try {
			out.writeObject(o);
			out.flush();
		}catch(SocketException e){
			//The server closed the socket
			this.model.setServerStatus(false);
			this.model.addShape((Shape)o); //re-add the shape
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
