package data;

import models.Guest;
import java.util.ArrayList;
import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {

    public final static Guest GUEST = makeGuest();

    private final ArrayList<Guest> guests = new ArrayList<>();

    public GuestRepositoryDouble() {
        guests.add(GUEST);
    }

    @Override
    public List<Guest> findAll() {
        return guests;
    }

    @Override
    public Guest findByEmail(String guestEmail) {
        return guests.stream()
                .filter(i -> i.getGuestEmail().equals(guestEmail))
                .findFirst()
                .orElse(null);
    }

    public static Guest makeGuest() {
        Guest guest = new Guest();

        guest.setGuestId(1);
        guest.setLastName("Sullivan");
        guest.setFirstName("Lomas");
        guest.setGuestEmail("slomas0@mediafire.com");
        guest.setPhoneNumber("(702) 7768761");
        guest.setState("NV");

        return guest;
    }

}
