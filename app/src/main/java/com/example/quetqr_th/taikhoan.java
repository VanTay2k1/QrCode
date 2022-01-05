package com.example.quetqr_th;

public class taikhoan {
    private int id;
    private String username;
    private String password;
    private byte[] anh;

    public taikhoan(int id, String username, String password, byte[] anh) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.anh = anh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }
}
