package by.sam.mvc.model;

import by.sam.mvc.entity.location.Town;
import by.sam.mvc.entity.menu.Cuisine;
import by.sam.mvc.entity.user.Client;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

public class OrderDto {



    private Town town;
    private DistrictDto district;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String time;
    private int menuId;
    private double price;
    private int countOfGuests;
    @NotEmpty
    private String address;

    private int clientId;
    @NotEmpty
    private String clientName;
    @NotEmpty
    private String clientSurName;
    @NotEmpty
    private String clientMobile;

    private List<Cuisine> cuisineList;



    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public DistrictDto getDistrict() {
        return district;
    }

    public void setDistrict(DistrictDto district) {
        this.district = district;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCountOfGuests() { return countOfGuests; }
    public void setCountOfGuests(int countOfGuests) { this.countOfGuests = countOfGuests; }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public List<Cuisine> getCuisineList() {
        return cuisineList;
    }

    public void setCuisineList(List<Cuisine> cuisineList) {
        this.cuisineList = cuisineList;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurName() {
        return clientSurName;
    }

    public void setClientSurName(String clientSurName) {
        this.clientSurName = clientSurName;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public void setClient(Client client){
        this.clientId = client.getId();
        this.clientName = client.getName();
        this.clientSurName = client.getSurname();
        this.clientMobile = client.getMobile();
    }
}
