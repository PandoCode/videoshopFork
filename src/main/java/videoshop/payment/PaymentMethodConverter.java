package videoshop.payment;

import org.salespointframework.payment.*;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.LocalDateTime;

public class PaymentMethodConverter {
    public static PaymentMethod toPaymentMethod(String name) {
        return switch (name) {
            case "cash" -> Cash.CASH;
            case "cheque" -> new Cheque(
                    "test", "123456789", "987654321",
                    "Boss", LocalDateTime.now(), "TestBank", "1111111111", "222222222");
            case "creditcard" -> new CreditCard(
                    "testName", "aliasTestName", "123456789", "test", "111111",
                    LocalDateTime.of(2024, 4, 1, 0, 0), LocalDateTime.of(2025, 4, 1, 0, 0),
                    "3333333",
                    Monetary.getDefaultAmountFactory().setCurrency("EUR").setNumber(1).create(),
                    Monetary.getDefaultAmountFactory().setCurrency("EUR").setNumber(1).create());
            case "debitcard" -> new DebitCard(
                    "testName", "aliasTestName", "123456789", "test", "111111",
                    LocalDateTime.of(2024, 4, 1, 0, 0), LocalDateTime.of(2025, 4, 1, 0, 0),
                    "3333333",
                    Monetary.getDefaultAmountFactory().setCurrency("EUR").setNumber(1).create());
            default -> throw new IllegalStateException("Wert: " + name + " als Zahlungsmethode nicht gültig!");
        };
    }

    public static String toName(PaymentMethod paymentMethod) {
        if(paymentMethod instanceof Cash) return "Bargeld";
        else if(paymentMethod instanceof Cheque) return "Scheck";
        else if(paymentMethod instanceof CreditCard) return "Kreditkarte";
        else if(paymentMethod instanceof DebitCard) return "EC-Karte";

        throw new IllegalStateException("Wert: " + PaymentMethod.class.getName() + " als Zahlungsmethode nicht gültig!");
    }
}
