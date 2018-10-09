package com.binninfo.tobacco.entity;

public class Map {
    private Integer id;
    private String target;
    private Double coordinateX;
    private Double coordinateY;
    private Double angle;
    private String mapIMG;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public String getMapIMG() {
        return mapIMG;
    }

    public void setMapIMG(String mapIMG) {
        this.mapIMG = mapIMG;
    }
}
