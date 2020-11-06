package com.springboot.hwz.practicaltraning.entity;



import javax.persistence.*;
import java.util.Date;


@Entity
public class Browse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer browseid;


    private Integer userid;


    private String movieids;


    private Date browsetime;

    public Integer getBrowseid() {
        return browseid;
    }

    public void setBrowseid(Integer browseid) {
        this.browseid = browseid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getmovieids() {
        return movieids;
    }

    public void setmovieids(String movieids) {
        this.movieids = movieids;
    }

    public Date getBrowsetime() {
        return browsetime;
    }

    public void setBrowsetime(Date browsetime) {
        this.browsetime = browsetime;
    }
}
