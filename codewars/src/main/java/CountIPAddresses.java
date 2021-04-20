import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountIPAddresses {

    public static void main(String[] args) {
        System.out.println(new IpAddress("20.0.0.10").rangeTo("20.0.1.0"));
    }

    static class IpAddress {
        List<Integer> addressParts = new ArrayList<>();
        public IpAddress(String ipAddress) {
            addressParts.addAll(Arrays.stream(ipAddress.split("\\."))
                .map(Integer::parseInt).collect(Collectors.toList()));
        }
        int rangeTo(String ipAddress) {
            IpAddress other = new IpAddress(ipAddress);
            int result= 0;
            for(int i = 0; i < 4; i++) {
                System.out.println(i + " " + other.addressParts.get(i));
                result += (other.addressParts.get(i) - addressParts.get(i)) * Math.pow(256, (3 - i));
            }
            return result;
        }
    }
}
