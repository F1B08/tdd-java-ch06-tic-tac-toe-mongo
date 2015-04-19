package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TickTackToeCollectionSpec {

    TickTackToeCollection collection;

    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TickTackToeCollection());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasDbNameTickTackToe() {
//            throws UnknownHostException {
//        TickTackToeCollection collection = new TickTackToeCollection();
        assertEquals(
                "tick-tack-toe",
                collection.getMongoCollection().getDBCollection().getDB().getName());
    }

    @Test
    public void whenInstantiatedThenMongoCollectionHasNameGame() {
//            throws UnknownHostException {
//        TickTackToeCollection collection = new TickTackToeCollection();
        assertEquals(
                "game",
                collection.getMongoCollection().getName());
    }

    @Test
    public void whenSaveMoveThenInvokeMongoCollectionSave() {
        TickTackToeBean bean = new TickTackToeBean(3, 2, 1, 'Y');
        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }

}
