package com.packtpublishing.tddjava.ch03tictactoe.mongo;

import com.mongodb.MongoException;
import org.jongo.MongoCollection;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TickTackToeCollectionSpec {

    TickTackToeCollection collection;
    TickTackToeBean bean;
    MongoCollection mongoCollection;

    @Before
    public void before() throws UnknownHostException {
        collection = spy(new TickTackToeCollection());
        bean = new TickTackToeBean(3, 2, 1, 'Y');
        mongoCollection = mock(MongoCollection.class);
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
//        TickTackToeBean bean = new TickTackToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.saveMove(bean);
        verify(mongoCollection, times(1)).save(bean);
    }

    @Test
    public void whenSaveMoveThenReturnTrue() {
//        TickTackToeBean bean = new TickTackToeBean(3, 2, 1, 'Y');
//        MongoCollection mongoCollection = mock(MongoCollection.class);
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.saveMove(bean));
    }

    @Test
    public void givenExceptionWhenSaveMoveThenReturnFalse() {
        doThrow(new MongoException("Bla"))
                .when(mongoCollection)
                .save(any(TickTackToeBean.class));
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.saveMove(bean));
    }

    @Test
    public void whenDropThenInvokeMongoCollectionDrop() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        collection.drop();
        verify(mongoCollection, times(1)).drop();
    }

    @Test
    public void whenDropThenReturnTrue() {
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertTrue(collection.drop());
    }

    @Test
    public void givenExceptionWhenDropThenReturnFalse() {
        doThrow(new MongoException("Bla")).when(mongoCollection).drop();
        doReturn(mongoCollection).when(collection).getMongoCollection();
        assertFalse(collection.drop());
    }

}
