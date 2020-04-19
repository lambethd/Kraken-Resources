package lambethd.kraken.resource.interfaces;

import org.springframework.stereotype.Service;
import runescape.Catalogue;
import runescape.ItemCategory;

import java.io.IOException;

@Service
public interface ICatalogueApi {
    Catalogue getCatalogueForCategory(ItemCategory category) throws IOException;
}
