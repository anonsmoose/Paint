package ca.utoronto.utm.paint.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintServer extends Thread{
	private ArrayList<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int portNumber;
	public PaintServer(ArrayList shapes, int portNumber){
		this.shapes = shapes;	
		this.portNumber = portNumber;
	}
	
	public void run(){
		boolean listeningForClients = true;
		try (ServerSocket serverSocket = new ServerSocket(portNumber)){
			while(listeningForClients){
				new PaintServerConnectionThread(this, serverSocket.accept()).start();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		} 
	}
	
	public void addShape(Shape shape){
		this.shapes.add(shape);
		sendShapeToClients(shape);
	}
	
	public void sendShapeToClients(Shape shape){
		for(ObjectOutputStream client : clients){
			try {
				client.writeObject(shape);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendCurrentShapes(ObjectOutputStream out){
		try {
			out.writeObject(this.shapes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addClient(ObjectOutputStream client){
		this.clients.add(client);
		System.out.println(client.toString());
	}
	
	public void removeClient(ObjectOutputStream client){
		this.clients.remove(client);
	}
	
}
