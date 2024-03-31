public class NewAccount {

    private String name;
    private String userName;
    private String email;
    private String category;
    private int phoneNum;
    private String password;

    public NewAccount(BadDataException e) {

        this.name = e.getMessage();
        this.userName = null;
        this.email = null;
        this.category = null;
        this.phoneNum = 0;
        this.password = null;

    }

    public NewAccount(String data) throws BadDataException {

        String[] parsed = data.split(",");

        if (parsed.length != 6) {
            throw new BadDataException("Bad User Data");
        }

        try {

            this.name = parsed[0];
            this.userName = parsed[1];
            this.email = parsed[2];
            this.category = parsed[3];
            this.phoneNum = Integer.parseInt(parsed[4]);
            this.password = parsed[5];


        } catch (Exception e) {
            throw new BadDataException("Bad User Data");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String loginInfo(String userName, String password) {
        return "username: " + this.userName +
                "\npassword: " + this.password;
    }

    @Override
    public String toString() {
        return "NewAccount{" +
                "name: '" + name + '\'' +
                ", userName: '" + userName + '\'' +
                ", email: '" + email + '\'' +
                ", category: '" + category + '\'' +
                ", phoneNum: " + phoneNum +
                '}';
    }
}
