package fr.insee.formation.part1;

import java.util.Arrays;
import java.util.Comparator;

class SalutClasse implements BonjourInterface, CoucouInterface {

    Comparator<Point> comparator = Comparator.comparingInt(Point::getY).thenComparing(Point::getX);
    Comparator<String> comparator2 = Comparator.reverseOrder();

    @Override
    public String hello(String name) {
        Arrays.asList("a", "b", "c").sort(Comparator.reverseOrder());
        return String.format("Salut %s !", name);
    }

    static class Point {

        int x, y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
