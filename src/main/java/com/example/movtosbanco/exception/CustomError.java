package com.example.movtosbanco.exception;

import lombok.Getter;

import java.util.Date;


@Getter
public class CustomError {

    private Date timeStamp;
    private String mensaje;
    private int HttpCode;


    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public int getHttpCode() {
        return HttpCode;
    }
    public void setHttpCode(int httpCode) {
        HttpCode = httpCode;
    }

    public CustomError(Date timeStamp, String mensaje, int HttpCode) {
        super();
        this.timeStamp = timeStamp;
        this.mensaje = mensaje;
        this.HttpCode = HttpCode;
    }


    public CustomError(Date timeStamp, String mensaje) {
        super();
        this.timeStamp = timeStamp;
        this.mensaje = mensaje;
    }


}
