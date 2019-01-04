package springcloud.locationservice;

import java.util.HashMap;

public class GeoHashUtils {
    private static final char[] BASE_32 =  {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                                            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
                                            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final HashMap<Character, Integer> DECODE_MAP = new HashMap<>();
    private static final int PRECISION = 12;
    private static final int[] BITS = {16, 8, 4, 2, 1};

    static {
        for(int i = 0; i < BASE_32.length; i++){
            DECODE_MAP.put(Character.valueOf(BASE_32[i]), Integer.valueOf(i));
        }
    }

    private GeoHashUtils(){}

    /**
     * Encodes the given longitude and latitude into a geohash
     * @param latitude latitude to encode
     * @param longitude longitude to encode
     * @return Geohash encoding of the latitude and longitude
     */
    public static String encode(double latitude, double longitude){
        double[] latInterval = {-90.0, 90.0};
        double[] longInterval = {-180.0, 180.0};

        final StringBuilder geohash = new StringBuilder();
        boolean isEven = true;

        int bit = 0;
        int ch = 0;

        while(geohash.length() < PRECISION){
            double mid = 0.0;
            if(isEven){
                mid = (longInterval[0] + longInterval[1]) / 2D;
                if(longitude > mid){
                    ch |= BITS[bit];
                    longInterval[0] = mid;
                } else {
                    longInterval[1] = mid;
                }
            } else {
                mid = (latInterval[0] + latInterval[1]) / 2D;
                if(latitude > mid){
                    ch |= BITS[bit];
                    latInterval[0] = mid;
                } else {
                    latInterval[1] = mid;
                }
            }

            isEven = !isEven;

            if(bit < 4){
                bit++;
            } else {
                geohash.append(BASE_32[ch]);
                bit = 0;
                ch = 0;
            }
        }
        return geohash.toString();
    }

    public static double[] decode(String geohash){
        final double[] latInterval = {-90.0, 90.0};
        final double[] longInterval = {-180.0, 180.0};

        boolean isEven = true;
        double latitude;
        double longitude;

        for(int i = 0; i < geohash.length(); i++){
            final int cd = DECODE_MAP.get(Character.valueOf(geohash.charAt(i))).intValue();
            for(int mask : BITS){
                if(isEven){
                    if((cd & mask) != 0){
                        longInterval[0] = (longInterval[0] + longInterval[1]) / 2D;
                    } else {
                        longInterval[1] = (longInterval[0] + longInterval[1]) / 2D;
                    }

                } else {
                    if((cd & mask) != 0){
                        latInterval[0] = (latInterval[0] + latInterval[1]) / 2D;
                    } else {
                        latInterval[1] = (latInterval[0] + latInterval[1]) / 2D;
                    }
                }
                isEven = !isEven;
            }
        }
        latitude = (latInterval[0] + latInterval[1]) / 2D;
        longitude = (longInterval[0] + longInterval[1]) / 2D;

        return new double[]{latitude, longitude};
    }

}
