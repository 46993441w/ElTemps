
package com.example.poblenou.eltemps.json;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class List implements Serializable{

    private Long dt;
    private Temp temp;
    private Double pressure;
    private Long humidity;
    private java.util.List<Weather> weather = new ArrayList<>();
    private Double speed;
    private Long deg;
    private Long clouds;
    private Double rain;

    /**
     * @return The dt
     */
    public Long getDt() {
        return dt;
    }

    /**
     * @param dt The dt
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     * @return The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     * @param temp The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     * @return The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The humidity
     */
    public Long getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     * @param weather The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     * @return The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * @param speed The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * @return The deg
     */
    public Long getDeg() {
        return deg;
    }

    /**
     * @param deg The deg
     */
    public void setDeg(Long deg) {
        this.deg = deg;
    }

    /**
     * @return The clouds
     */
    public Long getClouds() {
        return clouds;
    }

    /**
     * @param clouds The clouds
     */
    public void setClouds(Long clouds) {
        this.clouds = clouds;
    }

    /**
     * @return The rain
     */
    public Double getRain() {
        return rain;
    }

    /**
     * @param rain The rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }

    public String getForecastString() {
        Long dt = getDt();
        java.util.Date date = new java.util.Date(dt * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M");
        String dateString = dateFormat.format(date);
        String description = getWeather().get(0).getDescription();
        Long min = Math.round(getTemp().getMin());
        Long max = Math.round(getTemp().getMax());
        return String.format("%s - %s - %s/%s",
                dateString, description, min, max
        );
    }
}
