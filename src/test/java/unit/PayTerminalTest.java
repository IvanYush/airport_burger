package unit;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.*;
import static com.javaacademy.burger.dish.DishType.*;

public class PayTerminalTest {

    @Test
    @DisplayName("проверка терминала на предмет покупки бургера за рубли")
    public void buyBurgertoRubles() {
        PayTerminal payTerminal = new PayTerminal();
        Paycheck paycheckResult = payTerminal.pay(BURGER, RUB);
        Paycheck expected = new Paycheck(BigDecimal.valueOf(300), RUB, BURGER);

        Assertions.assertEquals(expected, paycheckResult);
    }

    @Test
    @DisplayName("проверка терминала на предмет покупки бургера за мозамбикские доллары")
    public void buyBurger() {
        PayTerminal payTerminal = new PayTerminal();

        Assertions.assertThrows(NotAcceptedCurrencyException.class, () -> payTerminal.pay(BURGER, MOZAMBICAN_DOLLARS));
    }
}
