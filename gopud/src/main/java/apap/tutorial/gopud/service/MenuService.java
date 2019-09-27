package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MenuService {
    void addMenu(MenuModel menu);
    List<MenuModel> findAllMenuByIdRestoran(long idRestoran);

    //Method untuk mendapatkan data sebuah Menu berdasarkan idRestoran
    Optional<MenuModel> getMenuByIdMenu(Long id);

    MenuModel changeMenu(MenuModel menuModel);

    void deleteMenu(MenuModel Menu);
}