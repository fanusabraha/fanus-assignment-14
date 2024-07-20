package coderscampus.assignment14.repository;

import java.util.UUID;

public class testuiid {

        public static void main(String[] args) {
            // Generate a random UUID (version 4)
            UUID randomUUID = UUID.randomUUID();
            System.out.println("Random UUID: " + randomUUID);

            // Generate a name-based UUID (version 5)
            UUID nameBasedUUID = UUID.nameUUIDFromBytes("example.com".getBytes());
            System.out.println("Name-based UUID: " + nameBasedUUID);
        }


}
