/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Admin
 */
@Entity
public class Compte implements Serializable {

    public Compte(String rib, double solde) {
        this.rib = rib;
        this.solde = solde;
    }

    private static final long serialVersionUID = 1L;
    @Id
    private String rib;
    private double solde;
    private boolean ouvert;
    private char classe;

    public Compte() {
    }

    public Compte(String rib, double solde, char classe) {
        this.rib = rib;
        this.solde = solde;
        this.ouvert = false;
        this.classe = classe;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public boolean isOuvert() {
        return ouvert;
    }

    public void setOuvert(boolean ouvert) {
        this.ouvert = ouvert;
    }

    public char getClasse() {
        return classe;
    }

    public void setClasse(char classe) {
        this.classe = classe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rib != null ? rib.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.rib == null && other.rib != null) || (this.rib != null && !this.rib.equals(other.rib))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Compte{" + "rib=" + rib + ", solde=" + solde + ", ouvert=" + ouvert + ", classe=" + classe + '}';
    }

}
