package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<MenuModel> getMenuByIdMenu(Long id) {
        return menuDB.findById(id);
    }

    @Override
    public MenuModel changeMenu(MenuModel menuModel) {
        //ngambil object menu yang ingin diubah
        MenuModel targetMenu = menuDB.findById(menuModel.getId()).get();

        try{
            targetMenu.setNama((menuModel).getNama());
            targetMenu.setDeskripsi(menuModel.getDeskripsi());
            targetMenu.setHarga(menuModel.getHarga());
            targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
            menuDB.save(targetMenu);
            return targetMenu;
        }
        catch (NullPointerException nullException){
            return null;
        }
    }

    public void deleteMenu(MenuModel menu) { menuDB.delete(menu); }

    public List<MenuModel> getListMenuOrderByHargaAsc(Long idRestoran) {
        return menuDB.findByRestoranIdRestoranOrderByHarga(idRestoran);
    }
}
