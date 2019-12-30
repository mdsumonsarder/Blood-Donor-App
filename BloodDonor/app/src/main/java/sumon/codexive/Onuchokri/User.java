package sumon.codexive.Onuchokri;

/**
 * Created by drajusahfdfhsd on 2/17/2018.
 */

public class User {
    String name,phone,blood_city,blood_city_date,name_phone_date;

    public User(){

    }

    public User(String name_phone_date){
        this.name_phone_date = name_phone_date;
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }



    public User(String name, String phone, String blood_city) {
        this.name = name;
        this.phone = phone;
        this.blood_city = blood_city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlood_city() {
        return blood_city;
    }

    public void setBlood_city(String blood_city) {
        this.blood_city = blood_city;
    }

    public String getBlood_city_date() {
        return blood_city_date;
    }

    public void setBlood_city_date(String blood_city_date) {
        this.blood_city_date = blood_city_date;
    }

    public String getName_phone_date() {
        return name_phone_date;
    }

    public void setName_phone_date(String name_phone_date) {
        this.name_phone_date = name_phone_date;
    }

}
