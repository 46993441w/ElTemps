
package com.example.poblenou.eltemps.json;

import java.io.Serializable;
import java.util.ArrayList;

public class Forecast implements Serializable {

    private City city;
    private String cod;
    private Double message;
    private Long cnt;
    private java.util.List<com.example.poblenou.eltemps.json.List> list = new ArrayList<>();

    /**
     * @return The city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return The message
     */
    public Double getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * @return The cnt
     */
    public Long getCnt() {
        return cnt;
    }

    /**
     * @param cnt The cnt
     */
    public void setCnt(Long cnt) {
        this.cnt = cnt;
    }

    /**
     * @return The list
     */
    public java.util.List<com.example.poblenou.eltemps.json.List> getList() {
        return list;
    }

    /**
     * @param list The list
     */
    public void setList(java.util.List<com.example.poblenou.eltemps.json.List> list) {
        this.list = list;
    }

}
