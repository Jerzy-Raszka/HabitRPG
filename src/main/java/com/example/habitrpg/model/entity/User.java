package com.example.habitrpg.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private Integer level;
    private Integer xp;
    private Integer gold;
    private Integer maxHp;
    private Integer currentHp;
    //inventory


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.level = 1;
        this.xp = 0;
        this.gold = 0;
        this.maxHp = 100;
        this.currentHp = 100;
    }

    public void addLevel() {
        this.level += 1;
        this.setCurrentHp(this.getMaxHp());
    }

    public void addXp(Integer addedXp) {
        this.xp += addedXp;
        while (this.xp >= 100) {
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
        this.currentHp += hpAmount;
        if (this.currentHp > this.maxHp) {
            this.currentHp = this.maxHp;
        }
    }

    public void subtractHp(Integer hpAmount) {
        if (hpAmount >= this.currentHp) {
            throw new IllegalStateException("You died");
        }
        this.currentHp -= hpAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(level, user.level) && Objects.equals(xp, user.xp) && Objects.equals(gold, user.gold) && Objects.equals(maxHp, user.maxHp) && Objects.equals(currentHp, user.currentHp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, level, xp, gold, maxHp, currentHp);
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

    public Integer getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(Integer currentHp) {
        this.currentHp = currentHp;
    }

    public Integer getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(Integer maxHp) {
        this.maxHp = maxHp;
    }
}
