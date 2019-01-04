package springcloud.locationservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver {
    private long id;
    private String firstName;
    private String lastName;

    public Driver(){

    }

    public Driver(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @JsonProperty
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @JsonProperty
    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @JsonProperty
    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }



}
