package cn.tiiis.dynamicconf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {

    @Value("${cn.tiiis.user.name}")
    private String name;

    @Value("${cn.tiiis.user.phone}")
    private Integer phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
