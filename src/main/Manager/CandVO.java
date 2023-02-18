package main.Manager;

import java.awt.image.BufferedImage;

public class CandVO {
    private int cand_No;
    private String cand_Name;
    private String cand_Sent;
    private String elec_Name;
    private int elec_Num;
    private int cand_Selected;
    private BufferedImage cand_Img;

    public int getCand_No() {
        return cand_No;
    }

    public void setCand_No(int cand_No) {
        this.cand_No = cand_No;
    }

    public String getCand_Name() {
        return cand_Name;
    }

    public void setCand_Name(String cand_Name) {
        this.cand_Name = cand_Name;
    }

    public String getCand_Sent() {
        return cand_Sent;
    }

    public void setCand_Sent(String cand_Sent) {
        this.cand_Sent = cand_Sent;
    }

    public String getElec_Name() {
        return elec_Name;
    }

    public void setElec_Name(String elec_Name) {
        this.elec_Name = elec_Name;
    }

    public int getElec_Num() {
        return elec_Num;
    }

    public void setElec_Num(int elec_Num) {
        this.elec_Num = elec_Num;
    }

    public int getCand_Selected() {
        return cand_Selected;
    }

    public void setCand_Selected(int cand_Selected) {
        this.cand_Selected = cand_Selected;
    }

    public BufferedImage getCand_Img() {
        return cand_Img;
    }

    public void setCand_Img(BufferedImage cand_Img) {
        this.cand_Img = cand_Img;
    }
}
