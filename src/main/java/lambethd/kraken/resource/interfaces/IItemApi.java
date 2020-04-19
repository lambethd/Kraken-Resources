package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Item;

import java.io.IOException;
import java.util.List;

@Service
public interface IItemApi {
    List<Item> getItems() throws IOException;
}
