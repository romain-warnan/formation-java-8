package fr.insee.formation.part1;

import fr.insee.formation.part1.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class InterfaceMethodTest {

    @Test
    void simple_default_method_call_from_interface_coucou() {
        CoucouInterface salutation = new CoucouClasse();
        String actual = salutation.hello("Jean");
        assertThat(actual).isEqualTo("Coucou Jean !");
    }

    @Test
    void simple_default_method_call_from_interface_bonjour() {
        BonjourInterface salutation = new BonjourClasse();
        String actual = salutation.hello("Pierre");
        assertThat(actual).isEqualTo("Bonjour Pierre !");
    }

    @Test
    void static_method_call_from_class_salut() {
        SalutClasse salutation = new SalutClasse();
        String actual = salutation.hello("Marie");
        assertThat(actual).isEqualTo("Salut Marie !");
    }
}
