package com.mateo.Fuentes686Chumbita782;

import com.mateo.Fuentes686Chumbita782.classes.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactoStorage {

    private static List<Contacto> listaContactos = new ArrayList<>();

    public static List<Contacto> getListaContactos(){return listaContactos;};

    public static void setListaContactos(List<Contacto>list){listaContactos = list;}



}
