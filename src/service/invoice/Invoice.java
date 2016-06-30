package service.invoice;

import java.math.BigDecimal;
import java.util.List;

public class Invoice {

	private List<Item> items;
	private BigDecimal summaryWithVat;
}
