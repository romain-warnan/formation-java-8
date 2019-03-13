package fr.insee.formation.part1;

interface BonjourInterface {
    default String hello(String name) {
        return String.format("Bonjour %s !", name);
    }
}
