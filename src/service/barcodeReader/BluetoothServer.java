package service.barcodeReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Observable;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer extends Observable implements Runnable{
	
	//thread-safe singleton
	private static final BluetoothServer server = new BluetoothServer();
	
	private StreamConnection connection = null;
	private LocalDevice localDevice = null;
	private String readBarcode = "";
	
	//Generated UUID 128-bit
    private UUID uuid = new UUID("1e76aceef06b462c9f1b97b7d5d39e9a", false);
    //Create the service url
    private String connectionString = "btspp://localhost:" + uuid +";name=Sample SPP Server";
    
	/**
	 * Try to get local bluetooth device
	 * @return True if local bluetooth device was found, false if not
	 */
	public boolean initialize(){
		try {
			localDevice = LocalDevice.getLocalDevice();
			return true;
		} catch (BluetoothStateException e) {
			return false;
		}
	}
	
	public String getLocalDeviceName(){
		if(localDevice != null)
			return localDevice.getFriendlyName();
		return "";
	}
	
	public String getLocalDeviceBluetoothAddress(){
		if(localDevice != null)
			return localDevice.getBluetoothAddress();
		return "";
	}
	
	public String getConnectedDeviceName(){
		if(connection == null)
			return "";
		try {
			return RemoteDevice.getRemoteDevice(connection).getFriendlyName(true);
		} catch (IOException e) {
			return "";
		}
	}
	
	public String getConnectedDeviceBluetoothAddress(){
		if(connection == null)
			return "";
		try {
			return RemoteDevice.getRemoteDevice(connection).getBluetoothAddress();
		} catch (IOException e) {
			return "";
		}
	}
	
	public void startServer() throws IOException{
        //open server url
        while(true){
	        StreamConnectionNotifier streamConnNotifier = (StreamConnectionNotifier)Connector.open(connectionString);
	        
	        //Wait for client connection
	        System.out.println("\nServer Started. Waiting for clients to connect...");
	        connection=streamConnNotifier.acceptAndOpen();
	        
	        System.out.println("Remote device address: " + getConnectedDeviceBluetoothAddress());
	        System.out.println("Remote device name: " + getConnectedDeviceName());
	
	        //read string from spp client
	        InputStream inStream = connection.openInputStream();
	        BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));
	        String lineRead = bReader.readLine();
	
	        while(lineRead != null){
	        	try{
	        		readCode(lineRead);
	        		lineRead = bReader.readLine();
	        	}
	        	catch(IOException e){
	        		System.err.println(e);
	        		connection = null;
	        		readBarcode = "";
	        		break;
	        	}
	        }
	        System.out.println("Connection was closed.");
	        streamConnNotifier.close();
        }
	}
	
	public void readCode(String barcode){
		readBarcode = barcode;
		setChanged();
		notifyObservers(barcode);
	}

	public LocalDevice getLocalDevice() {
		return localDevice;
	}

	public String getReadBarcode() {
		return readBarcode;
	}

	private BluetoothServer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static BluetoothServer getInstance(){
		return server;
	}

	@Override
	public void run() {
		if(initialize()){
			try {
				startServer();
			} catch (IOException e) {
				System.out.println("Spojeni bylo prerusno nebo nebylo navazano");
				e.printStackTrace();
			}
		}
		else
			System.out.println("Bluetooth nebyl nalezen");
	}
	
}