package fr.insee.formation.part1;

interface CoucouInterface {
    default String hello(String name) {
        return String.format("Coucou %s !", name);
    }
}
