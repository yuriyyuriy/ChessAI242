package com.company;

import com.company.PieceTypes.PieceTestingSuite.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Yuriy on 2/20/2015.
 */
public class AllTests{
    @RunWith(Suite.class)
    @Suite.SuiteClasses({ChessGameTest.class, KingTest.class, QueenTest.class, BishopTest.class,RookTest.class,KnightTest.class, PawnTest.class, MountedBishopTest.class, MountedRookTest.class})
    public static class AllPieceTest {
    }
}
