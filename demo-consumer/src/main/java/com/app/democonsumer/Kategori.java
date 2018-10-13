package com.app.democonsumer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Kategori {

    private Long id;

    @NotEmpty
    @Size(max = 100, min = 3)
    private String name;

    public Kategori(){}

    public Kategori(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
