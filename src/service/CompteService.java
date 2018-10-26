package service;

import bean.Compte;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class CompteService extends AbstractFacade<Compte> {

    public CompteService() {
        super(Compte.class);
    }

    public Compte ouvrirCompte(String rib, double solde) {
        Compte c1 = new Compte();
        c1.setRib(rib);
        c1.setSolde(solde);
        c1.setOuvert(true);
        if (solde >= 0 && solde < 200) {
            c1.setClasse('D');
        } else if (solde >= 200 && solde < 1000) {
            c1.setClasse('C');
        } else if (solde >= 1000 && solde < 60000) {
            c1.setClasse('B');
        } else {
            c1.setClasse('A');
        }
        create(c1);
        return c1;
    }

//    public int fermerCompte(Compte compte) {
//        if (compte.isOuvert() == false) {
//            return -1;
//        } else if (compte.getSolde() != 0) {
//            return -2;
//        }else{
//            compte.setOuvert(false);
//            System.out.println("le compte est fermé");
//            return 1;
//        }  
//    }
    public int crediter(String rib, double montantCredit) {
        Compte compte = find(rib);
        if (compte == null) {
            return -2;
        } else {
            if (compte.isOuvert() == false) {
                return -1;
            } else {
                compte.setSolde(compte.getSolde() + montantCredit);
                edit(compte);
                return 1;
            }
        }
    }

    public int debiter(String rib, double montantDebit) {
        Compte compte = find(rib);
        if (compte.isOuvert() == false) {
            return -1;
        } else if (montantDebit > compte.getSolde() - 100) {
            return -2;
        } else if (montantDebit > 6000) {
            return -3;
        } else {
            compte.setSolde(compte.getSolde() - montantDebit);
        }
        edit(compte);
        return 1;
    }

    public int transferer(String rib1, String rib2, double montant) {
        Compte compteSource = find(rib1);
        Compte compteDestination = find(rib2);
        if (compteSource == null || compteDestination == null) {
            return -3;
        } else if (compteSource.isOuvert() == false || compteDestination.isOuvert() == false) {
            return -1;
        } else if (montant > compteSource.getSolde()) {
            return -2;
        } else {
            compteSource.setSolde(compteSource.getSolde() - montant);
            compteDestination.setSolde(compteDestination.getSolde() + montant);
            return 1;
        }

//    private Compte find(String rib) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public List<Compte> searchByCriteria(String rib, Double soldeMin, Double soldeMax) {
//        String query = constructQuery(rib, soldeMin, soldeMax);
//        return getEntityManager().createQuery(query).getResultList();
//    }
    public List<Compte> searchByCriteria(String rib, Double soldeMin, Double soldeMax) {
        String query = constructQuery(rib, soldeMin, soldeMax);
        return getEntityManager().createQuery(query).getResultList();
    }

    private String constructQuery(String rib, Double soldeMin, Double soldeMax) {
        String query = "SELECT c FROM COMPTE c WHERE 1=1";
        if (rib != null && !rib.equals("")) {
            query += "AND c.rib'" + rib + "'";
            return query;
        }
        if (soldeMin != null && !soldeMin.equals("")) {
            query += "AND c.solde >= '" + soldeMin + "'";
        }
        if (soldeMax != null && !soldeMax.equals("")) {
            query += "AND c.solde<='" + soldeMax + "'";
        }

        return query;
    }
}
