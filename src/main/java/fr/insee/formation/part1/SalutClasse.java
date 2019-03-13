package fr.insee.formation.part1;

class SalutClasse implements BonjourInterface, CoucouInterface {

    @Override
    public String hello(String name) {
        return String.format("Salut %s !", name);
    }
}
