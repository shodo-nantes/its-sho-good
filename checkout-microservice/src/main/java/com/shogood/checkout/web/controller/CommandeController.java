package com.shogood.checkout.web.controller;


import com.shogood.checkout.dao.CommandesDao;
import com.shogood.checkout.model.Commande;
import com.shogood.checkout.web.exceptions.CommandeNotFoundException;
import com.shogood.checkout.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;

import static java.time.LocalTime.now;

@RestController
public class CommandeController {

    final
    CommandesDao commandesDao;

    public CommandeController(CommandesDao commandesDao) {
        this.commandesDao = commandesDao;
        this.commandesDao.save(new Commande(0, 0, Date.from(Instant.now()), 1, true));
    }

    @PostMapping (value = "/checkout")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping(value = "/checkout/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }

    /*
    * Permet de mettre à jour une commande existante.
    * save() mettra à jours uniquement les champs renseignés dans l'objet commande reçu. Ainsi dans ce cas, comme le champs date dans "commande" n'est
    * pas renseigné, la date précédemment enregistrée restera en place
    **/
    @PutMapping(value = "/checkout")
    public void updateCommande(@RequestBody Commande commande) {

        commandesDao.save(commande);
    }
}
