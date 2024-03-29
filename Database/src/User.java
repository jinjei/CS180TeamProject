public class User {
    private String name;
    private String bio;
    public User(String name, String bio){
        this.name = name;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }
    public String getBio(){
        return bio;
    }
}
