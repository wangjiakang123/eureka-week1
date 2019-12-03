package com.wjk.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Shoop {

    private Integer id;
    private String name;
    private Integer price;
    private String imge_url;
    private Integer upper_Status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date factory_date;
    private String dase;

    public String getDase() {
        return dase;
    }

    public void setDase(String dase) {
        this.dase = dase;
    }

    public Shoop() {
        super();
    }

    @Override
    public String toString() {
        return "Shoop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imge_url='" + imge_url + '\'' +
                ", upper_Status=" + upper_Status +
                ", factory_date=" + factory_date +
                '}';
    }

    public Shoop(Integer id, String name, Integer price, String imge_url, Integer upper_Status, Date factory_date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imge_url = imge_url;
        this.upper_Status = upper_Status;
        this.factory_date = factory_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImge_url() {
        return imge_url;
    }

    public void setImge_url(String imge_url) {
        this.imge_url = imge_url;
    }

    public Integer getUpper_Status() {
        return upper_Status;
    }

    public void setUpper_Status(Integer upper_Status) {
        this.upper_Status = upper_Status;
    }

    public Date getFactory_date() {
        return factory_date;
    }

    public void setFactory_date(Date factory_date) {
        this.factory_date = factory_date;
    }
}
