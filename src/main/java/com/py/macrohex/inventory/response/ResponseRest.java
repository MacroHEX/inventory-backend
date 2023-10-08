package com.py.macrohex.inventory.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {
  private ArrayList<HashMap<String, String>> metada = new ArrayList<>();

  public ArrayList<HashMap<String, String>> getMetada() {
    return metada;
  }

  public void setMetada(String type, String code, String date) {
    HashMap<String, String> map = new HashMap<String, String>();

    map.put("type", type);
    map.put("code", code);
    map.put("date", date);

    metada.add(map);
  }
}
