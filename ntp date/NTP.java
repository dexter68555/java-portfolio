import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.net.ntp.*;

public class NTP {
	
	public static Date getNTPDate() {

		//domain name for NTP server. Set your prefer NTP server
        String[] hosts = new String[]{
            "ntp02.oal.ul.pt", "ntp04.oal.ul.pt",
            "ntp.xs4all.nl"};

        NTPUDPClient client = new NTPUDPClient();

        //set 5 seconds timeout if no response from NTP server
        client.setDefaultTimeout(5000);

        //get date information from NTP server
        for (String host : hosts) {

            try {
                InetAddress hostAddr = InetAddress.getByName(host);
                TimeInfo info = client.getTime(hostAddr);
                Date date = new Date(info.getReturnTime());
                return date;

            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        //close connection. Always remember to close it.
        client.close();

        return null;

    }

	public static void main(String args[]) {  

		Date date = null;
		
		date = getNTPDate();
		
		if(date != null) {
			
			System.out.println(date);
			
		}else {
			
			System.out.println("NTP server not responding or wrong damain name.");
			
		}
		

	}

}
