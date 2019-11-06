package apap.tutorial.gopud.service;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDB;
import apap.tutorial.gopud.repository.RestoranDB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
    @InjectMocks
    MenuService menuService = new MenuServiceImpl();
    @Mock
    MenuDB menuDb;

    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave() {
        MenuModel newMenu = new MenuModel();
        newMenu.setNama("kentang");
        newMenu.setDurasiMasak(10);
        newMenu.setHarga(BigInteger.valueOf(15000));
        newMenu.setDeskripsi("enak");


        menuService.addMenu(newMenu);
        verify(menuDb, times(1)).save(newMenu);
    }

    @Test
    public void whenGetMenuByIdCalledByExistingDataItShouldReturnCorrectData() {
        MenuModel returnedData = new MenuModel();
        returnedData.setNama("kentang");
        returnedData.setId((long)1);
        returnedData.setHarga(BigInteger.valueOf(15000));
        returnedData.setDeskripsi("enak");
        when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(returnedData
        ));

        Optional<MenuModel> dataFromServiceCall =
                menuService.getMenuByIdMenu(1L);
        verify(menuDb, times(1)).findById(1L);
        assertTrue(dataFromServiceCall.isPresent());
        MenuModel dataFromOptional = dataFromServiceCall.get();
        assertEquals("kentang", dataFromOptional.getNama());
        assertEquals(BigInteger.valueOf(15000), dataFromOptional.getHarga());
        assertEquals(Long.valueOf(1), dataFromOptional.getId());
        assertEquals("enak", dataFromOptional.getDeskripsi());
    }

    @Test
    public void whenFindMenuByIdRestoranListCalledItShouldReturnAllMenu() {
        List<MenuModel> allMenuInRestoran = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allMenuInRestoran.add(new MenuModel());
        }
        when (menuService.findAllMenuByIdRestoran(1L)).thenReturn(allMenuInRestoran);
        List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(1L);
        assertEquals(3, dataFromServiceCall.size());
        verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
    }
    @Test
    public void whenChangeMenuCalledItShouldChangeMenuData() {
        MenuModel updatedData = new MenuModel();
        updatedData.setNama("ayam mekdi");
        updatedData.setId(Long.valueOf(1));
        updatedData.setDurasiMasak(10);
        updatedData.setHarga(BigInteger.valueOf(20000));
        updatedData.setDeskripsi("enak");
        when(menuDb.findById(1L)).thenReturn(Optional.of(updatedData));
        when(menuService.changeMenu(updatedData)).thenReturn(updatedData);
        MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
        assertEquals("ayam mekdi", dataFromServiceCall.getNama());
        assertEquals(BigInteger.valueOf(20000), dataFromServiceCall.getHarga());
        assertEquals(Long.valueOf(1), dataFromServiceCall.getId());
        assertEquals("enak", dataFromServiceCall.getDeskripsi());
    }

    @Test
    public void whenDeleteValidMenuItShouldRemoveTheMenu() {
        MenuModel menu = new MenuModel();
        menu.setNama("kentang");
        menu.setDurasiMasak(10);
        menu.setHarga(BigInteger.valueOf(15000));
        menu.setDeskripsi("enak");


        menuService.deleteMenu(menu);
        verify(menuDb, times(1)).delete(menu);
    }

    @Test
    public void whenMenuOrderByHargaAscCalledItShouldReturnAllMenuOrderByHargaAsc() {
        List<MenuModel> allMenuInRestoran = new ArrayList<>();
        for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
            allMenuInRestoran.add(new MenuModel());
        }
        when (menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(allMenuInRestoran);
        List<MenuModel> dataFromServiceCall = menuService.getListMenuOrderByHargaAsc(1L);
        assertEquals(3, dataFromServiceCall.size());
        verify(menuDb, times(1)).findByRestoranIdRestoranOrderByHarga(1L);
    }
}
