package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class TicketMachineTest {
    private static final int PRICE = 50; // Une constante

    private TicketMachine machine; // l'objet à tester

    @BeforeEach
    public void setUp() {
        machine = new TicketMachine(PRICE); // On initialise l'objet à tester
    }

    @Test
        // On vérifie que le prix affiché correspond au paramètre passé lors de
        // l'initialisation
        // S1 : le prix affiché correspond à l’initialisation.
    void priceIsCorrectlyInitialized() {
        // Paramètres : valeur attendue, valeur effective, message si erreur
        assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
    }

    @Test
        // S2 : la balance change quand on insère de l’argent
    void insertMoneyChangesBalance() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        // WHEN On insère de l'argent
        machine.insertMoney(10);
        machine.insertMoney(20);
        // THEN La balance est mise à jour, les montants sont correctement additionnés
        assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
    }

    @Test
        // S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
    void printTicket() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)

        // THEN le ticket ne s'imprime pas car il n'y a pas assez d'argent
        assertFalse(machine.printTicket());
    }

    @Test
        // S4 : on imprime le ticket si le montant inséré est suffisant
    void printTicketWithMoney() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        machine.insertMoney(50);
        // THEN le ticket s'imprime
        assertTrue(machine.printTicket());
    }

    @Test
        // S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
    void decrementeWhenPrintTicket() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        machine.insertMoney(50);
        // THEN le ticket s'imprime
        machine.printTicket();
        assertEquals(0, machine.getBalance(), "La balance n'est pas correctement mise à jour");
    }

    @Test
        // S6 : Le montant collecté est mis à jour quand on imprime un ticket (pas avant)
    void collectedBalanceIsUpdatedAfterPrintTicket() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        machine.insertMoney(50);
        assertEquals(50, machine.getBalance(), "La balance n'est pas correctement mise à jour");
        // THEN le ticket s'imprime
        machine.printTicket();
        assertEquals(0, machine.getBalance(), "La balance n'est pas correctement mise à jour");
    }

    @Test
    // S7 : rend correctement la monnaie
    void refundIsRefundingTheMoney() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        machine.insertMoney(100);
        machine.refund();
        assertEquals(0, machine.getTotal(), "Le total n'est pas correctement mise à jour");
    }

    @Test
    // S8 : rend correctement la monnaie
    void refundIsUpdatedBalanceAfterRefund() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        machine.insertMoney(100);
        machine.refund();
        assertEquals(0, machine.getBalance(), "La balance n'est pas correctement mise à jour");
    }

    @Test
    // S9 : On ne peut pas insérer un montant négatif
    void insertNegatifAmount() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        assertThrows(IllegalArgumentException.class, () -> {
            machine.insertMoney(-10);
        });
    }

    @Test
    // S10 : On ne peut pas créer des machine qui délivre des tickets dont le prix est négatif
    void createMachineWithNegativeTicket() {
        // GIVEN : une machine vierge (initialisée dans @BeforeEach)
        assertThrows(IllegalArgumentException.class, () -> {
            new TicketMachine(-10);
        });
    }



}
