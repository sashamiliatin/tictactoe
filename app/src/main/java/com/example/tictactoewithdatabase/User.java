package com.example.tictactoewithdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")

public class User {
    @PrimaryKey(autoGenerate = true)
    int userId;
    @ColumnInfo(name = "user_id")
    String id ;
    @ColumnInfo(name = "user_name")
    String name ;
    @ColumnInfo(name = "user_wins")
    int wins;
    @ColumnInfo(name = "user_games")
    int games;

    public User(String name, int wins, int games) {
        this.name = name;
        this.wins = wins;
        this.games = games;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }
}
