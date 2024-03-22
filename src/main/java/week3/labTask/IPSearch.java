package week3.labTask;

import week3.labTask.IPAddress;

public class IPSearch {
    public static IPAddress search(IPAddress[] ipAddresses, String ipAddress) {
        // return IPAddress object in array if exist and null if it does not exist
        long searchedIPAddress = convertToIPAddress(ipAddress);

        int low = 0;
        int hight = ipAddresses.length - 1;

        //binary search to locate the IPAddress
        while (low <= hight){
            int mid = low + (hight - low) / 2;

            //pay attention to ranges
            if (searchedIPAddress < ipAddresses[mid].getStartIp()){
                hight = mid-1;
            } else if (searchedIPAddress > ipAddresses[mid].getEndIp()){
                low = mid + 1;
            } else {
                return ipAddresses[mid];
            }
        }

        return null;
    }
    //convert String ipAddress into long type, extract octets (w,x,y,z), split input at .

    public static long convertToIPAddress (String ipAddress){
        String[] splitedAddress = ipAddress.split("\\.");
        //IP number =16777216*w + 65536*x + 256*y + z
        return (16777216L * Integer.parseInt(splitedAddress[0]))
                + (65536L * Integer.parseInt(splitedAddress[1]))
                + (256L * Integer.parseInt(splitedAddress[2]))
                + Integer.parseInt(splitedAddress[3]);
    }
}
