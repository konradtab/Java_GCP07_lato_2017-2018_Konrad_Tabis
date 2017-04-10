package application;

public class User
{
    private String username, password,sex,address;
    private Integer age;

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public void setSex(String sex){this.sex=sex;}

    public void setAddress(String address){this.address=address;}

    public void setAge(int age){this.age=age;}
}