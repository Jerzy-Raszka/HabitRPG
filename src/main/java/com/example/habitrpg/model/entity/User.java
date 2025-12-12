package com.example.habitrpg.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true)
    private String username;
    private String password;
    private Integer level;
    private Integer xp;
    private Integer gold;
    private Integer hp;
    //inventory


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.level = 1;
        this.xp = 0;
        this.gold = 0;
        this.hp = 100;
    }

    public void addLevel() {
        this.level += 1;
    }

    public void addXp(Integer addedXp) {
        this.xp += addedXp;
        if (this.xp >= 100) {
            this.xp -= 100;
            this.addLevel();
        }
    }

    public void addGold(Integer goldAmount) {
        this.gold += goldAmount;
    }

    public void subtractGold(Integer goldAmount) {
        if (goldAmount > this.gold) {
            throw new IllegalStateException("Not enough gold");
        }
        this.gold -= goldAmount;
    }

    public void addHp(Integer hpAmount) {
        this.hp += hpAmount;
    }

    public void subtractHp(Integer hpAmount) {
        if (hpAmount >= this.hp) {
            throw new IllegalStateException("You died");
        }
        this.hp -= hpAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(level, user.level) && Objects.equals(xp, user.xp) && Objects.equals(gold, user.gold) && Objects.equals(hp, user.hp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, level, xp, gold, hp);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }
}
