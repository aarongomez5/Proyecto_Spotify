package com.example.spotify;

public class Playlist {
    private String tit_playlist;

    public Playlist(String tit_playlist) {
        this.tit_playlist = tit_playlist;
    }

    public String getTit_playlist() {
        return tit_playlist;
    }

    public void setTit_playlist(String tit_playlist) {
        this.tit_playlist = tit_playlist;
    }
}
