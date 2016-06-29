package service.barcodeReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer {

	public void startServer() throws IOException{

        //Generated UUID 128-bit
        UUID uuid = new UUID("1e76aceef06b462c9f1b97b7d5d39e9a", false);
        //java.util.UUID uuid = UUID.fromString("1e76acee-f06b-462c-9f1b-97b7d5d39e9a");
		//Create the servicve url
        String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";

        //open server url
        while(true){
	        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open( connectionString );
	
	        //Wait for client connection
	        System.out.println("\nServer Started. Waiting for clients to connect...");
	        StreamConnection connection=streamConnNotifier.acceptAndOpen();
	
	        RemoteDevice dev = RemoteDevice.getRemoteDevice(connection);
	        System.out.println(LocalDateTime.now());
	        System.out.println("Remote device address: "+dev.getBluetoothAddress());
	        System.out.println("Remote device name: "+dev.getFriendlyName(true));
	
	      //read string from spp client
	        InputStream inStream = connection.openInputStream();
	        BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
	        String lineRead = bReader.readLine();
	
	        while(lineRead != null){
	        	try{
	        		System.out.println(lineRead);
	        		lineRead = bReader.readLine();
	        	}
	        	catch(IOException e){
	        		System.err.println(e);
	        		break;
	        	}
	        }
	        System.out.println("Connection was closed.");
	        streamConnNotifier.close();
        }
	}
}