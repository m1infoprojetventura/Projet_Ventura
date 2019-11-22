package fr.univtln.group_aha;

public class Materiel {
    private TypeMateriel typeMateriel;
    public enum TypeMateriel{
        ORDINATEUR ("ORDINATEUR"),
        TABLEAU_TACTIL("TABLEAU_TACTIL"),
        VIDEO_PROJECTEUR("VIDEO_PROJECTEUR"),
        IMPRIMANTE("IMPRIMANTE");
        private String name;
        TypeMateriel(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    public Materiel(TypeMateriel typeMateriel) {
        this.typeMateriel = typeMateriel;
    }
}
