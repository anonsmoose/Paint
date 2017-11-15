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
/**
 * The server thread used for hosting a session of
 * Paint Online
 *
 */
public class PaintServer extends Thread{
	private ArrayList<ObjectOutputStream> clients = new ArrayList<ObjectOutputStream>();
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private int portNumber;
	private ServerSocket serverSocket;
	private boolean listeningForClients = true;
	private ArrayList<Socket> sockets;
	
	/**
	 * Constructs the paint server
	 * @param shapes The shape list passed from the local model when the server starts
	 * @param portNumber The port the server is hosting through
	 */
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
	
	/**
	 * Adds a shape to the servers shape list and sends the new shape out
	 * to all of the presently connected clients
	 * @param shape The shape command being added to the server shape list 
	 */
	public void addShape(Shape shape){
		this.shapes.add(shape);
		sendShapeToClients(shape);
	}
	/**
	 * Sends a shape to all of the connected clients
	 * @param shape
	 */
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
	
	/**
	 * Sends the current shape list to the specified client (Typically when they initially connect)
	 * @param out The output stream of the client
	 */
	public void sendCurrentShapes(ObjectOutputStream out){
		try {
			out.writeObject(this.shapes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the server socket, allowing the thread to terminate.
	 */
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
	
	/**
	 * Adds a new client's output stream to the server's list of clients
	 * @param client The output stream of the client being added
	 */
	public void addClient(ObjectOutputStream client){
		this.clients.add(client);
	}
	
	/**
	 * Removes a client's output stream from the servers client list (for when they disconnect)
	 * @param client The client's output stream being removed
	 */
	public void removeClient(ObjectOutputStream client){
		this.clients.remove(client);
	}
	
}
