package fr.insee.formation.part1;

import java.util.List;

interface BonjourInterface {
    default String hello(String name) {
        return String.format("Bonjour %s !", name);
    }
}
