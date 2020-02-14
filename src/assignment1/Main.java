package assignment1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        final List<String> preferredFoods = new ArrayList<>();
        preferredFoods.add("pea soup");
        preferredFoods.add("pancakes");
        boolean restaurantFound = false;
        String restaurantFoundName = "";
        final Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        final int restaurantsCount = sc.nextInt();

        for (int i = 0; i < restaurantsCount; i++) {
            final int menuItemsCount = sc.nextInt();
            final String restaurantName = sc.next();
            final List<String> menuItems = new ArrayList<>();
            for (int x = 0; x < menuItemsCount; x++) {
                menuItems.add(sc.next());
            }

            if (!restaurantFound) {
                final Map<String, Boolean> preferredFoodTable = new LinkedHashMap<>();

                for (String preferredFood : preferredFoods) {
                    preferredFoodTable.put(preferredFood, false);
                    if (menuItems.parallelStream().anyMatch(s -> s.equals(preferredFood))) {
                        preferredFoodTable.replace(preferredFood, true);
                    }
                }

                // Matched all foods;
                if (preferredFoodTable.values().stream().allMatch(val -> val)) {
                    restaurantFound = true;
                    restaurantFoundName = restaurantName;
                }
            }
        }

        sc.close();

        if (restaurantFound) {
            System.out.println(restaurantFoundName);
        } else {
            System.out.println("Anywhere is fine I guess");
        }

    }
}
