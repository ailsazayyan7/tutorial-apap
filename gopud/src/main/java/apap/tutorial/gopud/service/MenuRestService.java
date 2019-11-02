package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MenuRestService {
    MenuModel createMenu(MenuModel menu);
    List<MenuModel> retriveListMenu();
    MenuModel getMenuByIdMenu(Long idMenu);
    MenuModel changeMenu(Long idMenu, MenuModel menuUpdate);
    void deleteMenu(Long idMenu);
    /*Mono<String> getStatus(Long idMenu);
    Mono<RestoranDetail> postStatus();*/
}
