package com.company;

import com.company.PieceTypes.PieceTestingSuite.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Yuriy on 2/12/2015.
 */
public enum PieceType {
    King, Queen, Rook, Bishop, Knight, Pawn, MountedBishop, MountedRook;

    /*public class AllPieceTest extends TestCase {
            public void testAllPieces(){
                BishopTest bishopTest= new BishopTest();
                bishopTest.testBishop();
                RookTest rookTest= new RookTest();
                rookTest.testRook();
            }
        }*/
   /* @RunWith(Suite.class)
    @Suite.SuiteClasses({KingTest.class, QueenTest.class, BishopTest.class,RookTest.class,KnightTest.class, PawnTest.class, MountedBishopTest.class, MountedRookTest.class})
    public static class AllPieceTest {
    }*/
}

