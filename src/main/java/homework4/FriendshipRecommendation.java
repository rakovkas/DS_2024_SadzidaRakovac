package homework4;

public class FriendshipRecommendation implements Comparable<FriendshipRecommendation> {
    private String user;
    private double recommendationStrength;

    public FriendshipRecommendation(String user, double recommendationStrength) {
        this.user = user;
        this.recommendationStrength = recommendationStrength;
    }

    public String getUser() {
        return user;
    }

    public double getRecommendationStrength() {
        return recommendationStrength;
    }

    @Override
    public int compareTo(FriendshipRecommendation o) {
        return Double.compare(this.recommendationStrength, o.recommendationStrength);
    }

    @Override
    public String toString() {
        return user + ": " + recommendationStrength;
    }

}
