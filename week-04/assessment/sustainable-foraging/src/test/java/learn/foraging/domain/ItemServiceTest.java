package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    ItemService service = new ItemService(new ItemRepositoryDouble());

    @Test
    void shouldNotSaveNullName() throws DataException {
        Item item = new Item(0, null, Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveBlankName() throws DataException {
        Item item = new Item(0, "   \t\n", Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }
    //NEW
    @Test
    void shouldNotSaveNullCategory() throws DataException {
        Item item = new Item(0, "Test Item", null, new BigDecimal("5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveNullDollars() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, null);
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveNegativeDollars() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("-5.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveTooLargeDollars() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("9999.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveTooDuplicateName() throws DataException {
        Item item = new Item(0, "Chanterelle", Category.EDIBLE, new BigDecimal("9.00"));
        Result<Item> result = service.add(item);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotSaveNullItem() throws DataException {
        Item item = null;
        Result<Item> result = service.add(item);
        assertEquals(result.getErrorMessages().get(0), "Item must not be null.");
    }

    @Test
    void shouldSave() throws DataException {
        Item item = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("5.00"));

        Result<Item> result = service.add(item);

        assertNotNull(result.getPayload());
        assertEquals(2, result.getPayload().getId());
    }

    //NEW
    @Test
    void shouldFindByCategory() throws DataException {
        List<Item> items = service.findByCategory(Category.EDIBLE);

        assertTrue(items.get(0).getName().equals("Chanterelle"));
    }

    //NEW
    @Test
    void shouldNotFindMissingCategory() throws DataException {
        List<Item> items = service.findByCategory(Category.INEDIBLE);

        assertEquals(items.size(), 0);
    }

}