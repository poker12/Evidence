package service.barcodeReader;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.LocalDevice;

public class ServerRun {

	public static void main(String[] args) {

		//display local device address and name
        LocalDevice localDevice;
		try {
			localDevice = LocalDevice.getLocalDevice();
			System.out.println("Address: "+localDevice.getBluetoothAddress());
	        System.out.println("Name: "+localDevice.getFriendlyName());
	        
	        BluetoothServer sampleSPPServer=new BluetoothServer();
	        sampleSPPServer.startServer();
		} catch (BluetoothStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
