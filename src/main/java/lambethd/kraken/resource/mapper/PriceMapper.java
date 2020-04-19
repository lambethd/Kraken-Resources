package lambethd.kraken.resource.mapper;

import lambethd.kraken.resource.interfaces.IPriceMapper;
import org.springframework.stereotype.Service;

@Service
public class PriceMapper implements IPriceMapper {
    @Override
    public float mapToFloat(String price) {
        price = price.replace(",", "") //remove commas
                .replace(" ", ""); //remove spaces
        boolean kPrice = price.contains("k");
        boolean mPrice = price.contains("m");
        boolean bPrice = price.contains("b");
        price = price.replace("k", "") //remove k
                .replace("m", "") //remove m
                .replace("b", ""); //remove b
        float floatPrice = Float.parseFloat(price);
        if (kPrice) {
            floatPrice = floatPrice * 1000;
        }
        if (mPrice) {
            floatPrice = floatPrice * 1000000;
        }
        if (bPrice) {
            floatPrice = floatPrice * 1000000000;
        }
        return floatPrice;
    }
}
