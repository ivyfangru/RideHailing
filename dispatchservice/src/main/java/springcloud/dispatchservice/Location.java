package springcloud.dispatchservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Location {
    private double latitude;
    private double longitude;
    private long id;
    private LocalDateTime timestamp;

    public Location(){

    }

    public Location(double longitude, double latitude, LocalDateTime timestamp){
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    @JsonProperty
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    @JsonProperty
    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    @JsonProperty
    public double getLongitude(){
        return this.longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    @JsonProperty
    public double getLatitude(){
        return this.latitude;
    }

    @JsonProperty
    public String getGeohash(){
        return GeoHashUtils.encode(latitude, longitude);
    }
}
