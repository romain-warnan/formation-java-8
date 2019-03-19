package fr.insee.formation.part1;

import java.util.Arrays;

class SalutClasse implements BonjourInterface, CoucouInterface {

    @Override
    public String hello(String name) {
        return String.format("Salut %s !", name);
    }
}
