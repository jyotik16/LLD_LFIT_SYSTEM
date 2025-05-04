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
        System.out.println( "Wleome to the DLF LIFT! " );

        Lift lift1 = new Lift(1,new StoppedState(), new DefaultMovementStrategy());

        LiftController controller = new LiftController(List.of(lift1));
        controller.requestLift(3);
        controller.requestLift(5);
        controller.requestLift(2);


        // Emergency handling
        lift1.setMovementStrategy(new EmergencyMovementStrategy());
        lift1.handleRequest(lift1,0);

    }
}
