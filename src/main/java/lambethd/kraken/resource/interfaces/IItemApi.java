package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Item;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public interface IItemApi {
    Stream<Item> getItems() throws IOException;
}
