package lambethd.kraken.resource.mapper;

import lambethd.kraken.resource.interfaces.IHistoricalDataMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import runescape.HistoricalData;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class HistoricalDataMapper implements IHistoricalDataMapper {

    @Override
    public List<HistoricalData> mapToHistoricalData(String data) {
        List<HistoricalData> returnList = new ArrayList<>();
        Document doc = Jsoup.parse(data);
        if (doc.select(".mw-highlight") != null && doc.select(".mw-highlight").first() != null && doc.select(".mw-highlight").first().child(0) != null && doc.select(".mw-highlight").first().child(0).select(".s1") != null) {
            for (Element element : doc.select(".mw-highlight").first().child(0).select(".s1")) {
                String hist = element.childNode(0).toString();
                hist = hist.replace("'", "");
                String[] split = hist.split(":");
                HistoricalData historicalData = new HistoricalData();
                long epoch = Long.parseLong(split[0]);
                historicalData.setDate(LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC));
                historicalData.setPrice(Float.parseFloat(split[1]));
                returnList.add(historicalData);
            }
            return returnList;
        } else if (doc.select(".mw-code") != null && doc.select(".mw-code").hasText()) {
            String text = doc.select(".mw-code").text();
            Arrays.stream(text.replace("return", "").replace("{", "").replace("}", "").split(",")).forEach(value -> {
                String[] keyValue = value.replace("'", "").trim().split(":");
                HistoricalData historicalData = new HistoricalData();
                long epoch = Long.parseLong(keyValue[0]);
                historicalData.setDate(LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.UTC));
                historicalData.setPrice(Float.parseFloat(keyValue[1]));
                returnList.add(historicalData);
            });
            return returnList;
        } else {
            return null;
        }
    }
}
