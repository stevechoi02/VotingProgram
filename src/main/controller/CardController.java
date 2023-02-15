package main.controller;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CardController extends ViewController {

    private Component currentView = null;
    private List<Component> views;
    private Map<Component, String> mapNames;

    private Container parent;
    private CardLayout cardLayout;

    public CardController(Container parent, CardLayout cardLayout){
        this.cardLayout=cardLayout;
        this.parent=parent;
        views = new ArrayList<>(25);
        mapNames = new HashMap<>(25);
    }

    public Container getParent(){
        return parent;
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public void addView(Component comp, String name){
        views.add(comp);
        mapNames.put(comp, name);
        getParent().add(comp,name);
    }

    public void removeView(Component comp, String name) {
        views.remove(comp);
        mapNames.remove(comp);
        getParent().remove(comp);
    }

    @Override
    public void nextView() {
        if (views.size() > 0) {
            String name = null;
            if (currentView == null) {
                currentView = views.get(0);
                name = mapNames.get(currentView);
            } else {
                int index = views.indexOf(currentView);
                index++;
                if (index >= views.size()) {
                    index = 0;
                }
                currentView = views.get(index);
                name = mapNames.get(currentView);
            }
            getCardLayout().show(getParent(), name);
        }
    }

    @Override
    public void getView(String name){
        if(validate(mapNames.get(currentView))){
            currentView = getKeyValue(mapNames, name);
            getCardLayout().show(getParent(), name);
        }
    }

    @Override
    public void previousView() {
        if (views.size() > 0) {
            String name = null;
            if (currentView == null) {
                currentView = views.get(views.size() - 1);
                name = mapNames.get(currentView);
            } else {
                int index = views.indexOf(currentView);
                index--;
                if (index < 0) {
                    index = views.size() - 1;
                }
                currentView = views.get(index);
                name = mapNames.get(currentView);
            }
            getCardLayout().show(getParent(), name);
        }
    }

    public static <T, E> T getKeyValue(Map<T,E> map, E value){
        for (Map.Entry<T,E> entry: map.entrySet()){
            if(Objects.equals(value, entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean validate(String name){
        boolean res = true;
        //데이타베이스 controller 객체와 communicate 해서 조건이 맞으면 true, 아니면 false를 리턴
        return res;
    }
}
