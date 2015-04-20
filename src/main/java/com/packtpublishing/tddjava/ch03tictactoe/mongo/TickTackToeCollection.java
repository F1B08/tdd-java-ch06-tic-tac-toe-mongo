package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

import java.net.UnknownHostException;

public class TickTackToeCollection {

    private MongoCollection mongoCollection;
    protected MongoCollection getMongoCollection() {
        return mongoCollection;
    }

    public TickTackToeCollection() throws UnknownHostException {
        DB db = new MongoClient().getDB("tick-tack-toe");
        mongoCollection = new Jongo(db).getCollection("game");
    }

    public boolean saveMove(TickTackToeBean bean) {
        try {
            getMongoCollection().save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean drop() {
        try {
            getMongoCollection().drop();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
