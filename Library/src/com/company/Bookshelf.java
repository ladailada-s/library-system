package com.company;

import java.util.ArrayList;

public class Bookshelf {
    private String sClassesCde;
    private String sClassesDesc;


    public Bookshelf(String sClassesCde,String sClassesDesc) {
        setsClassesCde(sClassesCde);
        setsClassesDesc(sClassesDesc);
    }

    public String getsClassesCde() {
        return sClassesCde;
    }

    public void setsClassesCde(String sClassesCde) {
        this.sClassesCde = sClassesCde;
    }

    public String getsClassesDesc() {
        return sClassesDesc;
    }

    public void setsClassesDesc(String sClassesDesc) {
        this.sClassesDesc = sClassesDesc;
    }


}
