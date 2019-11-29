
package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/v1")
public class MenuRestController {
    @Autowired
    private MenuRestService menuRestService;

    @PostMapping(value = "/menu")
    private MenuModel createRestoran(@Valid @RequestBody MenuModel menu, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field");
        }
        else{
            return menuRestService.createRestoran(menu);
        }
    }

    @PutMapping(value="/menu/{id}")
    private MenuModel updateMenu(@PathVariable(value = "id") Long id, @RequestBody MenuModel menu){
        try {
            return menuRestService.changeMenu(id, menu);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Menu "+String.valueOf(id)+" Not Found");
        }
    }

    @GetMapping(value="/menu/{id}")
    private MenuModel retrieveMenu(@PathVariable(value = "id") Long id){
        try{
            return menuRestService.retrieveMenu(id);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "ID Restoran "+String.valueOf(id)+" Not Found");
        }
    }

    @GetMapping(value = "/menus")
    private List<MenuModel> retrieveAllMenu(){
        return menuRestService.retrieveAllMenu();
    }

    @DeleteMapping(value = "/menu/{id}")
    private ResponseEntity<String> deleteMenu(@PathVariable("id") Long id){
        try{
            menuRestService.deleteMenu(id);
            return ResponseEntity.ok("Menu with ID "+String.valueOf(id)+" Deleted!");
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Menu with ID "+String.valueOf(id)+" Not Found!");
        }
    }
}
=======
package apap.tutorial.gopud.restcontroller;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuRestService;
import apap.tutorial.gopud.service.RestoranRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1")
public class MenuRestController {
        @Autowired
        private MenuRestService menuRestService;

        @Autowired
        private RestoranRestService restoranRestService;

        @PostMapping(value = "/menu")
        private MenuModel createMenu(@Valid @RequestBody MenuModel menu, BindingResult bindingResult){
            if (bindingResult.hasFieldErrors()){
                throw new ResponseStatusException(HttpStatus.MULTI_STATUS.BAD_REQUEST, "Request body has invalid type or missing field");
            }
            else {
                RestoranModel restoran= restoranRestService.getRestoranByIdRestoran(2L);
                restoran.getListMenu().add(menu);
                menu.setRestoran(restoran);
                return menuRestService.createMenu(menu);
            }
        }

        @GetMapping(value = "/menu/{idMenu}")
        private MenuModel retrieveMenu(@PathVariable("idMenu") Long idMenu){
            try{
                return menuRestService.getMenuByIdMenu(idMenu);
            }catch (NoSuchElementException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Menu " + String.valueOf(idMenu) + " Not Found");
            }
        }

        @DeleteMapping(value = "/menu/{idMenu}")
        private ResponseEntity<String> deleteMenu(@PathVariable("idMenu") Long idMenu){
            try{
                menuRestService.deleteMenu(idMenu);
                return ResponseEntity.ok("Menu has been deleted");
            }catch (NoSuchElementException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu with ID " + String.valueOf(idMenu) + " Not Found");
            }
        }

        @PutMapping(value = "/menu/{idMenu}")
        private MenuModel updateMenu(
                @PathVariable (value = "idMenu") Long idMenu,
                @RequestBody MenuModel menu
        ){
            try{
                return menuRestService.changeMenu(idMenu, menu);
            }catch (NoSuchElementException e){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID Menu " + String.valueOf(idMenu) + " Not Found");
            }
        }

        @GetMapping(value = "/menus")
        private List<MenuModel> retriveListRestoran(){
            return menuRestService.retriveListMenu();
        }
}
