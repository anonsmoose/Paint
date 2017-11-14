package ca.utoronto.utm.paint.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Shapes.Shape;

public class PaintServer extends Thread{
	private ArrayList<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int portNumber;
	private ServerSocket serverSocket;
	private boolean listeningForClients = true;
	private ArrayList<Socket> sockets;
	public PaintServer(ArrayList shapes, int portNumber){
		this.shapes = shapes;	
		this.portNumber = portNumber;
	}
	
	public void run(){
		try (ServerSocket serverSocket = new ServerSocket(portNumber)){
			this.serverSocket = serverSocket;
			this.sockets = new ArrayList<Socket>();
			
			while(listeningForClients){
				Socket socket = serverSocket.accept();
				new PaintServerConnectionThread(this, socket).start();
				this.sockets.add(socket);
			}
		}catch(IOException e){
			this.listeningForClients = false;
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
			}catch(SocketException e){

			}
			catch (IOException e) {
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
	
	public void close(){
		try {
			this.serverSocket.close();
			for(Socket socket : sockets){
				socket.close();
			}
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
