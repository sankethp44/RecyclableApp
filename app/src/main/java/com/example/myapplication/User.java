package com.example.myapplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class User {
    @Nullable
    private String name;
    @Nullable
    private String emailAccount;
    @Nullable
    private String city;

    public User(@Nullable String name, @Nullable String emailAccount, @Nullable String city) {
        this.name = name;
        this.emailAccount = emailAccount;
        this.city = city;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(@Nullable String emailAccount) {
        this.emailAccount = emailAccount;
    }

    @Nullable
    public String getCity() {
        return city;
    }

    public void setCity(@Nullable String city) {
        this.city = city;
    }

    @NotNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailAccount='" + emailAccount + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (emailAccount != null ? emailAccount.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (emailAccount != null ? !emailAccount.equals(user.emailAccount) : user.emailAccount != null) return false;
        return city != null ? city.equals(user.city) : user.city == null;
    }
}
