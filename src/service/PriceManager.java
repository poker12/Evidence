package service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * PriceManager provides functions for price operations 
 * 
 * @author Tomáš Paleèek
 *
 */
public class PriceManager {

	/**
	 * 
	 * @param valueWithVat - Price with VAT, from which price without VAT is calculated
	 * @param vatRate - VAT rate for calculate price without VAT
	 * @param scale - Rounding position
	 * @return Price without VAT or null in case of incorrect input values 
	 */
	public BigDecimal calculatePriceWithoutVat(BigDecimal valueWithVat, BigDecimal vatRate, int scale){
		if(valueWithVat.signum() <= 0 || vatRate.signum() <= 0 || scale < 0)
			return null;
		return valueWithVat.divide((vatRate.add(new BigDecimal(100)).divide(new BigDecimal(100), scale, RoundingMode.HALF_UP)), scale, RoundingMode.HALF_UP);
	}
	
	/**
	 * 
	 * @param valueWithoutVat - Price without VAT, from which price with VAT is calculated
	 * @param vatRate - VAT rate for calculate price with VAT
	 * @param scale - Rounding position
	 * @return Price with VAT or null in case of incorrect input values
	 */
	public BigDecimal calculatePriceWithVat(BigDecimal valueWithoutVat, BigDecimal vatRate, int scale){
		if(valueWithoutVat.signum() <= 0 || vatRate.signum() <= 0 || scale < 0)
			return null;
		return valueWithoutVat.multiply((vatRate.add(new BigDecimal(100)).divide(new BigDecimal(100), scale, RoundingMode.HALF_UP)))
				.setScale(scale, RoundingMode.HALF_UP);
	}
	
	/**
	 * 
	 * @param priceWithoutVat - Price without VAT, from which VAT price will be calculated
	 * @param vatRate - VAT rate for calculate VAT price
	 * @param scale - Rounding position
	 * @return Price of VAT or null in case of incorrect input values
	 */
	public BigDecimal calculateVatPriceFromPriceWithoutVat(BigDecimal priceWithoutVat, BigDecimal vatRate, int scale){
		if(priceWithoutVat.signum() <= 0 || vatRate.signum() <= 0 || scale < 0)
			return null;
		BigDecimal myVatRate = vatRate.multiply(new BigDecimal(0.01));
		BigDecimal vatPrice = priceWithoutVat.multiply(myVatRate);
		vatPrice = vatPrice.setScale(scale, RoundingMode.HALF_UP);
		return vatPrice;
	}
}
