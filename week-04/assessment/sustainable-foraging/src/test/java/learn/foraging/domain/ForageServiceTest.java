package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForageRepositoryDouble;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ForageServiceTest {

    ForageService service = new ForageService(
            new ForageRepositoryDouble(),
            new ForagerRepositoryDouble(),
            new ItemRepositoryDouble());

    //NEW
    @Test
    void shouldFindByDate() throws DataException {
        List<Forage> all = service.findByDate(LocalDate.of(2020, 6, 26));

        assertEquals(all.get(0).getKilograms(), 1.25);
    }

    //NEW
    @Test
    void shouldNotFindMissingDate() throws DataException {
        List<Forage> all = service.findByDate(LocalDate.of(2020, 7, 22));

        assertEquals(0, all.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Forage forage = new Forage();
        forage.setDate(LocalDate.now());
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(ItemRepositoryDouble.ITEM);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(36, result.getPayload().getId().length());
    }

    @Test
    void shouldNotAddWhenForagerNotFound() throws DataException {

        Forager forager = new Forager();
        forager.setId("30816379-188d-4552-913f-9a48405e8c08");
        forager.setFirstName("Ermengarde");
        forager.setLastName("Sansom");
        forager.setState("NM");

        Forage forage = new Forage();
        forage.setDate(LocalDate.now());
        forage.setForager(forager);
        forage.setItem(ItemRepositoryDouble.ITEM);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertFalse(result.isSuccess());
    }

    //NEW
    @Test
    void shouldNotAddNullForager() throws DataException {
        Forager forager = null;

        Forage forage = new Forage();
        forage.setDate(LocalDate.now());
        forage.setForager(forager);
        forage.setItem(ItemRepositoryDouble.ITEM);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Forager is required.");

    }

    //NEW
    @Test
    void shouldNotAddNullForage() throws DataException {
        Forage forage = null;

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Nothing to save.");

    }




    @Test
    void shouldNotAddWhenItemNotFound() throws DataException {

        Item item = new Item(11, "Dandelion", Category.EDIBLE, new BigDecimal("0.05"));

        Forage forage = new Forage();
        forage.setDate(LocalDate.now());
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(item);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertFalse(result.isSuccess());
    }


    //NEW
    @Test
    void shouldNotAddNullItem() throws DataException {

        Forage forage = new Forage();
        forage.setDate(LocalDate.now());
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(null);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Item is required.");
    }

    //NEW
    @Test
    void shouldNotAddNullDate() throws DataException {
        Item item = new Item(11, "Dandelion", Category.EDIBLE, new BigDecimal("0.05"));


        Forage forage = new Forage();
        forage.setDate(null);
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(item);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Forage date is required.");
    }

    //NEW
    @Test
    void shouldNotAddFutureDate() throws DataException {
        Item item = new Item(11, "Dandelion", Category.EDIBLE, new BigDecimal("0.05"));


        Forage forage = new Forage();
        forage.setDate(LocalDate.of(2025, 12, 12));
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(item);
        forage.setKilograms(0.5);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Forage date cannot be in the future.");
    }

    //NEW
    @Test
    void shouldNotAddIfKGOver250() throws DataException {
        Item item = new Item(11, "Dandelion", Category.EDIBLE, new BigDecimal("0.05"));


        Forage forage = new Forage();
        forage.setDate(LocalDate.of(2018, 12, 12));
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(item);
        forage.setKilograms(251);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Kilograms must be a positive number less than 250.0");
    }

    //NEW
    @Test
    void shouldNotAddIfKGEqualORLessThan0() throws DataException {
        Item item = new Item(11, "Dandelion", Category.EDIBLE, new BigDecimal("0.05"));


        Forage forage = new Forage();
        forage.setDate(LocalDate.of(2018, 12, 12));
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(item);
        forage.setKilograms(0);

        Result<Forage> result = service.add(forage);
        assertEquals(result.getErrorMessages().get(0), "Kilograms must be a positive number less than 250.0");
    }

    // New Test
    @Test
    void shouldNotAddDuplicateForage() throws DataException {

        Forage forage = new Forage();
        forage.setDate(LocalDate.of(2020, 6, 26));
        forage.setForager(ForagerRepositoryDouble.FORAGER);
        forage.setItem(ItemRepositoryDouble.ITEM);
        forage.setKilograms(0.5);

        Response actual = service.add(forage);
        assertFalse(actual.isSuccess());
        assertTrue(actual.getErrorMessages().get(0).equals("Cannot enter duplicate Forage with same Date, Item, and Forager"));
    }
    //NEW
    @Test
    void createKGReportShouldReturnCorrectTotalKG() throws DataException {
        Map<Item, Double> map = service.createItemKGReport(
                (LocalDate.of(2020, 6, 26)));

        assertTrue(map.containsValue(1.25));
    }
    //NEW
    @Test
    void createKGReportShouldNotReturnInCorrectTotalKG() throws DataException {
        Map<Item, Double> map = service.createItemKGReport(
                (LocalDate.of(2020, 6, 26)));

        assertFalse(map.containsValue(2.25));
    }

    //NEW
    @Test
    void createCategoryValueShouldReturnCorrectValue() throws DataException {
        Map<Category, BigDecimal> map = service.createCategoryValueReport(
                (LocalDate.of(2020, 6, 26)));
        BigDecimal expected = new BigDecimal("12.487500");

        assertTrue(map.containsValue(expected));
    }

    //NEW
    @Test
    void createCategoryValueShouldReturnNullIfForageNotFound() throws DataException {
        Map<Category, BigDecimal> map = service.createCategoryValueReport(
                (LocalDate.of(2020, 7, 26)));

        assertNull(map);
    }

}