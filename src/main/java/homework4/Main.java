package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
        // load in the graph from the file
        SocialNetwork network = new SocialNetwork();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Loading in the social network...");

        try {
            File file = new File("social_network.csv");
            Scanner fileScanner = new Scanner(file);
            fileScanner.nextLine();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(";");
                String user1 = data[0].trim();
                String user2 = data[1].trim();
                int friendshipStrength = Integer.parseInt(data[2].trim());
                Friendship friendship = new Friendship(user1, user2, friendshipStrength);
                network.addFriendship(friendship);
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        System.out.println("Network successfully loaded.");

        //Print out the total number of users and friendships.
        System.out.println("Total users: " + network.getNumberOfUsers());
        System.out.println("Total friendships: " + network.getNumberOfFriendships());
        System.out.println("=================================================== \n");

        //Next, repeatedly ask the user to type in a name and surname to recommend friends to, or -1 to exit.
        while (true) {
            System.out.print("Enter a name to recommend friends for, or -1 to exit: ");
            String input = scanner.nextLine();

            //If the user does not exist in the graph:
            //Print out an appropriate error message.
            if (input.equals("-1")) {
                System.out.println("Thank you for using our friendship recommender algorithm.");
                break;
            }

            ArrayList<FriendshipRecommendation> recommendations = network.recommendFriends(input);
            if (recommendations.isEmpty()) {
                System.out.println("The user you are looking for does not exist in the network. \n");
            } else {
                // If the user exists in the graph:
                // Find the friend recommendations.
                // Print out the total number of recommendations.
                // Print out the top 10 recommendations, in descending order (from highest recommendation strength towards lower).
                System.out.println("Total recommendations: " + recommendations.size());
                System.out.println("Top 10 recommendations based on friendship strength: ");
                recommendations.stream().limit(10).forEach(System.out::println);
            }
        }
        scanner.close();
    }
}
