package com.lift;

import com.lift.controller.LiftController;
import com.lift.model.Lift;
import com.lift.model.StoppedState;
import com.lift.strategy.DefaultMovementStrategy;
import com.lift.strategy.EmergencyMovementStrategy;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to the DLF LIFT! " );

         /*
            multi_requesting_testing();
         */

        /*
            emergency_testing();
        */

        optimialRoute_testing();

    }

    private static void optimialRoute_testing() {
        Lift lift1 = new Lift(1,new StoppedState(), new DefaultMovementStrategy());
        LiftController controller = new LiftController(List.of(lift1));

        Runnable user1 = () -> controller.requestLift(1);
        Runnable user2 = () -> controller.requestLift(6);
        Runnable user3 = () -> controller.requestLift(2);
        Runnable user4 = () -> controller.requestLift(3);

        Thread t1 = new Thread(user1,"thread-1");
        Thread t2 = new Thread(user2,"thread-2");
        Thread t3 = new Thread(user3,"thread-3");
        Thread t4 = new Thread(user4,"thread-4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    static void multi_requesting_testing(){
        Lift lift1 = new Lift(1,new StoppedState(), new DefaultMovementStrategy());
        Lift lift2 = new Lift(2,new StoppedState(), new DefaultMovementStrategy());

        LiftController controller = new LiftController(List.of(lift1,lift2));

        Runnable user1 = () -> controller.requestLift(3);
        Runnable user2 = () -> controller.requestLift(5);


        Runnable user4 = () -> controller.requestLift(1);
        Runnable user5 = () -> controller.requestLift(6);


        Thread t1 = new Thread(user1,"thread-1");
        Thread t2 = new Thread(user2,"thread-2");

        Thread t4 = new Thread(user4,"thread-4");
        Thread t5 = new Thread(user5,"thread-5");

        t4.start();
        t5.start();
        System.out.println("---------------------");
        System.out.println("---------------------");
        t1.start();
        t2.start();
    }

    static void emergency_testing(){
        Lift lift1 = new Lift(1,new StoppedState(), new EmergencyMovementStrategy());
        Lift lift2 = new Lift(2,new StoppedState(), new EmergencyMovementStrategy());

        LiftController controller = new LiftController(List.of(lift1,lift2));

        Runnable user1 = () -> controller.requestLift(3);
        Runnable user2 = () -> controller.requestLift(5);

        Thread t1 = new Thread(user1,"thread-1");
        Thread t2 = new Thread(user2,"thread-2");

        t1.start();
        t2.start();
    }
}
