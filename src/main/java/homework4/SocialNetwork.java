package homework4;

import java.util.*;

public class SocialNetwork {
    private int V;
    private int E;

    //This class should be structured like an edge-weighted graph using
    // an adjacency list. As a hint, you can use a HashMap to keep track
    // of required adjacency lists: user’s name → list of adjacent Friendships
    private HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {
        //constructor to initialize an empty social network with no vertices or edges.
        this.V = 0;
        this.E = 0;
        adj = new HashMap<String, ArrayList<Friendship>>();
    }

    public SocialNetwork(Scanner in) {
        //constructor to initialize a graph from an input stream (read: file) of the format given earlier.
        in.nextLine();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] data = line.split(";");
            if (data.length == 3) {
                String friend1 = data[0];
                String friend2 = data[1];
                int friendshipStrength = Integer.parseInt(data[2]);
                addFriendship(new Friendship(friend1, friend2, friendshipStrength));
            }
        }
    }

    public void addUser(String user) {
//add a new user into the graph, and increase the number of vertices.
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {
        // add a new “Friendship” into the graph, and increase the number of edges.
            // Friendship → two users and their friendship strength
        if (!adj.containsKey(f.getFriend1())) {
            addUser(f.getFriend1());
        }
        if (!adj.containsKey(f.getFriend2())) {
            addUser(f.getFriend2());
        }
        adj.get(f.getFriend1()).add(f);
        adj.get(f.getFriend2()).add(f);
        E++;
    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {
        if (!adj.containsKey(user)) {
            return new ArrayList<>();
        }

        HashMap<String, Integer> potentialFriends = new HashMap<>();
        ArrayList<Friendship> userFriendships = adj.get(user);

        //Given a “user” as a parameter, the method should go through all the user’s friends.
        String friend;
        for (Friendship f : userFriendships) {
            if (f.getFriend1().equals(user)) {
                friend = f.getFriend2();
            } else {
                friend = f.getFriend1();
            }

            //For each friend, go through all their friends (“friends of the friend”)
            // fof - friends of friend
            String potentialFriend = null;
            for (Friendship fof : adj.get(friend)) {
                if (fof.getFriend1().equals(friend)) {
                    potentialFriend = fof.getFriend2();
                } else {
                    potentialFriend = fof.getFriend1();
                }

                //For each user who is not already a friend of the “user” and is not
                // the “user” themselves,
                if (potentialFriends.equals(user) || containsFriendship(user, potentialFriend)) {
                    continue;
                }

                //sum the friendship weights of the mutual friends they share with the user.
                //The chosen weight should be the minimum between the user’s friendship
                // strength with the friend, and the friend’s friendship strength with
                // the “potential recommendation”
                int currentStrength = Math.min(f.getFriendshipStrength(), fof.getFriendshipStrength());
                potentialFriends.merge(potentialFriend, currentStrength, Integer::sum);
            }
        }

        //Return an ArrayList of FriendshipRecommendation objects sorted by the highest “recommendation strength”
        ArrayList<FriendshipRecommendation> recommendations = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : potentialFriends.entrySet()) {
            recommendations.add(new FriendshipRecommendation(entry.getKey(), entry.getValue()));
        }

        Collections.sort(recommendations);
        Collections.reverse(recommendations);

        return recommendations;
    }

    private boolean containsFriendship(String user, String potentialFriend) {
        // Checks if two users are already friends
        return adj.get(user).stream().anyMatch(f -> f.getFriend1().equals(potentialFriend) || f.getFriend2().equals(potentialFriend));
    }

    public int getNumberOfUsers() {
        return V;
    }

    public int getNumberOfFriendships() {
        return E;
    }
}
