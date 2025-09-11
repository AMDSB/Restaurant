package dev.sn.entities.enums;

public enum Role {

    GERANT,
    SERVEUR,
    CUISINIER,
    CLIENT;

    public String toUpperCase() {
        return name().toUpperCase();
    }
}
