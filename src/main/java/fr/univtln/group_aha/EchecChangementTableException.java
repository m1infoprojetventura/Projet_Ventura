package fr.univtln.group_aha;

public class EchecChangementTableException extends Exception {
    public EchecChangementTableException(String classe) {
        super("Echec de la création ou de la modification de la ligne de la table " + classe);
    }
}
