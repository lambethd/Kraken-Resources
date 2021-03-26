package lambethd.kraken.resource.mapper;

import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import runescape.BuyingLimit;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class BuyingLimitMapper {
    public List<BuyingLimit> map(String buyingLimitsString){
        String[] buyingLimitStrings = Jsoup.parse(buyingLimitsString)
                .select("pre")
                .first()
                .childNode(0)
                .toString()
                .replace(".*return\\s*","")
                .split(",");

        List<BuyingLimit> buyingLimits = new LinkedList<>();
        Arrays.asList(buyingLimitStrings).subList(2, buyingLimitStrings.length - 1).forEach(bl->{
            String name = bl.split("=")[0].replaceAll("\\[|\\]","").replace("\"","").trim();
            Integer value = Integer.parseInt(bl.split("=")[1].trim());
            BuyingLimit buyingLimit = new BuyingLimit();
            buyingLimit.setItemName(name);
            buyingLimit.setLimit(value);
            buyingLimits.add(buyingLimit);
        });

        return buyingLimits;
    }
}
