package lambethd.kraken.resource.interfaces;

import runescape.BuyingLimit;

import java.io.IOException;
import java.util.List;

public interface IBuyingLimitApi {
    List<BuyingLimit> getBuyingLimits() throws IOException;
}
