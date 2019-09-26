package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDB menuDB;

    @Override
    public void addMenu(MenuModel menu) {
        menuDB.save(menu);
    }

    @Override
    public List<MenuModel> findAllMenuByIdRestoran(long idRestoran) {
        return menuDB.findByRestoranIdRestoran(idRestoran);
    }
}
