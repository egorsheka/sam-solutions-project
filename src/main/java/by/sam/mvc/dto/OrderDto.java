package by.sam.mvc.dto;

import by.sam.mvc.models.location.Town;
import by.sam.mvc.models.menu.Menu;
import org.springframework.format.annotation.DateTimeFormat;

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
}
