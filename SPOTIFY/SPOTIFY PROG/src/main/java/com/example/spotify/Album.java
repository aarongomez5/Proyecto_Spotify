package com.example.spotify;

public class Album {
    private int anyo_album;
    private String tit_album;

    public Album(int anyo_album, String tit_album) {
        this.anyo_album = anyo_album;
        this.tit_album = tit_album;
    }

    public int getAnyo_album() {
        return anyo_album;
    }

    public void setAnyo_album(int año_album) {
        this.anyo_album = año_album;
    }

    public String getTit_album() {
        return tit_album;
    }

    public void setTit_album(String tit_album) {
        this.tit_album = tit_album;
    }
}
