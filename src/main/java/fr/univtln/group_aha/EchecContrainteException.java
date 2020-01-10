package fr.univtln.group_aha;

public class EchecContrainteException extends Exception {
    public EchecContrainteException(String classe) {
        super("Echec de la création ou de la modification de la ligne de la table " + classe);
    }
}
